package cz.codecamp.logger.loggers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by honzapua on 18.10.2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ImperativeMultiLoggerTest.class,
        FunctionMultiloggerTest.class
})
public class MultiLoggerTestSuite {
}
