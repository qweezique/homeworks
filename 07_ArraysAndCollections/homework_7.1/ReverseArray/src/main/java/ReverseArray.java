public class ReverseArray {

    //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
    public static String[] reverse (String[] strings){
        int length = strings.length;
        for (int i = 0; i < length/2; i++) {
            String temp = strings[length-i-1];
            strings[length-i-1] = strings[i];
            strings[i] = temp;
        }
        return strings;
    }
}
