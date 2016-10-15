package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements LoggerInterface, PragmaticLoggerInterface, Closeable {

    private String logFileName;
    private PrintStream fileStream;

    final DateTimeFormatter formatterLog = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm"); // pozor na velikost pismen
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // pozor na velikost pismen

    public FileLogger() {
        this.logFileName = generateLogFileName();
        generateFileStream();
    }

    @Override
    public void log(LogLevelEnum level, String message, int minLogLevel) {
        System.out.println("Volano z: " +  Thread.currentThread().getStackTrace()[1].getClassName() + ": " + Thread.currentThread().getStackTrace()[1].getLineNumber());
        System.out.println("Volano z: " +  Thread.currentThread().getStackTrace()[2].getClassName() + ": " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        if (!generateLogFileName().equals(this.logFileName)){
            this.logFileName = generateLogFileName();
            generateFileStream();
            System.out.println("Vygenerovan novy log file.");
        }

        if (minLogLevel == 1) { printLog(level, message); }
        else if (minLogLevel == 2 && level != LogLevelEnum.DEBUG) { printLog(level, message); }
        else if (minLogLevel == 3 && level != LogLevelEnum.DEBUG && level != LogLevelEnum.INFO) { printLog(level, message); }
        else if (minLogLevel == 4 && level != LogLevelEnum.DEBUG && level != LogLevelEnum.INFO && level != LogLevelEnum.WARNING) { printLog(level, message); }
    }

    @Override
    public void close() throws IOException {
        if (fileStream != null) {
            fileStream.flush();
            fileStream.close();
        }
    }

    public String generateLogFileName() {
        String nazev = "log " + LocalDateTime.now().format(formatterLog) + ".log";
        return nazev;
    }

    public void generateFileStream(){
        try {
            this.fileStream = new PrintStream(
                    new FileOutputStream(
                            new File(this.logFileName), true
                    )
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean printLog(LogLevelEnum level, String message){
        // format: [INFO] [2016-10-05 16:34:00] message
        fileStream.printf("[" + level.name() + "] [" + LocalDateTime.now().format(formatter) + "] " + message + "%n");
        System.out.println("Zalogovano do souboru: " + this.logFileName);
        return true;
    }

    public DateTimeFormatter getFormatterLog() {
        return formatterLog;
    }

    public PrintStream getFileStream() {
        return fileStream;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }
}
