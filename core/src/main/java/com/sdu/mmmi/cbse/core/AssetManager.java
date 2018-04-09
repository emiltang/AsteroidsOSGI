/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.sdu.mmmi.cbse.core;

import com.badlogic.gdx.graphics.Texture;
import dk.sdu.mmmi.cbse.api.IAssetManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Emil
 */
public class AssetManager implements IAssetManager {

    private static final Map<String, Texture> ASSETS = new HashMap<>();

    Texture getAsset(String key) {
        return ASSETS.get(key);
    }

    @Override
    public void loadAsset(String key, String path) {
        ASSETS.put(key, new Texture(path));
    }

    @Override
    public void unloadAsset(String key) {
        ASSETS.get(key).dispose();
        ASSETS.remove(key);
    }

    void dispose() {
        ASSETS.values().forEach(Texture::dispose);
    }
}
