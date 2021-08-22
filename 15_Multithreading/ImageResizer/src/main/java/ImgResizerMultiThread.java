import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgResizerMultiThread implements Runnable {

    private int newWidth;
    private String dstFolder;
    private File file;

    public ImgResizerMultiThread(File file, String dstFolder, int newWidth) {
        this.file = file;
        this.dstFolder = dstFolder;
        this.newWidth = newWidth;
    }

    @Override
    public void run() {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                BufferedImage newImage = Scalr.resize(image, Scalr.Method.QUALITY, newWidth);
                String imgFormatOfFile = file.getName().replaceAll(".*.[^(png|jpeg|jpg|bmp|gif|wbmp)]"
                        , "").trim();
                File outputFile = new File(dstFolder + "/__resizedMultiThread_width_" + newWidth + "_"
                        + file.getName());
                ImageIO.write(newImage, imgFormatOfFile, outputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
