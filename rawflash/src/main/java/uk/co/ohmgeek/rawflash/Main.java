package uk.co.ohmgeek.rawflash;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.*;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    private static final String RPC_QUEUE_NAME = "rawflash";

    private static String getBase64Image(BufferedImage output) throws IOException {

        // now get the png format to send:

        ByteArrayOutputStream binaryOutput = new ByteArrayOutputStream();
        ImageIO.write(output, "png", binaryOutput);

        String data = DatatypeConverter.printBase64Binary(binaryOutput.toByteArray());
        System.out.println("Finished Base64 conversion");
        return "data:image/png;base64," + data;
    }


    public static void main(String[] argv) {
      try
        {
          Thread.sleep(15000);
        }
        catch(InterruptedException ex)
        {
          Thread.currentThread().interrupt();
        }
        ImageIO.setUseCache(false);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq"); // using Docker.

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
                        String json = new String(body, "UTF-8");
                        OperationManager opManager = new OperationManager();
                        FileManager fileManager = new FileManager();

                        // Take JSON and convert to hashmap.
                        HashMap<String, String> jsonString = new Gson().fromJson(
                                json,
                                new TypeToken<HashMap<String, String>>() {
                                }.getType()
                        );

                        // Download the file, caching it locally.

                        String newFilePath = fileManager.downloadFile(jsonString.get("filename")).getAbsolutePath();
                        jsonString.put("filename", newFilePath);
                        System.out.println("New File Path: ");
                        System.out.println(jsonString.get("filename"));
                        // Only now, do we process the image, yielding the result.
                        opManager.setInstructions(jsonString);


                        BufferedImage output = opManager.process();
                        String base64Image = getBase64Image(output);

                        // output to JSON
                        HashMap<String, String> mappedOutput = new HashMap<String, String>();
                        mappedOutput.put("img", base64Image);
                        mappedOutput.put("client", jsonString.get("client"));

                        String dataToSend = new Gson().toJson(mappedOutput);
                        response += dataToSend;
                        System.gc();

//                        File dir = new File("/rawflash_cache");
//                        File[] toBeDeleted = dir.listFiles(pathname -> (pathname.getName().endsWith(".tiff")));
//                        if(toBeDeleted != null) {
//                            for (File file : toBeDeleted) {
//                                file.delete(); // try to delete. Not a major issue
//                            }
//                        }
                    }
                    catch (Exception e){
                        System.out.println(" [.] " + e.toString());
                    }
                    finally {
                        channel.basicPublish( "", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));

                        channel.basicAck(envelope.getDeliveryTag(), false);
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
                    } catch (InterruptedException e) {
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
