/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package dk.sdu.mmmi.cbse.library;

import dk.sdu.mmmi.cbse.api.IMoveAbility;
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
    public void process(float dt) {
        for (IMoveAble m : world.getEntities(IMoveAble.class)) {

            IMoveAbility mA = m.getMoveAbility();

            // TL;DR: stuff moves
            if (mA.isTurnLeft()) {
                m.setRotation(m.getRotation() + mA.getRotationSpeed() * dt);
            }
            if (mA.isTurnRight()) {
                m.setRotation(m.getRotation() - mA.getRotationSpeed() * dt);
            }

            if (mA.isMoveForward()) {
                mA.setDx((float) (mA.getDx() + cos(m.getRotation()) * mA.getAcceleration() * dt));
                mA.setDy((float) (mA.getDy() + sin(m.getRotation()) * mA.getAcceleration() * dt));
            }
            float v = (float) sqrt(mA.getDx() * mA.getDx() + mA.getDy() * mA.getDy());
            if (v > 0) {
                mA.setDx(mA.getDx() - (mA.getDx() / v) * mA.getDeceleration() * dt);
                mA.setDy(mA.getDy() - (mA.getDy() / v) * mA.getDeceleration() * dt);
            }
            if (v > mA.getMaxSpeed()) {
                mA.setDx((mA.getDx() / v) * mA.getMaxSpeed());
                mA.setDy((mA.getDy() / v) * mA.getMaxSpeed());
            }

            m.setX(m.getX() + mA.getDx() * dt);
            if (m.getX() > IWorld.WIDTH) {
                m.setX(0);
            } else if (m.getX() < 0) {
                m.setX(IWorld.WIDTH);
            }

            m.setY(m.getY() + mA.getDy() * dt);
            if (m.getY() > IWorld.HEIGHT) {
                m.setY(0);
            } else if (m.getY() < 0) {
                m.setY(IWorld.HEIGHT);
            }
        }
    }
}
