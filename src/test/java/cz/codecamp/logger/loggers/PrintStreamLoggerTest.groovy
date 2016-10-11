package cz.codecamp.logger.loggers

import cz.codecamp.logger.LogLevelEnum
import spock.lang.Specification

/**
 * Remaining issues:
 * Improve phrasing.
 * Stream closing.
 */
class PrintStreamLoggerTest extends Specification {

    def "Trying to create PrintStreamLogger with null as a PrintStream is rejected"() {
        when:
        new PrintStreamLogger(null).log(LogLevelEnum.DEBUG, "A typical cloud weighs around 1.1 million pounds.")
        then:
        thrown(IllegalArgumentException)
    }

    def "PrintStreamLogger should log the message to the specified PrintStream"() {
        given:
        def mockStream = new ByteArrayOutputStream()
        when:
        new PrintStreamLogger(new PrintStream(mockStream)).log(LogLevelEnum.INFO, 'Reed Hasting started Netflix after receiving $40 in late fees when returning Apollo 13.')
        then:
        mockStream.toString() == '[INFO]: Reed Hasting started Netflix after receiving $40 in late fees when returning Apollo 13.\n'
    }
}
