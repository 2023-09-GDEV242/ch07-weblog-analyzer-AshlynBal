/**
 * Read web server data and analyze hourly access patterns.
 *
 * @author Ashlyn Balicki, David J. Barnes, and Michael Kölling.
 * @version 2023.12.19
 */
public class LogAnalyzer {
    private int[] yearCounts;
    private int[] monthCounts;
    private int[] dayCounts;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] minuteCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer() {
        // Create the array object to hold the access counts.
        // Year = 2000 + i
        // Thank god this program will never be used after 2019
        yearCounts = new int[20];
        monthCounts = new int[12];
        dayCounts = new int[31];
        hourCounts = new int[24];
        minuteCounts = new int[60];
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
        // Create the array object to hold the access counts.
        // Year = 2000 + i
        // Thank god this program will never be used after 2019
        yearCounts = new int[20];
        monthCounts = new int[12];
        dayCounts = new int[31];
        hourCounts = new int[24];
        minuteCounts = new int[60];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    //7.14

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

    //

    /**
     * Analyze the year, month, day, hour, and minute data from the log file.
     */
    public void analyzeTimeData() {
        while (reader.hasNext()) {
            LogEntry entry = reader.next();
            // Year = 2000 + i
            yearCounts[entry.getYear() - 2000]++;
            // January = month 0
            monthCounts[entry.getMonth() - 1]++;
            dayCounts[entry.getDay()]++;
            hourCounts[entry.getHour()]++;
            minuteCounts[entry.getMinute()]++;
        }
    }

    /**
     * Print the hourly counts. These should have been set with a prior call to analyzeHourlyData or analyzeTimeData.
     */
    public void printHourlyCounts() {
        System.out.println("Hr: Count");
        for (int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }

    /**
     * Print the monthly counts. These should have been set with a prior call to analyzeTimeData.
     */
    public void totalAccessesPerMonth() {
        System.out.println("Month: Count");
        for (int i = 0; i < monthCounts.length; i++) {
            System.out.println(i + ": " + monthCounts[i]);
        }
    }

    // 7.15

    /**
     * Returns the busiest hour.
     * If there are multiple hours tied for busiest, it only returns the first.
     * With no data, null is returned.
     *
     * @return busiest hour
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
        return hour;
    }

    // 7.16

    /**
     * Returns the quietest hour.
     * If there are multiple hours tied for quietest, it only gives the first.
     * With no data, null is returned.
     *
     * @return quietest hour
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
        return hour;
    }

    /**
     * Returns the busiest day.
     * If there are multiple days tied for busiest, it only returns the first.
     * With no data, null is returned.
     *
     * @return busiest day
     */
    public int busiestDay() {
        int highestValue = 0;
        int day = -1;
        boolean tied = false;
        for (int i = 0; i < dayCounts.length; i++) {
            if (dayCounts[i] == highestValue) {
                tied = true;
            }
            if (dayCounts[i] > highestValue) {
                highestValue = dayCounts[i];
                day = i;
                tied = false;
            }
        }
        return day;
    }

    // 7.16

    /**
     * Returns the quietest day.
     * If there are multiple days tied for quietest, it only gives the first.
     * With no data, null is returned.
     *
     * @return quietest hour
     */
    public int quietestDay() {
        int lowestValue = Integer.MAX_VALUE;
        int day = -1;
        boolean tied = false;
        for (int i = 0; i < dayCounts.length; i++) {
            if (dayCounts[i] == lowestValue) {
                tied = true;
            }
            if (dayCounts[i] < lowestValue) {
                lowestValue = dayCounts[i];
                day = i;
                tied = false;
            }
        }
        return day;
    }

    /**
     * Returns the busiest month.
     * If there are multiple months tied for busiest, it only returns the first.
     * With no data, null is returned.
     *
     * @return busiest month
     */
    public int busiestMonth() {
        int highestValue = 0;
        int month = -1;
        boolean tied = false;
        for (int i = 0; i < monthCounts.length; i++) {
            if (monthCounts[i] == highestValue) {
                tied = true;
            }
            if (monthCounts[i] > highestValue) {
                highestValue = monthCounts[i];
                month = i;
                tied = false;
            }
        }
        return month;
    }

    // 7.16

    /**
     * Returns the quietest month.
     * If there are multiple months tied for quietest, it only gives the first.
     * With no data, null is returned.
     *
     * @return quietest month
     */
    public int quietestMonth() {
        int lowestValue = Integer.MAX_VALUE;
        int month = -1;
        boolean tied = false;
        for (int i = 0; i < monthCounts.length; i++) {
            if (monthCounts[i] == lowestValue) {
                tied = true;
            }
            if (monthCounts[i] < lowestValue) {
                lowestValue = monthCounts[i];
                month = i;
                tied = false;
            }
        }
        return month;
    }


    // 7.18

    /**
     * Returns the busiest consecutive two hours.
     * If there are multiple hours tied for busiest, it only gives the first pair.
     * With no data, null is returned.
     *
     * @return busiest hour pair
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
            return null;
        }
        return new int[]{hour, hour + 1};
    }

    /**
     * Returns the average amount of data access per month.
     *
     * @return the average amount of data access per month
     */
    public double averageAccessesPerMonth() {
        return (double) numberOfAccesses() / 12;
    }


    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData() {
        reader.printData();
    }
}
