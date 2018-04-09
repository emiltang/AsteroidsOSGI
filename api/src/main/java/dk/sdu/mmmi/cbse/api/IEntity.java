package dk.sdu.mmmi.cbse.api;

/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */


/**
 * @author Emil
 */
public interface IEntity {

    /**
     * @return Asset name
     */
    String getAsset();

    /**
     * @return x coordinate in game units
     */
    float getX();

    /**
     * @param x x coordinate in game units
     */
    void setX(float x);

    /**
     * @return y coordinate in game units
     */
    float getY();

    /**
     * @param y y coordinate in game units
     */
    void setY(float y);

    /**
     * @return entity rotation in radians
     */
    float getRotation();

    /**
     * @param rotation entity rotation in radians
     */
    void setRotation(float rotation);

}
