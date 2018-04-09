/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package dk.sdu.mmmi.cbse.library;

import dk.sdu.mmmi.cbse.api.IEntity;
import dk.sdu.mmmi.cbse.api.IWorld;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Emil
 */
public class World implements IWorld {

    private static final List<IEntity> ENTITIES = new ArrayList<>();

    @Override
    public List<IEntity> getEntities() {
        return ENTITIES;
    }

    @Override
    public <E extends IEntity> List<E> getEntities(Class<E> type) {
        return ENTITIES.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(toList());
    }

    @Override
    public void addEntity(IEntity entity) {
        ENTITIES.add(entity);
    }

    @Override
    public <E extends IEntity> void removeEntities(List<E> entities) {
        ENTITIES.removeAll(entities);
    }

    @Override
    public void removeEntity(IEntity entity) {
        ENTITIES.remove(entity);
    }
}
