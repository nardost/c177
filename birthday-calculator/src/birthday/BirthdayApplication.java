package birthday;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;

public class BirthdayApplication {

    final static Map<String, String> dateTimeFormatters = new HashMap<>();

    static {
        System.out.println("Welcome to the 100% Scientifically Accurate Birthday Calculator!");
        System.out.println();

        // allowed birthday date patterns and corresponding formatters
        // https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#ofPattern-java.lang.String-java.util.Locale-
        Arrays.stream(new String[][] {
                { "\\d{1,2}/\\d{1,2}/\\d{4}", "dd/MM/yyyy" },
                { "\\d{1,2}-\\d{1,2}-\\d{4}", "dd-MM-yyyy" }
        }).forEach(e -> dateTimeFormatters.put(e[0], e[1]));
    }

    public static void main(String[] args) {

        try (final Scanner scanner = new Scanner(System.in)) {
            String birthdayString;
            System.out.print("What is your birthday? ");
            birthdayString = scanner.next();

            final LocalDate birthdate = getLocalDateFromString(birthdayString);

            final LocalDate today = LocalDate.now();
            final LocalDate thisYearsBirthday = LocalDate.of(today.getYear(), birthdate.getMonth(), birthdate.getDayOfMonth());
            final LocalDate nextYearsBirthday = LocalDate.of(today.getYear() + 1, birthdate.getMonth(), birthdate.getDayOfMonth());

            final boolean thisYearsAlreadyPassed = today.until(thisYearsBirthday).getDays() < 0;
            final LocalDate upcomingBirthday = thisYearsAlreadyPassed ? nextYearsBirthday : thisYearsBirthday;
            final String fallsFell = thisYearsAlreadyPassed ? "fell" : "falls";

            final long daysLeftToNextBirthday = DAYS.between(today, upcomingBirthday);
            final long age = YEARS.between(birthdate, upcomingBirthday);

            System.out.printf("That means you were born on a %s!%n", birthdate.getDayOfWeek().toString().toUpperCase());
            System.out.printf("This year it %s on a %s...%n", fallsFell, thisYearsBirthday.getDayOfWeek().toString().toUpperCase());
            System.out.printf("And since today is %s,%n", today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
            System.out.printf("there's only %d more days until the next one when you turn %d!%n%n", daysLeftToNextBirthday, age);

        } catch (RuntimeException rte) {
            System.out.println("Valid date formats are:");
            dateTimeFormatters.values().forEach(System.out::println);
        }
    }

    private static LocalDate getLocalDateFromString(final String dateString) {
        // infer regex pattern from given date
        final String pattern = dateTimeFormatters.keySet().stream()
                .filter(dateString::matches)
                .findFirst()
                .orElseThrow(RuntimeException::new);
        final String format = dateTimeFormatters.get(pattern);
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateString, dateTimeFormatter);
    }
}
