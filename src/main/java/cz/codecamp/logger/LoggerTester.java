package cz.codecamp.logger;

import cz.codecamp.logger.loggers.FileLogger;
import cz.codecamp.logger.loggers.ImperativeMultiLogger;
import cz.codecamp.logger.loggers.StdoutLogger;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoggerTester {

    private static final Map<String, LogLevelEnum> LEVEL_MAP;
    /*staticky inicializacni blok - pousti se prave jednou kdyz se inicializuje trida
        definice tridy, inicializace, pripadne instanciace
        definice-rozparsovani bytecode a registrace do virtualni masiny
        inicializace-spusteni statickych inicializacnich bloku v poradi jak jsou v bytecodu, kdyz jich je vice. je nepovinna
        instanciace- ne vsechny tridy, napr public abstract class InputStream

        static final - CONST

        program rozlisuje nekolik 4 typy logovaci hlasek, ovlada se z prikazove radky. Jejich spolecny format je pismenko prikazu a prave jeden parametr.
        1pismeny prikaz odpovida t

 */


    static { //staticky inicializacni kod
        Map<String, LogLevelEnum> levelMap = new HashMap<>(); //hash mapa klic, hodnota
        levelMap.put("d", LogLevelEnum.DEBUG);
        levelMap.put("i", LogLevelEnum.INFO);
        levelMap.put("w", LogLevelEnum.WARNING);
        levelMap.put("e", LogLevelEnum.ERROR);
        LEVEL_MAP = Collections.unmodifiableMap(levelMap);
    }

    public static void main(String[] args) {

        LoggerInterface logger;
        try {
            final FormatterInterface singleFormatter = new SingleFormatter();
            final FormatterInterface timeFormatter = new TimeFormatter();
            // logger zere pole, ktere ma vice policek
            logger = new ImperativeMultiLogger(
                    new FileLogger("a.log", timeFormatter),
                    new FileLogger("b.log", singleFormatter),
                    new StdoutLogger(timeFormatter)
            );

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
            return;
        }

        for (Scanner scanner = new Scanner(System.in); ; ) { //nekonecny cyklus ; ; scanner ma komplexni podminku pro vyskoceni z cycklu,
            System.out.print("> "); /* Scanner parsuje system.in metoda main je rizena klavesnici viz ridici promenna cyklu co Dela.*/

            if (!scanner.hasNextLine()) { // jakmile uzivatel ukonci vstup. Program prerusi ccyklus, to neznamena Ctrl + D
                break;
            }

            String line = scanner.nextLine(); //pres scanner nacetl dal radku
            String[] parts = line.split("\\s+", 2); // rozseka mezerama. Zajimaji ho 2 veci mezi nimiz je mezera.

            //System.err.println(parts.length); //nad ramec at zjistim kolik toho je

            if (parts.length < 2) {
                System.err.println("not enough arguments");
                continue; //vracime se na zacatek cyklu, koncime aktualni iteraci
            }

            LogLevelEnum level = LEVEL_MAP.get(parts[0].substring(0, 1).toLowerCase()); //hleda prvni pismeno v mape zmensi si ho, ale vysledna hodnota je enum
            if (level == null) { /*get vrati null pokud nenajde klic, type safe vycty*/
                System.err.println("unknown level [" + parts[0] + "]");
                continue;
            }

            logger.log(level, parts[1]);
        }

        System.exit(0);
    }

}
