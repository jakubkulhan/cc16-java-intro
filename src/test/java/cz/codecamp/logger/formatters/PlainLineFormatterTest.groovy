package cz.codecamp.logger.formatters

import cz.codecamp.logger.LogLevelEnum
import cz.codecamp.logger.LogMessage
import spock.lang.Specification


class PlainLineFormatterTest extends Specification {

    def test() {
        given:
        def formatter = new PlainLineFormatter()
        def message = LogMessage.basicLogMessage(LogLevelEnum.ERROR, "Pants not found.")
        expect:
        formatter.makePrintable(message) == "[ERROR]: Pants not found.\n"
    }
}
