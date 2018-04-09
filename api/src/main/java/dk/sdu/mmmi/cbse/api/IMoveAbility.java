package dk.sdu.mmmi.cbse.api;

/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */



public interface IMoveAbility {
    float getRotationSpeed();

    float getMaxSpeed();

    float getDeceleration();

    float getAcceleration();

    float getDx();

    void setDx(float dx);

    float getDy();

    void setDy(float dy);

    boolean isTurnLeft();

    void setTurnLeft(boolean turnLeft);

    boolean isTurnRight();

    void setTurnRight(boolean turnRight);

    boolean isMoveForward();

    void setMoveForward(boolean moveForward);
}
