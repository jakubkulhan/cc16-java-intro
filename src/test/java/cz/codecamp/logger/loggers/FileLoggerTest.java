package cz.codecamp.logger.loggers;

import cz.codecamp.logger.CalleeFormatter;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by honzapua on 18.10.2016.
 */
public class FileLoggerTest {

    private File testLogFile;

    @Test
    public void isWritingToFile() throws Exception{
        LoggerInterface logger = new FileLogger(testLogFile.toString(), new CalleeFormatter(), LogLevelEnum.DEBUG,
                new NoRotationRuleMock());
        logger.log(LogLevelEnum.ERROR, "Test Message");
        logger = null;
        System.gc(); //volani garbage collector
        // soubor existuje
        Assert.assertTrue(testLogFile.exists());
        // neco je zapsano
        Assert.assertTrue(testLogFile.length() > 0);
        System.out.println(testLogFile.length());
    }

    @Before
    public void setUp() throws Exception {
        //zjistit docasny adresar
        testLogFile = File.createTempFile("test","log");
    }

    @After
    public void tearDown() throws Exception{
        if (testLogFile.exists()) {
            testLogFile.deleteOnExit();
            testLogFile.delete();
        }
    }

}
