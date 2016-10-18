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
 */
public class FileRollLogger implements LoggerInterface{
    private LocalDateTime nextCheckTime;
    private LocalDateTime actual;
    private StringFormatter stringFormatter;
    private PrintStream ps;
    private Period period;
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
         * gets as @param date
         * @returns date + period
         */
        public LocalDateTime addPeriod(LocalDateTime date) {
            return date.plusDays(days).plusHours(hours).plusMinutes(minutes);
        }
    }
    public String setFileName(LocalDateTime date) {
        return String.valueOf(date.getDayOfMonth())+String.valueOf(date.getMonthValue())+String.valueOf(date.getYear())+String.valueOf(date.getHour())+String.valueOf(date.getMinute())+".log";
    }
    public FileRollLogger(int days, int hours, int minutes) {
        actual = LocalDateTime.now();
        //System.out.println("actual : "+actual.toString());
        period = new Period(days, hours, minutes);
        //System.out.println(period.toString());
        nextCheckTime = period.addPeriod(actual);
        System.out.println("next check time : "+nextCheckTime.toString());
        stringFormatter = new StringFormatter(true);
        setPrintStream(setFileName(actual));
    }

    /**
     *
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

    @Override
    public void log(LogLevelEnum level, String message) {
        checkPrintStream();
        ps.printf("%s\n", stringFormatter.format(level, message));
    }
}
