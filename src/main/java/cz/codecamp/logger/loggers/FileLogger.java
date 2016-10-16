package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.utils.FileUtils;

import java.io.*;
import java.util.Date;

/**
 * Created by honzapua on 9.10.2016.
 */

public class FileLogger extends PrintStreamLogger {
    private Date lastWritten; //kdy naposledy doslo k zapisu do file
    private final String fileName;
    private final String fileExt;
    private File currentLogFile;

    public FileLogger(String fileName, FormatterInterface formater, LogLevelEnum threshold) throws FileNotFoundException {
        super(null, formater, threshold);

        this.fileName = FileUtils.extractFileName(fileName);
        String ext = FileUtils.extractFileExtension(fileName);
        this.fileExt = ext != null ? String.format(".%s", ext) : "";
        this.currentLogFile = new File(fileName);

        setStream(new PrintStream(new FileOutputStream(currentLogFile, true)));
    }

}


