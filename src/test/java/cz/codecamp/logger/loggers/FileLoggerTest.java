package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.fileHandlers.DayBasedFileHandler;
import cz.codecamp.logger.fileHandlers.IntervalBasedFileHandler;
import cz.codecamp.logger.formatters.LogFormatter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Filip Ocelka on 15.10.2016, filip.ocelka@gmail.com
 */
public class FileLoggerTest {

    private FileLogger logger;
    private LocalDateTime now;
    private DateTimeFormatter dateTimeFormatter;

    @Before
    public void setUp() throws Exception {
        logger = new FileLogger();
        now = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    }

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testOutputOdStdOutLoggerWithUnspecifiedFormatter() throws Exception {
        logger.setLogsFolder(tempFolder.getRoot().toPath().toString());

        logger.log(LogLevelEnum.INFO, "test");
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result

        String actual = getLines(logger.getLogsFolder());
        String expected = getExpectedResult(ste[1]);
        assertEquals(expected, actual);
    }

    @Test
    public void testOutputOdStdOutLoggerWithGivenFormatter() throws Exception {
        logger.setLogsFolder(tempFolder.getRoot().toPath().toString());

        FormatterInterface logFormatter = new LogFormatter();

        logger.log(LogLevelEnum.INFO, "test", logFormatter);
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result

        String actual = getLines(logger.getLogsFolder());
        String expected = getExpectedResult(ste[1]);
        assertEquals(expected, actual);
    }

    @Test
    public void testOutputOdStdOutLoggerWithUnspecifiedFormatterWithDayBasedFileHandler() throws Exception {

        logger = new FileLogger(new DayBasedFileHandler("app.log"));
        logger.setLogsFolder(tempFolder.getRoot().toPath().toString());

        logger.log(LogLevelEnum.INFO, "test");
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result

        String actual = getLines(logger.getLogsFolder());
        String expected = getExpectedResult(ste[1]);
        assertEquals(expected, actual);
    }

    private String getLines(String logsFolder) throws Exception {
        File folder = new File(logsFolder);
        File[] listOfFiles = folder.listFiles();
        Path path = listOfFiles[0].toPath();
        List<String> lines = Files.readAllLines(path);
        return lines.get(0);
    }

    private String getExpectedResult(StackTraceElement ste) {
        return "[INFO] [" + dateTimeFormatter.format(now) + "] test Class: " + ste.getClassName() + " line: " + (ste.getLineNumber()-1);
    }

    @Test
    public void testLogFileRotation() throws Exception {
        logger = new FileLogger(new IntervalBasedFileHandler("app.log", ChronoUnit.SECONDS, 1));
        logger.setLogsFolder(tempFolder.getRoot().toPath().toString());

        logger.log(LogLevelEnum.WARNING, "test random message");

        Thread.sleep(2000);

        logger.log(LogLevelEnum.INFO, "test");
        StackTraceElement[] ste = Thread.currentThread().getStackTrace(); // ! Must be right after calling log method -> to get proper line number in test result

        File folder = new File(logger.getLogsFolder());
        File[] listOfFiles = folder.listFiles();

        File lastModifiedFile = listOfFiles[0];
        for (int i = 1; i < listOfFiles.length; i++) {

            if (listOfFiles[i].lastModified() > lastModifiedFile.lastModified()) {
                lastModifiedFile = listOfFiles[i];
            }
        }

        Path path = lastModifiedFile.toPath();
        List<String> lines = Files.readAllLines(path);

        String actual = lines.get(0);
        String expected = "[INFO] [" + dateTimeFormatter.format(LocalDateTime.now()) + "] test Class: " + ste[1].getClassName() + " line: " + (ste[1].getLineNumber()-1);
        assertEquals(expected, actual);
    }
}