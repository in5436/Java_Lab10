package Java_Lab10.Task1;

import java.lang.reflect.Field;
import java.util.Scanner;

public class ReflectionMain {

    public static void main(String[] args) {
        String s1 = "Це початковий рядок.";
        String newValue1 = "Це вже ЗМІНЕНИЙ рядок!";

        System.out.println("--- 1. Тест з літералом ---");
        System.out.println("До:    " + s1 + " (id=" + System.identityHashCode(s1) + ")");

        changeString(s1, newValue1);

        System.out.println("Після: " + s1 + " (id=" + System.identityHashCode(s1) + ")");
        System.out.println("Чи змінився об'єкт? " + (s1 == newValue1));
        System.out.println("Чи рівний вміст? " + s1.equals(newValue1));

        System.out.println("\n--- 2. Тест з клавіатурою ---");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть початковий рядок (напр., 'Привіт'):");
        String s2 = scanner.nextLine();

        System.out.println("Введіть рядок для заміни (напр., 'Бувай'):");
        String newValue2 = scanner.nextLine();

        System.out.println("До:    " + s2);
        changeString(s2, newValue2);
        System.out.println("Після: " + s2);

        scanner.close();
    }

    public static void changeString(String original, String newValue) {

        try {
            Class<String> stringClass = String.class;

            Field valueField = stringClass.getDeclaredField("value");

            valueField.setAccessible(true);

            byte[] newBytes = (byte[]) valueField.get(newValue);

            valueField.set(original, newBytes);

        } catch (Exception e) {
            System.err.println("Помилка рефлексії: " + e.getMessage());
        }
    }
}
