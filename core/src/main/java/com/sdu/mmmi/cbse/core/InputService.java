/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.sdu.mmmi.cbse.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.mmmi.cbse.api.IInputService;
import org.osgi.service.component.annotations.Component;


@Component(service = IInputService.class)
public class InputService implements IInputService {

    @Override
    public boolean keyDown(Key key) {
        switch (key) {
            case RIGHT:
                return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
            case LEFT:
                return Gdx.input.isKeyPressed(Input.Keys.LEFT);
            case UP:
                return Gdx.input.isKeyPressed(Input.Keys.UP);
            default:
                return false;
        }
    }
}
