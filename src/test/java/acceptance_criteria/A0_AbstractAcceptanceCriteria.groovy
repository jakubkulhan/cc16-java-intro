package acceptance_criteria

import cz.codecamp.logger.PragmaticLoggerInterface
import cz.codecamp.logger.util.TestLoggerFactory
import cz.codecamp.logger.util.TimeProvider
import cz.codecamp.logger.util.TimeSource
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.Month

import static java.util.stream.Collectors.toList

class A0_AbstractAcceptanceCriteria extends Specification {

    public static final LocalDateTime BACK_TO_THE_FUTURE_DATE = LocalDateTime.of(2015, 10, 21, 1, 24);
    public static final LocalDateTime WORLD_WIDE_WEB_LAUNCH_DATE = LocalDateTime.of(1991, Month.AUGUST, 6, 0, 0, 1)

    protected PragmaticLoggerInterface ourAlmightyLogger

    ByteArrayOutputStream mockedSystemOut = new ByteArrayOutputStream()
    ByteArrayOutputStream mockedSystemErr = new ByteArrayOutputStream()

    def setup() {
        System.setErr(new PrintStream(mockedSystemErr))
        System.setOut(new PrintStream(mockedSystemOut))

        ourAlmightyLogger = new TestLoggerFactory().getAlmightyLoggerSolution();
    }

    def cleanup() {
        System.setOut(null)
        System.setErr(null)
        TimeProvider.getInstance().setSource(new TimeSource());
    }


    protected String firstLoggersLogsLastLine() {
        List loggedLines = Files.lines(Paths.get("first.log")).collect(toList());
        lastElementOf(loggedLines)
    }

    protected String secondLoggersLogsLastLine() {
        List lines = mockedSystemOut.toString().split('\n') as List
        lastElementOf(lines)
    }

    protected String thirdLoggersLogsLastLine() {
        List lines = mockedSystemErr.toString().split('\n') as List
        lastElementOf(lines)
    }

    protected String fourthLoggersLogsLastLine() {
        List loggedLines = Files.lines(Paths.get("fourth.log")).collect(toList());
        lastElementOf(loggedLines)

    }

    protected String lastElementOf(List list) {
        def indexOfLastElement = list.size() - 1;
        list.get(indexOfLastElement)
    }
}
