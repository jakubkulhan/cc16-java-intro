package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.io.PrintStream;

/**
 * Created by honzapua on 9.10.2016.
 */

/* tohle FilleLooger Stout logger meaji spolecny kod zde. Kdyz mam neco zobecnit musim identifikovat to co maji spolecne

    presunuti spolecne logiky do teto nove tridy. Presunuti implementaci interfacu

    uprava puvodnich trid na vyuzivani nove spolecne implementace (api, protokol se nemeni)
        pridani konstruktoru, odebrani metod.

    otazka zabezbeceni muze se stat, ze kvuli sobe. Bude package private. Nezmenili jsme protokol.


    */
class PrintStreamLogger implements LoggerInterface, PragmaticLoggerInterface { /* smazali jsme public je to private package */
    private final PrintStream stream;

    //implementace Pragmatic loggeru do interfacu. DOhledaji se PREDCI vsech loggeru. Kde to ma smysl implementovat.

    public PrintStreamLogger(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void log(LogLevelEnum level, String message) { //log je implementace interfacu
        stream.printf("[%s] %s%n", level.name(), message);
    }


}
