/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.api.IInputService;
import dk.sdu.mmmi.cbse.api.IInputService.Key;
import dk.sdu.mmmi.cbse.api.IProcessor;
import dk.sdu.mmmi.cbse.api.IWorld;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
    public void process(float dt) {
        for (Player p : world.getEntities(Player.class)) {
            p.getMoveAbility().setMoveForward(inputService.keyDown(Key.UP));
            p.getMoveAbility().setTurnLeft(inputService.keyDown(Key.LEFT));
            p.getMoveAbility().setTurnRight(inputService.keyDown(Key.RIGHT));

            p.getCollisionAbility().getCollisions().forEach(c -> p.setHealthPoints(
                    p.getHealthPoints() - c.getCollisionAbility().getDamage()));
            if (p.getHealthPoints() <= 0) {
                world.removeEntity(p);
            }
        }
    }

    public void setWorld(IWorld world) {
        this.world = world;
    }

    public void removeWorld(IWorld world) {
        this.world = null;
    }

    public void setInputService(IInputService inputService) {
        this.inputService = inputService;
    }

    public void removeInputService(IInputService inputService) {
        this.inputService = null;
    }
}
