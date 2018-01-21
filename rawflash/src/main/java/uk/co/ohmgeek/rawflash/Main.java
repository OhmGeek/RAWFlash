package uk.co.ohmgeek.rawflash;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.*;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    private static String RPC_QUEUE_NAME = "rawflash";

    private static String getBase64Image(String output) throws IOException {
        File f = new File(output);
        BufferedImage image = ImageIO.read(f);

        // now get the png format to send:

        ByteArrayOutputStream binaryOutput = new ByteArrayOutputStream();
        ImageIO.write(image, "png", binaryOutput);

        String data = DatatypeConverter.printBase64Binary(binaryOutput.toByteArray());
        return "data:image/png;base64," + data;
    }


    public static void main(String[] argv) {
        ImageIO.setUseCache(false);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = null;
        try {
            connection      = factory.newConnection();
            final Channel channel = connection.createChannel();

            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                            .Builder()
                            .correlationId(properties.getCorrelationId())
                            .build();

                    String response = "";

                    try {
                        String json = new String(body,"UTF-8");
                        OperationManager opManager = new OperationManager();
                        FileManager fileManager = new FileManager();

                        // Take JSON and convert to hashmap.
                        HashMap<String, String> jsonString = new Gson().fromJson(
                                json,
                                new TypeToken<HashMap<String, String>>(){}.getType()
                        );

                        // Download the file, caching it locally.

                        String newFilePath = fileManager.downloadFile(jsonString.get("filename")).getAbsolutePath();
                        jsonString.put("filename", newFilePath);
                        System.out.println("New File Path: ");
                        System.out.println(newFilePath);
                        // Only now, do we process the image, yielding the result.
                        opManager.setInstructions(jsonString);


                        String output = opManager.process();
                        System.out.println(output);
                        String dataToSend = "{'img': " + getBase64Image(output) + "}";
                        System.out.println(dataToSend);
                        response += dataToSend;
                        channel.basicPublish( "", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));

                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                    catch (RuntimeException e){
                        System.out.println(" [.] " + e.toString());
                    }
                    finally {
                        System.out.println("Finally output response");
                        System.out.println(response);

                        // RabbitMq consumer worker thread notifies the RPC server owner thread
                        synchronized(this) {
                            this.notify();
                        }
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);

            // Wait and be prepared to consume the message from RPC client.
            while (true) {
                synchronized(consumer) {
                    try {
                        consumer.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (IOException _ignore) {}
        }
    }
}
