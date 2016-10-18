package acceptance_criteria

class E2_InvokersCodeLocationLogMessageDetailSpec extends A0_AbstractAcceptanceCriteria {

    def "Logger is able to display the code location of the class which triggered the log event."() {
        when:
        ourAlmightyLogger.error("I am the one who knocks.")
        then:
        fourthLoggersLogsLastLine().contains('E2_InvokersCodeLocationLogMessageDetailSpec')
    }
}
