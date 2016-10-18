package cz.codecamp.logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class LogLevelEnumTest {
    @Test
    public void getLevelNumberDebug() {
        assertEquals(0, LogLevelEnum.DEBUG.getlevelNumber());
    }

    @Test
    public void getLevelNumberInfo() {
        assertEquals(1, LogLevelEnum.INFO.getlevelNumber());
    }

    @Test
    public void getLevelNumberWarning() {
        assertEquals(2, LogLevelEnum.WARNING.getlevelNumber());
    }

    @Test
    public void getLevelNumberError() {
        assertEquals(3, LogLevelEnum.ERROR.getlevelNumber());
    }

    @Test
    public void errorIsHigherThanWarning() {
        assertTrue(LogLevelEnum.ERROR.isHigherOrEqual(LogLevelEnum.WARNING));
    }

    @Test
    public void errorIsHigherThanInfo() {
        assertTrue(LogLevelEnum.ERROR.isHigherOrEqual(LogLevelEnum.INFO));
    }

    @Test
    public void errorIsHigherThanDebug() {
        assertTrue(LogLevelEnum.ERROR.isHigherOrEqual(LogLevelEnum.DEBUG));
    }

    @Test
    public void warningIsHigherThanInfo() {
        assertTrue(LogLevelEnum.WARNING.isHigherOrEqual(LogLevelEnum.INFO));
    }

    @Test
    public void warningIsHigherThanThanDebug() {
        assertTrue(LogLevelEnum.WARNING.isHigherOrEqual(LogLevelEnum.DEBUG));
    }

    @Test
    public void infoIsHigherThanDebug() {
        assertTrue(LogLevelEnum.INFO.isHigherOrEqual(LogLevelEnum.DEBUG));
    }
}
