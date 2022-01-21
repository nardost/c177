package capitals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StateCapitalsApp {

    public static void main(String[] args) throws IOException {

        // map that holds the (state, Capital) key-value pairs
        final Map<String, Capital> stateCapitals = new HashMap<>();

        // construct platform independent file path
        final String filePath = System.getProperty("user.dir") +
                File.separator +
                "resources" +
                File.separator +
                "MoreStateCapitals.txt";

        try (final BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // define the delimiter string
            final String delimiter = "::";

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    final String[] tokens = line.split(delimiter);
                    final String state = tokens[0];
                    final String capitalName = tokens[1];
                    final long population = Long.parseLong(tokens[2]);
                    final double area = Double.parseDouble(tokens[3]);
                    stateCapitals.put(state, new Capital(capitalName, population, area));
                } catch (NumberFormatException | IndexOutOfBoundsException re) {
                    // ignore the malformed record and continue to the next iteration
                }
            }
        } finally {
            System.out.printf("%n%d STATE/CAPITAL PAIRS LOADED.%n", stateCapitals.size());
            System.out.println("==============================");
            stateCapitals.forEach((state, capital) -> System.out.printf("%s - %s%n", state, capital));

            final Scanner scanner = new Scanner(System.in);

            System.out.print("Please enter the lower limit for capital city population: ");
            final long population = scanner.nextLong();
            System.out.printf("%n%nLISTING CAPITALS WITH POPULATIONS GREATER THAN %d%n", population);
            stateCapitals.entrySet()
                    .stream()
                    .filter(e -> e.getValue().getPopulation() > population)
                    .forEach(e -> System.out.printf("%s - %s%n", e.getKey(), e.getValue()));

            System.out.print("Please enter the upper limit for capital city sq mileage: ");
            final double area = scanner.nextDouble();
            System.out.printf("%n%nLISTING CAPITALS WITH AREAS LESS THAN %f%n", area);
            stateCapitals.entrySet()
                    .stream()
                    .filter(e -> e.getValue().getSquareMileage() > area)
                    .forEach(e -> System.out.printf("%s - %s%n", e.getKey(), e.getValue()));
        }
    }
}
