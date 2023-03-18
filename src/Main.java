import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sname, name, patronymic, dob;

        try {
            System.out.println("Введите фамилию, имя, отчество и дату рождения:");
            Scanner scanner = new Scanner(System.in);
            sname = scanner.next();
            name = scanner.next();
            patronymic = scanner.next();
            dob = scanner.next();
        } catch (Exception e) {
            System.out.println("Ошибка при считывании данных - проверьте правильность ввода!");
            return;
        }
        sname = sname.substring(0, 1).toUpperCase() + sname.substring(1);

        char sex = switch (Character.toLowerCase(patronymic.charAt(patronymic.length() - 1))) {
            case 'а' -> 'Ж';
            case 'ч' -> 'М';
            default -> '?';
        };

        int years;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate start = LocalDate.parse(dob, formatter);
            LocalDate end = LocalDate.now();
            years = (int) ChronoUnit.YEARS.between(start, end);
        } catch (Exception e) {
            System.out.println("Ошибка в дате рождения! Проверьте правильность и формат ввода: ДД.ММ.ГГГГ");
            return;
        }
        String yrs_word = switch (years % 10) {
            case 1 -> "год";
            case 2, 3, 4 -> "года";
            default -> "лет";
        };

        System.out.printf("%s %c.%c.\t%c \t%d %s", sname, Character.toUpperCase(name.charAt(0)), Character.toUpperCase(patronymic.charAt(0)), sex, years, yrs_word);
    }
}