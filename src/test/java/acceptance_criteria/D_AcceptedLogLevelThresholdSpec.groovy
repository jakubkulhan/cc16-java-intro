package acceptance_criteria

class D_AcceptedLogLevelThresholdSpec extends A0_AbstractAcceptanceCriteria {

    def "Logger is able to filter out log messages that are below specified log level threshold."() {
        when:
        ourAlmightyLogger.debug("Your typical user's passphrase in plain text.")
        then:
        firstLoggersLogsLastLine().contains("passphrase")
        !secondLoggersLogsLastLine().contains("passphrase")
        !thirdLoggersLogsLastLine().contains("passphrase")

        when:
        ourAlmightyLogger.info("Your typical user's passphrase in MD5.")
        then:
        firstLoggersLogsLastLine().contains("MD5")
        secondLoggersLogsLastLine().contains("MD5")
        !thirdLoggersLogsLastLine().contains("MD5")
    }
}
