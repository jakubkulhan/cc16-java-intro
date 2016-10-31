package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements LoggerInterface, Closeable, PragmaticLoggerInterface {

    private PrintStream fileStream;
    private String logFileName;


    public FileLogger() {
        this.logFileName = createLogFileName();
        initMapOfLevels();
        createFileStream();
    }

    public boolean createLog(LogLevelEnum level, String message){
        fileStream.printf("[" + level.name() + "] [" + formatTime.format(new Date()) + "] " + message + "\n");
        System.out.println("Logged in to file: " + this.logFileName);
        return true;
    }

    @Override
    public void log(LogLevelEnum level, String message, int minimumLevel) {
        System.out.println("Called from: " +  Thread.currentThread().getStackTrace()[1].getClassName() + ": " + Thread.currentThread().getStackTrace()[1].getLineNumber());

        if (!createLogFileName().equals(this.logFileName)){
            this.logFileName = createLogFileName();
            createFileStream();
            System.out.println("New log file created.");
        }
        if (mapOfLevels.get(level) >= minimumLevel){
            createLog(level, message);
        }
    }

    public String createLogFileName() {
        String fileName = "log " + format.format(new Date()) + ".log";
        System.out.println("Created new file.");
        return fileName;
    }

    public void initMapOfLevels(){
        mapOfLevels.put(LogLevelEnum.DEBUG,1);
        mapOfLevels.put(LogLevelEnum.INFO,2);
        mapOfLevels.put(LogLevelEnum.WARNING,3);
        mapOfLevels.put(LogLevelEnum.ERROR,4);
    }

    public void createFileStream(){
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

    public PrintStream getFileStream() {
        return fileStream;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    @Override
    public void close() throws IOException {
        if (fileStream != null){
            fileStream.flush();
            fileStream.close();
        }
    }
}
