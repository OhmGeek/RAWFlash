package uk.co.ohmgeek.rawflash;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;
import uk.co.ohmgeek.jdcraw.operations.NegativeBrightnessException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Main {

    private static String getBase64Image(String output) throws IOException {
        File f = new File(output);
        BufferedImage image = ImageIO.read(f);

        // now get the png format to send:

        ByteArrayOutputStream binaryOutput = new ByteArrayOutputStream();
        ImageIO.write(image, "png", binaryOutput);

        String data = DatatypeConverter.printBase64Binary(binaryOutput.toByteArray());
        return "data:image/png;base64," + data;
    }
    public static void main(String args[]) throws NegativeBrightnessException, IOException {
        // socketio server
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(3000);
        SocketIOServer server = new SocketIOServer(config);


        server.addEventListener("process-image", ProcessCommandObject.class, new DataListener<ProcessCommandObject>() {
            @Override
            public void onData(SocketIOClient client, ProcessCommandObject command, AckRequest ackRequest) throws Exception {
                // load json instructions into processor, and run
                OperationManager opManager = new OperationManager();
                opManager.loadInstructions(command.getJSONInput());
                String output = opManager.process();
                String dataToSend = getBase64Image(output);
                // now we read the file again, converting it to Base64.
                // send back

                client.sendEvent("image-processed", dataToSend);

            }
        });

        server.start();



//        // json string to process for testing purposes
//        String stringToProcess = "{" +
//                    "'filename': '/home/ryan/Pictures/RAW_NIKON_D7100.NEF', " +
//                    "'exposure': 2," +
//                    "'histogram-equalization': true," +
//                    "'mean-blur': 5" +
//                "}";
//
//        // load json instructions into processor, and run
//        OperationManager opManager = new OperationManager();
//        opManager.loadInstructions(stringToProcess);
//        opManager.process();
    }
}
