package cz.codecamp.logger;

import cz.codecamp.logger.formatters.CompleteFormatterTest;
import cz.codecamp.logger.formatters.DateFormatterTest;
import cz.codecamp.logger.formatters.NoDateFormatterTest;
import cz.codecamp.logger.loggers.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by lenka.salacova on 10/16/2016.
 */
public class TestRunner {
    public static void main (String[] args) {

        Result result = JUnitCore.runClasses(NoDateFormatterTest.class, DateFormatterTest.class, CompleteFormatterTest.class,
                StdoutLoggerTest.class, PrintSteamLoggerLevelComparisonTest.class, MultiLoggerTest.class, DayLoggerTest.class, FileLoggerTest.class);

        for (Failure failure : result.getFailures()){

            System.out.println(failure.toString());

        }

        System.out.println(result.wasSuccessful());

    }
}
