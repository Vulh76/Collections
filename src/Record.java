import java.text.Collator;
import java.util.Locale;
import java.util.Objects;

public class Record implements Comparable<Record> {
    private final Collator collator;
    private final String word;
    private int count = 1;

    public Record(String word) {
        this.word = word;
        this.collator = Collator.getInstance(new Locale("ru", "RU"));
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Record o) {
        if (this.getWord().length() < o.getWord().length()) return -1;
        else if (this.getWord().length() > o.getWord().length()) return 1;
        else return this.collator.compare(this.getWord(), o.getWord());
    }

    @Override
    public String toString() {
        return "Record{" +
                "Word='" + word + '\'' +
                ", Count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(getWord(), record.getWord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord());
    }
}
