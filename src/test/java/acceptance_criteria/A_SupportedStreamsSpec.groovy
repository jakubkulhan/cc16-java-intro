package acceptance_criteria

class A_SupportedStreamsSpec extends A0_AbstractAcceptanceCriteria {

    def "Logger is able to log to a file"() {
        when: "Logger logs message"
        ourAlmightyLogger.warning("Movie popcorn costs more per ounce than filet mignon.")
        then: "The last line of the 'application.log' contains the message as well as selected log level"
        firstLoggersLogsLastLine().contains("WARNING")
        firstLoggersLogsLastLine().contains("popcorn")
    }

    def "Logger is able to log to System.out"() {
        when:
        ourAlmightyLogger.warning("Doctors' sloppy handwriting kills more than 7,000 people annually in the United States.")
        then:
        secondLoggersLogsLastLine().contains("WARNING")
        secondLoggersLogsLastLine().contains("sloppy")
    }

    def "Logger is able to log to System.err"() {
        when:
        ourAlmightyLogger.warning("More people are bitten each year by New Yorkers than by sharks.")
        then:
        thirdLoggersLogsLastLine().contains("WARNING")
        thirdLoggersLogsLastLine().contains("sharks")
    }
}
