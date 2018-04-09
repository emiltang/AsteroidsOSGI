/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package dk.sdu.mmmi.cbse.library;

import dk.sdu.mmmi.cbse.api.IMoveAbility;

public class MoveAbility implements IMoveAbility {

    private final float acceleration;
    private final float deceleration;
    private final float maxSpeed;
    private final float rotationSpeed;

    private float dx;
    private float dy;
    private boolean turnLeft;
    private boolean turnRight;
    private boolean moveForward;

    public MoveAbility(float acceleration,
            float deceleration,
            float maxSpeed,
            float rotationSpeed) {
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.maxSpeed = maxSpeed;
        this.rotationSpeed = rotationSpeed;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public boolean isTurnLeft() {
        return turnLeft;
    }

    public void setTurnLeft(boolean turnLeft) {
        this.turnLeft = turnLeft;
    }

    public boolean isTurnRight() {
        return turnRight;
    }

    public void setTurnRight(boolean turnRight) {
        this.turnRight = turnRight;
    }

    public boolean isMoveForward() {
        return moveForward;
    }

    public void setMoveForward(boolean moveForward) {
        this.moveForward = moveForward;
    }
}
