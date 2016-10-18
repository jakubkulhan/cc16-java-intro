package cz.codecamp.logger.loggers;

/**
 * Created by dmachace on 18.10.2016.
 */


import java.io.FileInputStream;
import java.io.IOException;



public enum Properties {

    INSTANCE;

    private java.util.Properties jUtilProperties;
    private static String filePath;

    Properties() {

        this.init(this.getClass().getResource("/config.properties").getFile());
    }

    private void init(String filePath) {
        this.filePath = filePath;
        jUtilProperties = new java.util.Properties();
        try {

            jUtilProperties.load(new FileInputStream(this.filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFileName() {
        return this.jUtilProperties.getProperty("fileName");
    }

    public String getInfoLevel() {
        return this.jUtilProperties.getProperty("info");
    }

    public String getDirectory() {
        return this.jUtilProperties.getProperty("directory");
    }

    public String getDebugLevel() {
        return this.jUtilProperties.getProperty("debug");
    }

    public String getErrorLevel() {
        return this.jUtilProperties.getProperty("error");
    }

    public String getWarningLevel() {
        return  this.jUtilProperties.getProperty("warning");
    }

    public String getDateSuffixFormat() {
        return this.jUtilProperties.getProperty("dateSuffixFormat");
    }

    public String getDateInputFormat() {
        return this.jUtilProperties.getProperty("dateInputFormat");
    }

    public String getLogCallerName() {
        return this.jUtilProperties.getProperty("logCallerName");
    }

    public String getDeleteOldLogs() {
        return this.jUtilProperties.getProperty("deleteOldLogs");
    }

    public long getDeleteOldLogsTime() {
        return  Long.parseLong(this.jUtilProperties.getProperty("deleteOldLogsTime"));
    }

    public String getSystemLog() {
        return this.jUtilProperties.getProperty("systemLog");
    }

    public String getSystemLogFileName() {
        return this.jUtilProperties.getProperty("systemLogFileName");
    }

}