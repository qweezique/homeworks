import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Loader {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        new File("numbers_first.txt").createNewFile();
        new File("numbers_second.txt").createNewFile();

        try (BufferedWriter firstFile = new BufferedWriter(new FileWriter("numbers_first.txt"));
             BufferedWriter secondFile = new BufferedWriter(new FileWriter("numbers_second.txt"))) {

            ExecutorService executor = Executors.newWorkStealingPool();
            Set<Callable<String>> callables = new HashSet<>();

            for (int regionCode = 1; regionCode < 100; regionCode++) {

                int finalRegionCode = regionCode;

                callables.add(() -> {
                    if (finalRegionCode % 2 == 0) {
                        firstFile.write(task(finalRegionCode));
                    } else {
                        secondFile.write(task(finalRegionCode));
                    }
                    return Thread.currentThread().getName().concat(" - Ok");
                });

            }

            List<Future<String>> futures = executor.invokeAll(callables);

            for (Future<String> future : futures) {
                System.out.println(future.get());
            }

            firstFile.flush();
            secondFile.flush();

            executor.shutdown();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    public static String task(int regionCode) {
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        StringBuilder builder = new StringBuilder();
        for (int number = 1; number < 1000; number++) {
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter)
                                .append(padNumber(number, 3))
                                .append(secondLetter)
                                .append(thirdLetter)
                                .append(padNumber(regionCode, 2))
                                .append('\n');
                    }
                }
            }
        }
        return builder.toString();
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = String.valueOf(number);
        int padSize = numberLength - numberStr.length();
        return padSize > 0 ? "0".repeat(padSize).concat(numberStr) : numberStr;
    }

}
