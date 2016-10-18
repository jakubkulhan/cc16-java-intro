package cz.codecamp.logger;

public enum LogLevelEnum{
    DEBUG (0),
    INFO (1),
    WARNING (2),
    ERROR (3);

    private int levelNumber;

    LogLevelEnum(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getlevelNumber() {
        return levelNumber;
    }

    public boolean isHigherOrEqual(LogLevelEnum other) {
        return levelNumber >= other.getlevelNumber();
    }
}
