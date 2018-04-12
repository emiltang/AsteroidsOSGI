/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.api.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import static dk.sdu.mmmi.cbse.api.IInputService.Key.*;

/**
 * @author Emil
 */
@Component(service = IProcessor.class, immediate = true)
public class PlayerProcessor implements IProcessor {

    @Reference
    private IWorld world;

    @Reference
    private IInputService inputService;

    @Override
    public void process(final float dt) {
        for (var player : world.getEntities(Player.class)) {

            player.getMoveAbility().setMoveForward(inputService.keyDown(UP));
            player.getMoveAbility().setTurnLeft(inputService.keyDown(LEFT));
            player.getMoveAbility().setTurnRight(inputService.keyDown(RIGHT));

            player.getCollisionAbility().getCollisions().stream()
                    .map(ICollideAble::getCollisionAbility)
                    .map(ICollisionAbility::getDamage)
                    .forEach(player::reduceHealthPoints);

            if (player.getHealthPoints() <= 0)
                world.removeEntity(player);
        }
    }
}
