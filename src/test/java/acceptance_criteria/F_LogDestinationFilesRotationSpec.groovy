package acceptance_criteria

import cz.codecamp.logger.util.TimeProvider
import cz.codecamp.logger.util.TimeSource

import java.nio.file.Files
import java.nio.file.Paths

import static java.util.stream.Collectors.toList

class F_LogDestinationFilesRotationSpec extends A0_AbstractAcceptanceCriteria {

    def "Any FileLogger is able to rotate log destination files according to specified time rules"() {
        when:
        TimeSource mock1 = Mock(TimeSource)
        TimeSource mock2 = Mock(TimeSource)
        mock1.getTime() >> WORLD_WIDE_WEB_LAUNCH_DATE
        mock2.getTime() >> WORLD_WIDE_WEB_LAUNCH_DATE.plusDays(1)
        TimeProvider.getInstance().setSource(mock1)
        ourAlmightyLogger.error("5 minutes left")
        TimeProvider.getInstance().setSource(mock2)
        ourAlmightyLogger.error("Where did all my refactoring go?")
        then:
        lastElementOf(Files.lines(Paths.get('fourth.log.91-08-06')).collect(toList())).contains("5 minutes")
        lastElementOf(Files.lines(Paths.get('fourth.log.91-08-07')).collect(toList())).contains("refactoring")
        fourthLoggersLogsLastLine().contains("refactoring")
    }
}
