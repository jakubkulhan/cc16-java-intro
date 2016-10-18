package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;
import java.io.*;
/**
 * Created by dmachace on 18.10.2016.
 */
public class LoggerTest {
    FileHandler fileHandler = new FileHandler();
    File file = new File(fileHandler.getFileName());

    @Before
    public void setUp() throws Exception {

        file.delete();
        assertTrue(file.length()==0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testLogger() throws Exception {
        Logger logger = new Logger();

        logger.info("record1");
        assertTrue(getLastLine().length()>0);

        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "record1");
        logger.error("record2");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "record2");
        logger.debug("record3");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "record3");
        logger.warning("record4");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "record4");


        logger.info("record5", true);
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "record5");
        logger.error("record6", true);
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "record6");
        logger.debug("record7", true);
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "record7");
        logger.warning("record8", true);
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "record8");

        logger.info("recordA", false);
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordA");
        logger.error("recordB", false);
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordB");
        logger.debug("recordC", false);
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordC");
        logger.warning("recordD", false);
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordD");

        logger.log(LogLevelEnum.DEBUG, "recordE");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordE");
        logger.log(LogLevelEnum.ERROR, "recordF");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordF");
        logger.log(LogLevelEnum.WARNING, "recordG");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordG");
        logger.log(LogLevelEnum.INFO, "recordH");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordH");

        logger.log("i", "recordI");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordI");
        logger.log("e", "recordJ");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordJ");
        logger.log("d", "recordK");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordK");
        logger.log("w", "recordL");
        assertEquals(getLastLine().substring(getLastLine().length()-7, getLastLine().length()), "recordL");

        logger.system("systemRecord");
        assertEquals(getLastLineSystem().substring(getLastLineSystem().length()-12, getLastLineSystem().length()), "systemRecord");
    }

    private String getLastLine(){
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(fileHandler.getFileName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String last = "";
        String line;

        try {
            while ((line = input.readLine()) != null) {
                last = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return last;
    }

    private String getLastLineSystem(){
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(fileHandler.getSysLogFilePath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String last = "";
        String line;

        try {
            while ((line = input.readLine()) != null) {
                last = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return last;

    }


}