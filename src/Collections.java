import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.*;

public class Collections {
    public static void main(String[] args) {
        try(FileInputStream inFile = new FileInputStream("input.txt")) {
            BufferedReader buf = new BufferedReader(new InputStreamReader(inFile));
            TreeMap<String, Integer> words = new TreeMap<>(new stringComparator());
            String str;
            while ((str = buf.readLine()) != null) {
                Integer cnt = words.put(str, 1);
                if (cnt != null) {
                    words.put(str, ++cnt);
                }
            }

            System.out.println("\r\nСтатистика по словам:");
            printMap(words);

            System.out.println("\r\nСписок слов в обратном порядке:");



            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.print("\r\nВведите номер строки (для звершения введите -1): ");
                if (scanner.hasNextInt()) {
                    int num = scanner.nextInt();
                    if(num < 0) break;
                    printMapRec(words, num);
                }
                else {
                    scanner.next();
                }
            }

            ArrayList<String> list = new ArrayList<>();

        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static class stringComparator implements Comparator<String> {
        private final Collator russianCollator = Collator.getInstance(new Locale("ru", "RU"));

        @Override
        public int compare(String o1, String o2) {
            if (o1.length() < o2.length()) return -1;
            else if (o1.length() > o2.length()) return 1;
            else return russianCollator.compare(o1, o2);
        }
    }

    public static void printMap(Map map) {
        Set s = map.entrySet();
        Iterator it = s.iterator();

        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            System.out.println(m.getKey() + "   " +  m.getValue());
        }
    }

    public static void printMapRec(Map map, Integer num) {
        if (num < 0 || num > map.size() - 1) return;

        Set s = map.entrySet();
        Iterator it = s.iterator();

        for (int i = 0; i < num; i++) {
            it.next();
        }

        Map.Entry m = (Map.Entry) it.next();
        System.out.println(m.getKey());
    }
}
