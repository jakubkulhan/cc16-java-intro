package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;

import java.io.*;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

/**
 * Created by filip on 10/10/16.
 */
public class StdoutLoggerTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    //@org.junit.Test
    public static void main(String args[]) {

    }

    @org.junit.Test
    public void log() throws Exception {
        System.out.println("StdoutLogger test start");
        PrintStream stdout = System.out;
        StringBuilder sb = new StringBuilder();
        OutputStream os = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                sb.append((char) b);
            }

            @Override
            public String toString() {
                return sb.toString();
            }
        };
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        StdoutLogger printStreamLogger = new StdoutLogger();
        printStreamLogger.log(LogLevelEnum.ERROR, "logged sentence");
        assertThat(os.toString(), both(containsString("logged sentence")).and(containsString("ERROR")));
        System.setOut(stdout);
        System.out.println("StdoutLogger test end");
    }

    @org.junit.Test
    public void debug() throws Exception {

    }

    @org.junit.Test
    public void info() throws Exception {

    }

    @org.junit.Test
    public void warning() throws Exception {

    }

    @org.junit.Test
    public void error() throws Exception {

    }

}