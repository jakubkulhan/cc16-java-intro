package cz.codecamp.logger.loggers;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * Created by jbares on 18.10.2016.
 */

@RunWith(Parameterized.class)
public class MapOfLevelsTest {
    private LogLevelEnum level;
    private Integer expectedLevel;
    public Map<LogLevelEnum,Integer> mapOfLevels;
    private FileLogger logger;

    @Before
    public void init() {
        mapOfLevels = new HashMap<>();
        logger = new FileLogger();
        logger.initMapOfLevels();

    }

    public MapOfLevelsTest(LogLevelEnum level, Integer expectedLevel) {
        this.level = level;
        this.expectedLevel = expectedLevel;
    }

    @Parameterized.Parameters
    public static Collection levels() {
        return Arrays.asList(new Object[][] {
                { LogLevelEnum.DEBUG, 2 },
                { LogLevelEnum.INFO, 1 },
                { LogLevelEnum.WARNING, 3 },
                { LogLevelEnum.ERROR, 4 }
        });
    }

    @Test
    public void testPrimeNumberChecker() {
        System.out.println("Parameterized Level is : " + level);
        assertEquals(expectedLevel, mapOfLevels.get(level));
    }
}