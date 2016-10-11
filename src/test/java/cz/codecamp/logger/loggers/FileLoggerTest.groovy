package cz.codecamp.logger.loggers

import cz.codecamp.logger.LogLevelEnum
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

import static java.util.stream.Collectors.toList

/**
 * Remaining issues:
 * isolation is broken,
 * maybe test possible troubles that rise from not closing the print stream.
 */
class FileLoggerTest extends Specification {

    def "FileLogger should log messages to 'application.log'"() {
        given: "the message is logged to 'application.log' file by FileLogger"
        new FileLogger().log(LogLevelEnum.INFO, "Movie popcorn costs more per ounce than filet mignon.")
        when: "lines of the 'application.log' are retrieved"
        List loggedLines = Files.lines(Paths.get("application.log")).collect(toList());
        then: "the last line contains the message as well as selected log level"
        lastElementOf(loggedLines) == "[INFO]: Movie popcorn costs more per ounce than filet mignon."
    }

    String lastElementOf(List list) {
        def indexOfLastElement = list.size() - 1;
        list.get(indexOfLastElement)
    }
}
