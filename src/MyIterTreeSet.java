import java.util.Iterator;
import java.util.TreeSet;

public class MyIterTreeSet<E> extends TreeSet<E> {
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<E> revIt = MyIterTreeSet.super.descendingIterator();

            @Override
            public boolean hasNext() {
                return revIt.hasNext();
            }

            @Override
            public E next() {
                return revIt.next();
            }
        };

        /*return () -> new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };*/
    }
}
