package cz.codecamp.logger.loggers;

import cz.codecamp.logger.RotationRule;

import java.util.Date;

/**
 * Created by honzapua on 18.10.2016.
 */
class NoRotationRuleMock implements RotationRule {

    @Override
    public boolean shouldRotate(Date lastWritten) {
        return false;
    }
}
