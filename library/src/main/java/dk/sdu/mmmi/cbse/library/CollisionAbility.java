/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package dk.sdu.mmmi.cbse.library;

import dk.sdu.mmmi.cbse.api.ICollideAble;
import dk.sdu.mmmi.cbse.api.ICollisionAbility;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CollisionAbility implements ICollisionAbility {

    private final Set<ICollideAble> collisions = new HashSet<>();
    private final int damage;
    private final int hitRadius;

    public CollisionAbility(int damage, int hitRadius) {
        this.damage = damage;
        this.hitRadius = hitRadius;
    }

    public int getHitRadius() {
        return hitRadius;
    }

    public int getDamage() {
        return damage;
    }

    public void addCollision(ICollideAble entity) {
        collisions.add(entity);
    }

    public Collection<ICollideAble> getCollisions() {
        return collisions;
    }

    public void clearCollisions() {
        collisions.clear();
    }
}
