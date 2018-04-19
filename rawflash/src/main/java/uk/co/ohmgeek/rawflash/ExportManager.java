package uk.co.ohmgeek.rawflash;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExportManager {
    private final String[] ALLOWED_FORMATS = {
            "png", "tiff", "png", "tiff", "bmp", "ppm"
    };
    public ExportManager() {

    }
    private String getBase64Image(BufferedImage output, String format) throws IOException {

        // now get the png format to send:

        ByteArrayOutputStream binaryOutput = new ByteArrayOutputStream();
        ImageIO.write(output, format, binaryOutput);

        String data = DatatypeConverter.printBase64Binary(binaryOutput.toByteArray());
        System.out.println("Finished Base64 conversion");
        return "data:image/" + format + ";base64," + data;
    }
    public HashMap<String, String> process(BufferedImage image, HashMap<String, String> instructions, HashMap<String, String> response) {
        if(instructions.containsKey("export") && instructions.get("export").equals("true")) {
            String format = instructions.get("export-format");

            try {
                String base64output = getBase64Image(image, format);

                response.put("export-base64", base64output);
                response.put("export-format", format);
            } catch(Exception ignored) {
                // We just ignore any export errors
            }

        }

        return response;
    }
}
