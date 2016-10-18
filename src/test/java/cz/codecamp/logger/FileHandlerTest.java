package cz.codecamp.logger;

import cz.codecamp.logger.fileHandlers.FileHandler;
import cz.codecamp.logger.fileHandlers.IntervalBasedFileHandler;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by Filip Ocelka on 13.10.2016, filip.ocelka@gmail.com
 */
public class FileHandlerTest {

    private FileHandler fileHandler;
    private DateTimeFormatter formatter;
    private LocalDateTime dateTimeNow;

    @Before
    public void setUp() throws Exception {
        fileHandler = new IntervalBasedFileHandler("testLogFile.log", ChronoUnit.DAYS,1);
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        dateTimeNow = LocalDateTime.now();
    }

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void getRightLogFile() throws Exception {
        File tempFile1 = tempFolder.newFile("firstLogFile.12-10-2016-20-57-11.log"); // temporary file -> populates folder
        File tempFile2 = tempFolder.newFile("secondLogFile.11-10-2016-18-57-11.log"); // temporary file -> populates folder
        fileHandler.setLogsFolder(tempFolder.getRoot().toPath().toString());
        File actual = fileHandler.getLogFile();
        File expected = tempFolder.newFile("testLogFile." + formatter.format(dateTimeNow) + ".log");
        assertEquals(expected, actual);
    }

    @Test
    public void createNewDirectory() throws Exception {
        fileHandler.setLogsFolder(tempFolder.getRoot().toPath().toString() + File.separator + "logs");
        File actual = fileHandler.getLogFile();
        File expected = tempFolder.newFile("logs/testLogFile." + formatter.format(dateTimeNow) + ".log");
        assertEquals(expected, actual);
    }

}