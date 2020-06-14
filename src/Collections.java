import java.util.*;

public class Collections {
    public static void main(String[] args) {
        WordProcessor wordProcessor = new WordProcessor();

        int wordCount = wordProcessor.loadFromFile("input.txt");
        System.out.println("\r\nКоличество уникальных слов в файле: " + wordCount);

        System.out.println("\r\nСписок слов и статистика по словам:");
        for (Record r : wordProcessor) {
            System.out.println(r.getWord() + "   " + r.getCount());
        }

        System.out.println("\r\nСписок слов в обратном порядке:");
        Iterator<Record> iter = wordProcessor.reverseIterator();
        while (iter.hasNext())
        {
            System.out.println(iter.next().getWord());
        }

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("\r\nВведите номер строки (для звершения введите -1): ");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if(num < 0) break;
                if (num < wordProcessor.size())
                    System.out.println(wordProcessor.getRecord(num).getWord());
                else
                    System.out.println("Такой строки не существует!");
            }
            else {
                System.out.println("Ошибка ввода!");
                scanner.next();
            }
        }
    }
}
