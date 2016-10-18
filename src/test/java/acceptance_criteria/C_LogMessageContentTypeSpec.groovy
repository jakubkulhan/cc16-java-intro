package acceptance_criteria

class C_LogMessageContentTypeSpec extends A0_AbstractAcceptanceCriteria {

    def "Logger is able to write out log messages as json"() {
        when:
        ourAlmightyLogger.error("When hippos are upset, their sweat turns red.")
        then:
        secondLoggersLogsLastLine() == '{"severity":"ERROR","message":"When hippos are upset, their sweat turns red."}'
    }

    def "Logger is able to write out log messages as plain lines"() {
        when:
        ourAlmightyLogger.error("Human saliva has a boiling point three times that of regular water.")
        then:
        thirdLoggersLogsLastLine() == "[ERROR]: Human saliva has a boiling point three times that of regular water."
    }
}
