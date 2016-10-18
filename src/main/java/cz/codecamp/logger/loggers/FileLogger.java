package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.*;

public class FileLogger implements LoggerInterface {

    LogLevelEnum level;
    PrintStream fileLogger;
    String message;
    String nameOfFile;

    public FileLogger(String nameOfFile) throws FileNotFoundException {
        this.nameOfFile = nameOfFile;
        fileLogger = new PrintStream(new FileOutputStream(new File(nameOfFile),true));
    }

    public void setLogger(LogLevelEnum level, String message, String nameOfFile){
        this.level = level;
        this.message = message;
        this.nameOfFile = nameOfFile;
    }

    public LogLevelEnum getLevel(){
        return level;
    }
    public String getMessage(){
        return message;
    }

    public void closeStream() throws IOException {
        if(fileLogger != null) {
            fileLogger.flush();
            fileLogger.close();
        }
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        fileLogger.printf("%s %s\n", "[" + level.name() + "]", message);
        try {
            closeStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
