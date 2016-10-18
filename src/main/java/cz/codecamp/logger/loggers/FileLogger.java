package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.RotationRule;
import cz.codecamp.logger.utils.DateUtils;
import cz.codecamp.logger.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by honzapua on 9.10.2016.
 */

public class FileLogger extends PrintStreamLogger {
    private Calendar lastWritten; //kdy naposledy doslo k zapisu do file
    private final String fileName;
    private final String fileExt;
    private File currentLogFile;
    private final RotationRule rotationRule;

    public FileLogger(String fileName, FormatterInterface formater, LogLevelEnum threshold, RotationRule rotationRule) throws FileNotFoundException {
        super(null, formater, threshold);

        this.rotationRule = rotationRule;
        this.fileName = FileUtils.extractFileName(fileName);
        String ext = FileUtils.extractFileExtension(fileName);
        this.fileExt = ext != null ? String.format(".%s", ext) : "";

        setUpLogStream();
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if (rotationRule.shouldRotate(lastWritten.getTime())) {
            PrintStream stream = getStream();
            setStream(null);
            stream.flush();
            stream.close();
            //vytvori filename
            String historicFileName = FileUtils.createTimeStampedFileName(fileName, fileExt, lastWritten.getTime());
            //prejmenovani
            currentLogFile.renameTo(new File(historicFileName));

            try {
                setUpLogStream();
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.err);
            }
        }

        super.log(level, message);
    }

    private void setUpLogStream() throws FileNotFoundException {
        this.currentLogFile = new File(String.format("%s%s", fileName, fileExt));
        this.lastWritten = DateUtils.currentHour();
        setStream(new PrintStream(new FileOutputStream(currentLogFile, true)));
    }

}


