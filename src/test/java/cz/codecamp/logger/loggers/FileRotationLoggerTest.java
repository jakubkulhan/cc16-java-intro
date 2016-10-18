package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 18.10.2016.
 */
public class FileRotationLoggerTest {
    FileRotationLogger frl;
    @Before
    public void setUp() {
        frl = new FileRotationLogger(1,1,1);
    }

    @Test
    public void setFileName() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        assertEquals(frl.setFileName(date), String.valueOf(date.getDayOfMonth())+String.valueOf(date.getMonthValue())+String.valueOf(date.getYear())+String.valueOf(date.getHour())+String.valueOf(date.getMinute())+".log");
    }

    @Test
    public void setPrintStream() throws Exception {

    }

    @Test
    public void checkPrintStream() throws Exception {

    }

    @Test
    public void log() throws Exception {

    }

}