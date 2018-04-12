/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package dk.sdu.mmmi.cbse.library;

import dk.sdu.mmmi.cbse.api.IMoveAble;
import dk.sdu.mmmi.cbse.api.IProcessor;
import dk.sdu.mmmi.cbse.api.IWorld;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import static java.lang.Math.*;

/**
 * @author Emil
 */
@Component(service = IProcessor.class)
public class MoveProcessor implements IProcessor {

    @Reference
    private IWorld world;

    @Override
    public void process(float deltaTime) {
        for (var moveAble : world.getEntities(IMoveAble.class)) {

            var moveAbility = moveAble.getMoveAbility();

            // TL;DR: stuff moves
            if (moveAbility.isTurnLeft())
                moveAble.addRotation(moveAbility.getRotationSpeed() * deltaTime);

            if (moveAbility.isTurnRight())
                moveAble.subtractRotation(moveAbility.getRotationSpeed() * deltaTime);

            if (moveAbility.isMoveForward()) {
                moveAbility.translateDx((float) (cos(moveAble.getRotation()) * moveAbility.getAcceleration() * deltaTime));
                moveAbility.translateDy((float) (sin(moveAble.getRotation()) * moveAbility.getAcceleration() * deltaTime));
            }
            var velocity = (float) sqrt(moveAbility.getDx() * moveAbility.getDx() + moveAbility.getDy() * moveAbility.getDy());
            if (velocity > 0) {
                moveAbility.translateDx(-(moveAbility.getDx() / velocity) * moveAbility.getDeceleration() * deltaTime);
                moveAbility.translateDy(-(moveAbility.getDy() / velocity) * moveAbility.getDeceleration() * deltaTime);
            }
            if (velocity > moveAbility.getMaxSpeed()) {
                moveAbility.setDx((moveAbility.getDx() / velocity) * moveAbility.getMaxSpeed());
                moveAbility.setDy((moveAbility.getDy() / velocity) * moveAbility.getMaxSpeed());
            }

            moveAble.translateX(moveAbility.getDx() * deltaTime);
            if (moveAble.getX() > IWorld.WIDTH) {
                moveAble.setX(0);
            } else if (moveAble.getX() < 0) {
                moveAble.setX(IWorld.WIDTH);
            }

            moveAble.translateY(moveAbility.getDy() * deltaTime);
            if (moveAble.getY() > IWorld.HEIGHT) {
                moveAble.setY(0);
            } else if (moveAble.getY() < 0) {
                moveAble.setY(IWorld.HEIGHT);
            }
        }
    }
}
