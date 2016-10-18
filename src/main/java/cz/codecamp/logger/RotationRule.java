package cz.codecamp.logger;

import java.util.Date;

/**
 * Created by honzapua on 16.10.2016.
 */
public interface RotationRule {

    boolean shouldRotate(Date lastWritten);

}
