package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

/**
 * Created by Lenovo on 17.10.2016.
 * Class
 */
public class FileRotationLogger implements LoggerInterface{
    private LocalDateTime nextCheckTime;
    private LocalDateTime actual;
    private StringFormatter stringFormatter;
    private PrintStream ps;
    private Period period;

    /**
     * During program run period does not change, so I created inner class Period for easier counting of time
     * when stream should write to new log file
     */
    private class Period {
        int days;
        int hours;
        int minutes;
        public Period(int days, int hours, int minutes) {
            this.days = days;
            this.hours = hours;
            this.minutes = minutes;
        }
        public String toString() {
            return "rotate period is : "+days+"d "+hours+"h "+minutes+"m";
        }
        /**
         * http://blog.thingsdesigner.com/uploads/id/treeswing.jpg
         * gets as @param date to that you want add period time
         * @returns nextCheckTime = date + period, that means time when new PrintStream should be set up
         */
        public LocalDateTime addPeriod(LocalDateTime date) {
            return date.plusDays(days).plusHours(hours).plusMinutes(minutes);
        }
    }

    /**
     * Generates String name for log file starting from @date
     * @param date you want to convert to filename
     * @return String name of log file, in form "ddMMyyyyHHmm"
     */
    public String setFileName(LocalDateTime date) {
        return String.valueOf(date.getDayOfMonth())+String.valueOf(date.getMonthValue())+String.valueOf(date.getYear())+String.valueOf(date.getHour())+String.valueOf(date.getMinute())+".log";
    }

    /**
     * Constructor accepts desired file rotating period
     * @param days
     * @param hours
     * @param minutes
     * For first time sets @nextCheckTime variable that defines when should PrintStream to current file end and PrintStream to new file created.
     *
     */
    public FileRotationLogger(int days, int hours, int minutes) {
        actual = LocalDateTime.now();
        //System.out.println("actual : "+actual.toString());
        period = new Period(days, hours, minutes);
        //System.out.println("period : "+period.toString());
        nextCheckTime = period.addPeriod(actual);
        //System.out.println("next check time : "+nextCheckTime.toString());
        stringFormatter = new StringFormatter(true);
        setPrintStream(setFileName(actual));
    }

    /**
     * Closes old and opens new PrintStream
     * @param fileName updated name of log file, in form "ddMMyyyyHHmm"
     */
    public void setPrintStream(String fileName) {
        if(ps != null) {
            ps.flush();
            ps.close();
        }
        try {
            this.ps = new PrintStream(new FileOutputStream(new File(fileName), true));
            System.out.println("new filestream : "+fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method compares current system time and time when should stream write to new file
     * @return true if stream is still
     */
    public boolean checkPrintStream() {
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(nextCheckTime)) {
            nextCheckTime = period.addPeriod(nextCheckTime);
            System.out.println("new check time : "+nextCheckTime.toString());
            setPrintStream(setFileName(nextCheckTime));
            return false;
        }
        return true;
    }

    /**
     * Implementation of log(...) method from LoggerInterface, checks if stream is actual and prints log message to current file
     * @param level logging level from logger.LogLevelEnum
     * @param message log message
     */
    @Override
    public void log(LogLevelEnum level, String message) {
        checkPrintStream();
        ps.printf("%s\n", stringFormatter.format(level, message));
        //System.out.println("i am here: " + new Exception().getStackTrace()[0]);
    }
}
