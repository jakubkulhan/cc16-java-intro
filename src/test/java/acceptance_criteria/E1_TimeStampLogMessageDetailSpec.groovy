package acceptance_criteria

import cz.codecamp.logger.util.TimeProvider
import cz.codecamp.logger.util.TimeSource

import java.time.format.DateTimeFormatter

class E1_TimeStampLogMessageDetailSpec extends A0_AbstractAcceptanceCriteria {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");

    def "Logger is able to also log the time when the message is logged."() {
        given:
        TimeSource movieMomentsTimeSource = Mock(TimeSource)
        movieMomentsTimeSource.getTime() >> BACK_TO_THE_FUTURE_DATE

        TimeSource csTimeSource = Mock(TimeSource)
        csTimeSource.getTime() >> WORLD_WIDE_WEB_LAUNCH_DATE

        when:
        TimeProvider.getInstance().setSource(movieMomentsTimeSource)
        ourAlmightyLogger.error("No, no, no! The plutonium is required to generate the 1.21 jigawatts needed to power the car!")
        then:
        fourthLoggersLogsLastLine().contains("jigawatts")
        fourthLoggersLogsLastLine().contains(BACK_TO_THE_FUTURE_DATE.format(formatter))

        when:
        TimeProvider.getInstance().setSource(csTimeSource)
        ourAlmightyLogger.error("At one point, CERN was toying with patenting the World Wide Web.")
        then:
        fourthLoggersLogsLastLine().contains(WORLD_WIDE_WEB_LAUNCH_DATE.format(formatter))
        fourthLoggersLogsLastLine().contains("CERN")
    }

    def cleanup() {
//        TimeProvider.getInstance().setSource(new TimeSource())
    }
}
