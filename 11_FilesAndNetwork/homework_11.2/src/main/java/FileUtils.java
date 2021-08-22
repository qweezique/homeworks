import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        try {


            File source = new File(sourceDirectory);
            File destinaton = new File(destinationDirectory);

            org.apache.commons.io.FileUtils.copyDirectory(source, destinaton);
        } catch (IOException e) {
            e.printStackTrace();
        }

//Решение без сторонних библиотек

//    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
//
//                Files.walk(Paths.get(sourceDirectory))
//                        .forEach(source -> {
//                            Path destination = Paths.get(destinationDirectory, source.toString()
//                                    .substring(sourceDirectory.length()));
//                            try {
//                                Files.copy(source, destination);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        });
//        }

    }
}
