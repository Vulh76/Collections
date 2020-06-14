import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.*;

public class WordProcessor implements Iterable<Record> {
    private final ArrayList<Record> records = new ArrayList<>();

    public WordProcessor() {
    }

    public WordProcessor(@NotNull String fileName) {
        loadFromFile(fileName);
    }

    public int loadFromFile(@NotNull String fileName) {
        try(FileInputStream inFile = new FileInputStream("input.txt")) {
            BufferedReader buf = new BufferedReader(new InputStreamReader(inFile));
            NavigableSet<Record> recordSet = new TreeSet<>(new recordComparator());
            String str;
            while ((str = buf.readLine()) != null) {
                Record record = new Record(str);
                SortedSet<Record> findRecords = recordSet.subSet(record, true, record, true);
                if (findRecords.isEmpty()) {
                    recordSet.add(record);
                } else {
                    Record r = findRecords.first();
                    r.setCount(r.getCount() + 1);
                }
            }

            records.addAll(recordSet);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return records.size();
    }

    public int size() {
        return records.size();
    }

    public Record getRecord(int index) {
        if (index < 0 || index > records.size() - 1)
            throw new NoSuchElementException();
        return records.get(index);
    }

    @Override
    public Iterator<Record> iterator() {
        return new Iterator<Record>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return current < records.size() - 1;
            }

            @Override
            public Record next() {
                if (current == records.size() - 1)
                    throw new NoSuchElementException();
                current++;
                return records.get(current);
            }
        };
    }

    public Iterator<Record> reverseIterator() {
        return new Iterator<Record>() {
            int current = records.size() - 1;

            @Override
            public boolean hasNext() {
                return current > 0;
            }

            @Override
            public Record next() {
                if (current == 0)
                    throw new NoSuchElementException();
                current--;
                return records.get(current);
            }
        };
    }

    private static class recordComparator implements Comparator<Record> {
        private final Collator russianCollator = Collator.getInstance(new Locale("ru", "RU"));

        @Override
        public int compare(Record o1, Record o2) {
            if (o1.getWord().length() < o2.getWord().length()) return -1;
            else if (o1.getWord().length() > o2.getWord().length()) return 1;
            else return russianCollator.compare(o1.getWord(), o2.getWord());
        }
    }
}
