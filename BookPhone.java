import java.io.*;
import java.util.*;

public class BookPhone {
    public static void main(String[] args) {
        Map<String, List<String>> bookPhone = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Введите имя или пустую строку, чтобы завершить: ");
                String name = scanner.nextLine();
                if (name.isEmpty()) {
                    break;
                }
                
                System.out.print("Введите номер телефона в формате +70000000000: ");
                String phoneNumber = scanner.nextLine();

                bookPhone.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNumber);
            }

            try (PrintWriter writer = new PrintWriter("numbers.txt")) {
                for (Map.Entry<String, List<String>> entry : bookPhone.entrySet()) {
                    writer.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

     

        List<Map.Entry<String, List<String>>> sortedEntries = new ArrayList<>(bookPhone.entrySet());
        sortedEntries.sort(Comparator.comparing(entry -> -entry.getValue().size()));

        for (Map.Entry<String, List<String>> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}