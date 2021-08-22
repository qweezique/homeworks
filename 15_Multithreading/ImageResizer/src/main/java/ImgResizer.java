import lombok.Setter;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImgResizer {
    @Setter
    static int newWidth = 300;
    @Setter
    static String srcFolder = "src/main/resources/imgSrc";
    @Setter
    static String dstFolder = "src/main/resources/imgDst";

    public static void getSrcFolder() {
        System.out.println("Source folder is: " + srcFolder);
    }

    public static void getDestinationFolder() {
        System.out.println("Destination folder is: " + dstFolder);
    }

    public static void doResize() {
        long start = System.currentTimeMillis();
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                BufferedImage newImage = Scalr.resize(image, Scalr.Method.QUALITY, newWidth);
                String imgFormatOfFile = file.getName().replaceAll(".*.[^(png|jpeg|jpg|bmp|gif|wbmp)]", "").trim();
                File outputFile = new File(dstFolder + "/__resized_width_" + newWidth + "_" + file.getName());
                ImageIO.write(newImage, imgFormatOfFile, outputFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration in Mono-thread: " + (System.currentTimeMillis() - start));
    }
}
