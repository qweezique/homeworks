import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {
        String[] letter = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        Arrays.sort(letter);
        HashSet<String> arrNumber = new HashSet<>();

        //красивые буквы
        //цикл для каждого элемента из [] letter
        for (String a123 : letter) {
            for (int reg = 1; reg <= 199; reg++) {
                for (int j = 1; j <= 999; j++) {
                    arrNumber.add(String.format("%s%03d%s%s%d", a123, j, a123, a123, reg));
                }
            }
        }
        //красивые буквы
        for (int i = 111; i <= 999; i += 111) {
            for (String a1 : letter) {
                for (String a2 : letter) {
                    for (String a3 : letter) {
                        for (int reg = 1; reg <= 199; reg++) {
                            arrNumber.add(String.format("%s%03d%s%s%d", a1, i, a2, a3, reg));
                        }
                    }
                }
            }
        }
        ArrayList<String> arrNum = new ArrayList<>(arrNumber);
        return arrNum;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        long startTimer = System.nanoTime();
        if (list.contains(number)) {
            long endTimer = System.nanoTime();
            System.out.println("Поиск в List, когда элемент найден, нс = " + (endTimer - startTimer));
            return true;
        }
        long endTimer = System.nanoTime();
        System.out.println("Поиск в List, когда элемент не найден, нс = " + (endTimer - startTimer));
        return false;
    }


    public static boolean binarySearchInList(List<String> sortedList, String number) {
        Collections.sort(sortedList);
        long startTimer = System.nanoTime();
        if (Collections.binarySearch(sortedList, number) >= 0) {
            long endTimer = System.nanoTime();
            System.out.println("Бинарный поиск, когда элемент найден, нс = " + (endTimer - startTimer));
            return true;
        }
        long endTimer = System.nanoTime();
        System.out.println("Бинарный поиск, когда элемент не найден, нс = " + (endTimer - startTimer));
        return false;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        long startTimer = System.nanoTime();
        if (hashSet.contains(number)) {
            long endTimer = System.nanoTime();
            System.out.println("__HashSet поиск, когда элемент найден, нс = " + (endTimer - startTimer));
            return true;
        }
        long endTimer = System.nanoTime();
        System.out.println("__HashSet поиск, когда элемент не найден, нс = " + (endTimer - startTimer));
        return false;
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        long startTimer = System.nanoTime();
        if (treeSet.contains(number)) {
            long endTimer = System.nanoTime();
            System.out.println("___TreeSet поиск, когда элемент найден, нс = " + (endTimer - startTimer));
            return true;
        }
        long endTimer = System.nanoTime();
        System.out.println("___TreeSet поиск, когда элемент Не найден, нс = " + (endTimer - startTimer));
        return false;
    }
}
