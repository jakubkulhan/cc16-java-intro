package cz.codecamp.logger.loggers

import cz.codecamp.logger.LogLevelEnum
import spock.lang.Specification

/**
 * Remaining issues:
 * Improve phrasing.
 *
 */
class MultiLoggerTest extends Specification {

    def mockStreamA = new ByteArrayOutputStream()
    def mockLoggerA = createLoggerMock(mockStreamA)
    def mockStreamB = new ByteArrayOutputStream()
    def mockLoggerB = createLoggerMock(mockStreamB)
    def mockStreamC = new ByteArrayOutputStream()
    def mockLoggerC = createLoggerMock(mockStreamC)

    def "Null is rejected as an input for creating MultiLogger."() {
        when:
        new MultiLogger(null).log(LogLevelEnum.INFO, "Agatha Christie said she came up with many of her book plots while sitting in her bathtub eating apples.")
        then:
        thrown(IllegalArgumentException)
    }

    def "Single PrintStream logger is accepted as an input."() {
        when:
        new MultiLogger(mockLoggerA).log(LogLevelEnum.INFO, "Oxford University is older than the Aztec empire.")
        then:
        mockStreamA.toString().contains("Oxford")
    }

    def "MultiLogger logs the message to every inserted LoggerInterface."() {
        when:
        new MultiLogger(mockLoggerA, mockLoggerB, mockLoggerC).log(LogLevelEnum.ERROR, "More people are bitten each year by New Yorkers than by sharks.")
        then:
        mockStreamA.toString().contains("sharks")
        mockStreamA.toString() == mockStreamB.toString()
        mockStreamB.toString() == mockStreamC.toString()
    }

    def "If the same LoggerInterface is inserted twice, every message is repeated."() {
        when:
        def logger = new MultiLogger(mockLoggerA, mockLoggerA)
        logger.log(LogLevelEnum.DEBUG, "Knock, knock.")
        logger.log(LogLevelEnum.DEBUG, "Who's there?")
        then:
        def loggedLines = splitToLines(mockStreamA.toString());
        loggedLines[0] == loggedLines[1]
        loggedLines[2] == loggedLines[3]
    }

    def splitToLines(String s) {
        s.split('\n')
    }

    def createLoggerMock(ByteArrayOutputStream mockStream) {
        return new PrintStreamLogger(new PrintStream(mockStream))
    }
}
