import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static final long KB = 1000L;
    public static final long MB = 1000L*1000L;
    public static final long GB = 1000L*1000L*1000L;
    private static long folderSize = 0;

    public static long calculateFolderSize(String path) {
        try {
            Path folderPath = Paths.get(path);
            folderSize = Files.walk(folderPath)
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .mapToLong(File::length)
                    .sum();

            return folderSize;
        } catch (IOException e) {
            System.out.println("Ошибка ввода Path");
            e.getStackTrace();
        }
        return 0; //Зачем IDE просит вернуть 0? То есть в случае exception вернется 0?
    }

    public static void folderInfo(String path) {
        if (folderSize <= 0) {}

        else

        {
            Path folder = Paths.get(path).getFileName();
            StringBuilder folderInfo = new StringBuilder();
            String generalInfo = "Size of " + "\"" + folder + "\"" + " is ";
            folderInfo.append(generalInfo);
            if (folderSize <= KB) {
                System.out.println(folderInfo.append(folderSize).append("B"));
            }

            if (folderSize >= KB && folderSize < MB) {
                folderSize /= KB;
                System.out.println(folderInfo.append(folderSize).append("KB"));
            }

            if (folderSize >= MB && folderSize < GB) {
                folderSize /= MB;
                System.out.println(folderInfo.append(folderSize).append("MB"));
            }

            if (folderSize >= GB) {
                folderSize /= GB;
                System.out.println(folderInfo.append(folderSize).append("GB"));
            }
        }
    }
}
