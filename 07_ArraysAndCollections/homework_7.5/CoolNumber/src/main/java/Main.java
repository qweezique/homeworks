import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

    public static void main(String[] args) {

        List<String> testList = CoolNumbers.generateCoolNumbers();

        System.out.println("Сгенерирован список автомобильных номеров, в размере "
                + testList.size() + " единиц");


        System.out.println();
        CoolNumbers.bruteForceSearchInList(testList, "А001АА199");
        System.out.println();
        CoolNumbers.binarySearchInList(testList, "А001АА199");
        System.out.println();


        HashSet <String> testHash = new HashSet<>(testList);
        CoolNumbers.searchInHashSet(testHash, "А001АА199");
        System.out.println();
        TreeSet<String> testTreeSet = new TreeSet<>(testList);
        CoolNumbers.searchInTreeSet(testTreeSet, "А001АА199");
        System.out.println();

    }
}
