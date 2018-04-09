package dk.sdu.mmmi.cbse.api;

/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */



import java.util.Collection;

public interface ICollisionAbility {
    int getHitRadius();

    int getDamage();

    void addCollision(ICollideAble entity);

    Collection<ICollideAble> getCollisions();

    void clearCollisions();
}
