package loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.loggers.FileLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLoggerTest {

    private FileLogger fileLogger;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

    @Before
    public void setup(){
        fileLogger = Mockito.mock(FileLogger.class);
    }

    @Test
    public void testLogMethod(){
        // vubec nevim, jestli to neni naprosta blbost to testovat takhle :-)
        fileLogger.log(LogLevelEnum.INFO, "Test Message");
        Mockito.verify(fileLogger, Mockito.atLeast(1)).log(LogLevelEnum.INFO, "Test Message");
    }

    @Test
    public void testFileCreated(){
        fileLogger = new FileLogger(FileLogger.Time.EVERY10SECONDS);
        File file = new File(df.format(new Date()) + ".log");
        Assert.assertTrue(file.exists());
    }

}
