/**
 * Read web server data and analyse hourly access patterns.
 *
 * @author Ashlyn Balicki, David J. Barnes, and Michael Kölling.
 * @version 2023.12.19
 */
public class LogAnalyzer {
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer() {
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }

    //7.12

    /**
     * Constructor
     *
     * @param filename name of the LogfileReader.
     */
    public LogAnalyzer(String filename) {
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Return the number of accesses recorded in the log file.
     */
    public int numberOfAccesses() {
        int total = 0;
        for (int hourCount : hourCounts) {
            total += hourCount;
        }
        return total;
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData() {
        while (reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts() {
        System.out.println("Hr: Count");
        for (int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }

    // 7.15

    /**
     * Prints and returns the busiest hour in a year.
     * If there are multiple hours tied for busiest,
     * it says there's a tie and only prints the first.
     *
     * @return busiest hour in a year
     */
    public int busiestHour() {
        int highestValue = 0;
        int hour = -1;
        boolean tied = false;
        for (int i = 0; i < hourCounts.length; i++) {
            if (hourCounts[i] == highestValue) {
                tied = true;
            }
            if (hourCounts[i] > highestValue) {
                highestValue = hourCounts[i];
                hour = i;
                tied = false;
            }
        }
        if (highestValue == 0) {
            System.out.println("No data");
            return hour;
        }
        if (!tied) {
            System.out.println("Hour " + hour + " is the busiest hour with " + highestValue + " uses.");
        } else {
            System.out.println("Hour " + hour + " is tied for the busiest hour with " + highestValue + " uses.");
        }
        return hour;
    }

    // 7.16

    /**
     * Returns the quietest consecutive hour in a year.
     * If there are multiple hours tied for quietest,
     * it says there's a tie and only gives the first.
     *
     * @return quietest hour in a year
     */
    public int quietestHour() {
        int lowestValue = Integer.MAX_VALUE;
        int hour = -1;
        boolean tied = false;
        for (int i = 0; i < hourCounts.length; i++) {
            if (hourCounts[i] == lowestValue) {
                tied = true;
            }
            if (hourCounts[i] < lowestValue) {
                lowestValue = hourCounts[i];
                hour = i;
                tied = false;
            }
        }
        if (lowestValue == Integer.MAX_VALUE) {
            System.out.println("No data");
            return hour;
        }
        if (!tied) {
            System.out.println("Hour " + hour + " is the quietest hour with " + lowestValue + " uses.");
        } else {
            System.out.println("Hour " + hour + " is tied for the quietest hour with " + lowestValue + " uses.");
        }
        return hour;
    }

    // 7.18

    /**
     * Returns the busiest consecutive two hours in a year.
     * If there are multiple hours tied for busiest,
     * it says there's a tie and only gives the first pair.
     *
     * @return busiest hour pair
     * in a
     * year
     */
    public int[] busiestTwoHour() {
        int highestValue = 0;
        int hour = -1;
        boolean tied = false;
        for (int i = 0; i < hourCounts.length; i++) {
            int sum = hourCounts[i] + hourCounts[(i + 1) % 24];
            if (sum == highestValue) {
                tied = true;
            }
            if (sum > highestValue) {
                highestValue = sum;
                hour = i;
                tied = false;
            }
        }
        if (highestValue == 0) {
            System.out.println("No data");
            return null;
        }
        if (!tied) {
            System.out.println("Hours " + hour + " and " + (hour + 1) + " are the busiest hours with " + highestValue + " combined uses.");
        } else {
            System.out.println("Hours " + hour + " and " + (hour + 1) + " are tied for the busiest hours with " + highestValue + " combined uses.");
        }
        return new int[]{hour, hour + 1};
    }


    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData() {
        reader.printData();
    }
}
