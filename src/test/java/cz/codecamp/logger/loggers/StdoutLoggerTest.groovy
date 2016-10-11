package cz.codecamp.logger.loggers

import cz.codecamp.logger.LogLevelEnum
import spock.lang.Specification

/**
 * Remaining issues:
 * StdoutLogger is useless as a standalone class and probably will be removed.
 */
class StdoutLoggerTest extends Specification {

    ByteArrayOutputStream mockedSystemOut = new ByteArrayOutputStream()

    def setup() {
        System.setOut(new PrintStream(mockedSystemOut))
    }

    def cleanup() {
        System.setOut(null)
    }

    def "StdoutLogger logs messages to System.out"() {
        when:
        new StdoutLogger().log(LogLevelEnum.WARNING, "Doctors' sloppy handwriting kills more than 7,000 people annually in the United States.")
        then:
        mockedSystemOut.toString() == "[WARNING]: Doctors' sloppy handwriting kills more than 7,000 people annually in the United States.\n"
    }
}
