package cz.codecamp.logger.fileHandlers;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

/**
 * Created by Filip Ocelka on 13.10.2016, filip.ocelka@gmail.com
 */
public abstract class FileHandler {

    protected String fileName;
    protected String logsFolder = "./logs";
    protected LocalDateTime lastLogFileDateTime;
    protected DateTimeFormatter formatter;

    public FileHandler(String fileName, DateTimeFormatter formatter) {
        this.fileName = fileName;
        this.formatter = formatter;
        lastLogFileDateTime = LocalDateTime.MIN;
    }

    public void setLogsFolder(String logsFolder) {
        this.logsFolder = logsFolder;
    }

    public String getLogsFolder() {
        return logsFolder;
    }

    protected String getFileNameWithDateTimeTag(String fileName) {
        int indexOfLastDot = fileName.lastIndexOf('.');
        return fileName.substring(0,indexOfLastDot) + "." + formatter.format(LocalDateTime.now()) + fileName.substring(indexOfLastDot);
    }

    protected LocalDateTime getDateTimeFromFileName(String fileName) {
        int indexOfFirstDot = fileName.indexOf('.');
        int indexOfLastDot = fileName.lastIndexOf('.');
        String dateTimeString = fileName.substring(indexOfFirstDot+1, indexOfLastDot);
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    private boolean checkPathExists(String path) {
        File f = new File(path);
        if (f.exists() && f.isDirectory()) {
            return true;
        }
        return false;
    }

    private void createLogDirectory() {
        File f = new File(logsFolder);
        try {
            f.mkdir();
        }
        catch (SecurityException se) {
            se.printStackTrace();
        }
    }

    private File findLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {

            LocalDateTime date1 = getDateTimeFromFileName(lastModifiedFile.getName());
            LocalDateTime date2 = getDateTimeFromFileName(files[i].getName());

            if (date1.isBefore(date2)) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    protected File getLatestLogFile() {
        if (!checkPathExists(logsFolder)) {
            createLogDirectory();
            return null;
        }
        return findLatestFileFromDir(logsFolder);
    }

    public abstract boolean hasLogFileLifeExpired();

    public File getLogFile() {
        File logFile = getLatestLogFile();

        if (logFile == null) {
            logFile = new File(logsFolder + File.separator + getFileNameWithDateTimeTag(fileName));
        }

        LocalDateTime fileDate = getDateTimeFromFileName(logFile.getName());
        lastLogFileDateTime = fileDate;

        if (hasLogFileLifeExpired()) {
            logFile = new File(logsFolder + File.separator + getFileNameWithDateTimeTag(fileName));
            lastLogFileDateTime = getDateTimeFromFileName(logFile.getName());
        }

        return logFile;
    }
}

