import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String SRC_DATA_IMAGES = "src/data/images/";
    private static final String URL = "https://lenta.ru";

    public static void main(String[] args) {
        downLoadImagesFromURL(URL, SRC_DATA_IMAGES);
        printAllFilesInFolder(SRC_DATA_IMAGES);
    }


    public static void downLoadImagesFromURL(String URL, String localFolderPath) {

        try {
            Document doc = Jsoup.connect(URL).get();

            Elements images = doc.getElementsByTag("img");
            List<String> srcImgLinks =
                    images.stream()
                            .map(element -> element.absUrl("src"))
                            .filter(links -> links.endsWith(".jpg") || links.endsWith(".jpeg"))
                            .collect(Collectors.toList());

            for (String imgLink : srcImgLinks
            ) {
                int indexName = imgLink.lastIndexOf("/");

                if (indexName == imgLink.length()) {
                    imgLink = imgLink.substring(1, indexName);
                }

                String name = imgLink.substring(indexName);
                FileUtils.copyURLToFile(new URL(imgLink), new File(localFolderPath + name));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void printAllFilesInFolder(String destination) {
        File directoryPath = new File(destination);
        String[] contents = directoryPath.list();
        System.out.println("List of Files in ~/" + directoryPath.getName() + ":\n");
        Arrays.stream(contents).forEach(System.out::println);
    }
}


