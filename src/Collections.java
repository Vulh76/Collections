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
            TreeSet<Record> records = new TreeSet<>(new recordComparator());
            String str;
            while ((str = buf.readLine()) != null) {
                Record record = new Record(str);
                SortedSet<Record> rec = records.subSet(record, true, record, true);
                if (rec.isEmpty()) {
                    records.add(record);
                }
                else {
                    Record r = rec.first();
                    r.setCount(r.getCount() + 1);
                }
            }

            System.out.println("\r\nКоличество уникальных слов в файле: " + records.size());

            System.out.println("\r\nСписок слов:");
            for (Record r : records) {
                System.out.println(r.getWord());
            }

            System.out.println("\r\nСтатистика по словам:");
            for (Record r : records) {
                System.out.println(r.getWord() + "   " + r.getCount());
            }

            System.out.println("\r\nСписок слов в обратном порядке:");
            Iterator<Record> iter = records.descendingIterator();
            while (iter.hasNext())
            {
                System.out.println(iter.next().getWord());
            }

            MyIterTreeSet<Record> myTreeSet = new MyIterTreeSet<>();
            myTreeSet.addAll(records);
            System.out.println("\r\nСписок слов в обратном порядке при помощи собственного итератора:");
            Iterator<Record> myIter = myTreeSet.iterator();
            while (myIter.hasNext())
            {
                System.out.println(myIter.next().getWord());
            }

            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.print("\r\nВведите номер строки (для звершения введите -1): ");
                if (scanner.hasNextInt()) {
                    int num = scanner.nextInt();
                    if(num < 0) break;
                    printRecNum(records, num);
                }
                else {
                    System.out.println("Ошибка ввода!");
                    scanner.next();
                }
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void printRecNum(Set<Record> set, Integer num) {
        if (num < 0 || num > set.size() - 1) return;

        Record[] records = new Record[set.size()];
        set.toArray(records);

        System.out.println(records[num].getWord());
    }

    public static class recordComparator implements Comparator<Record> {
        private final Collator russianCollator = Collator.getInstance(new Locale("ru", "RU"));

        @Override
        public int compare(Record o1, Record o2) {
            if (o1.getWord().length() < o2.getWord().length()) return -1;
            else if (o1.getWord().length() > o2.getWord().length()) return 1;
            else return russianCollator.compare(o1.getWord(), o2.getWord());
        }
    }
}
