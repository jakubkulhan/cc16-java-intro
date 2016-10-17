package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by lenka.salacova on 10/15/2016.
 */
public class DayLogger extends FileLogger {

    private String plainFileName;
    public DayLogger(String fileName, FormatterInterface formatterInterface) {
        super(fileName, formatterInterface);
        this.plainFileName = fileName;


    }
    @Override
    protected String getFileName(String fileName) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fileName + "_" + LocalDateTime.now().format(dateFormat)+".log";
    }

    @Override
    public void log(LogLevelEnum level, String message) {

        String newFileName = getFileName(plainFileName);
        if (!currentName.equals(newFileName)) {
        createPrintStream(new File(newFileName));
            currentName = newFileName;
        }
        super.log(level, message);

        //this.printStream.printf("[%s]: %s\n", level.name(), message);

    }

}
