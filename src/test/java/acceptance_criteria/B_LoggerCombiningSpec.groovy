package acceptance_criteria

class B_LoggerCombiningSpec extends A0_AbstractAcceptanceCriteria {

    def "Logger is able to log into multiple destinations at the same time"() {
        when:
        ourAlmightyLogger.warning("If you lift a kangaroo’s tail off the ground it can’t hop.")
        then:

        firstLoggersLogsLastLine().contains("WARNING")
        firstLoggersLogsLastLine().contains("kangaroo")

        secondLoggersLogsLastLine().contains("WARNING")
        secondLoggersLogsLastLine().contains("kangaroo")

        thirdLoggersLogsLastLine().contains("WARNING")
        thirdLoggersLogsLastLine().contains("kangaroo")

    }
}
