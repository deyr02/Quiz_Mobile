package com.example.cars;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class DurationCalculator {
    static String
    findDifference(String start_date,
                   String end_date)
    {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds

            String diffs ="";

            if(difference_In_Years > 0){
                diffs = diffs + difference_In_Years + " years, ";
            }
            if(difference_In_Days >0){
                diffs = diffs+ difference_In_Days + " days, ";
            }
            if(difference_In_Hours > 0){
                diffs = diffs  + difference_In_Hours + " hours, ";
            }
            if(difference_In_Minutes >0){
                diffs = diffs + + difference_In_Minutes + " minutes, ";
            }
            if(difference_In_Seconds >0 ){
                diffs = diffs + + difference_In_Seconds + " seconds";
            }



            /*String diff =
                    difference_In_Years
                            + " years, "
                            + difference_In_Days
                            + " days, "
                            + difference_In_Hours
                            + " hours, "
                            + difference_In_Minutes
                            + " minutes, "
                            + difference_In_Seconds
                            + " seconds";

             */
            return diffs;
        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
