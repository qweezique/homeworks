import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ForkJoinPool;


public class Main {


    private static final String MAP_NAME = "data/sitemap.txt";

    private static final String URL_NAME = "https://skillbox.ru/";


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();

        List<String> workedLink = new ArrayList<>();
        workedLink.add(URL_NAME);
        LinkProcessor processor = new LinkProcessor(URL_NAME,0, workedLink);
        pool.execute(processor);
        pool.shutdown();

        processor.join();
        List<String> results = processor.getWorkedLinks();
        results.forEach(System.out::println);

        long end = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (end - start));

        try {
            Files.deleteIfExists(Path.of(MAP_NAME));
            Files.write(Path.of(MAP_NAME),results, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
