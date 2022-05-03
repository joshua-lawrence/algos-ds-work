package ceStock;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class StocksApp {
    public static void main(String[] args) throws ParseException {
        Locale englishUS = Locale.US;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        var table = readCSV("AMZN.csv", 0, 4);
        System.out.printf("Number of elements: " + table.size() + "\n");
        System.out.printf("A) Oldest closing price: %tD $%.2f.\n", table.min(), table.get(table.min()));
        System.out.printf("B) Newest closing price: %tD $%.2f.\n", table.max(), table.get(table.max()));
        System.out.printf("C) The closing price increased by $%.2f.\n\n", table.get(table.max()) - table.get(table.min()));

        System.out.printf("D) Closing price from 8/31/18: $%.2f.\n", table.get(formatter.parse("2018-08-31")));
        System.out.printf("E) Closing price from 3/10/20: $%.2f.\n", table.get(formatter.parse("2020-03-10")));
        System.out.printf("F) The closing price decreased by $%.2f.\n\n", Math.abs(table.get(formatter.parse("2020-03-10")) - table.get(formatter.parse("2018-08-31"))));

        System.out.printf("G) First available closing price in 2019: %tD $%.2f.\n", table.ceiling(formatter.parse("2019-01-01")), table.get(table.ceiling(formatter.parse("2019-01-01"))));
        System.out.printf("G) Last available closing price in 2020: %tD $%.2f.\n", table.floor(formatter.parse("2020-12-31")), table.get(table.floor(formatter.parse("2020-12-31"))));


    }

    private static ST<Date, Double> readCSV(String fileName, int keyField, int valField)  {
        ST<Date, Double> table = new ST<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        In in = new In(fileName);
        int i = 0;
        while(in.hasNextLine()) {
            String line = "";
            try {
                line = in.readLine();
                if(i != 0) {
                    String[] tokens = line.split(",");
                    Date key = formatter.parse(tokens[keyField]);
                    Double val = Double.parseDouble(tokens[valField]);
                    table.put(key, val);
                }
                i++;
            }
            catch (Exception e) {
                System.out.println(line);
                e.printStackTrace();
            }
        }
        return table;
    }
}
