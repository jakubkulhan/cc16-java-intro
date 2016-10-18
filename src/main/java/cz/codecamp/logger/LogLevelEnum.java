package cz.codecamp.logger;

public enum LogLevelEnum {
    DEBUG(1),
    INFO(10),
    WARNING(100),
    ERROR(1000);

    private final int severity;

    LogLevelEnum(int severity) {
        this.severity = severity;
    }

    public int getSeverity() {
        return severity;
    }
}
