/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.sdu.mmmi.cbse.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.mmmi.cbse.api.IAssetManager;
import org.osgi.service.component.annotations.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Emil
 */
@Component(service = {IAssetManager.class, ILocalAssetManager.class}, immediate = true)
public class AssetManager implements IAssetManager, ILocalAssetManager {

    private final Map<String, Texture> assets = new HashMap<>();

    @Override
    public Texture getAsset(String key) {
        return assets.get(key);
    }

    @Override
    public void loadAsset(final String key, final byte[] data) {
        if (data == null || data.length == 0)
            throw new IllegalArgumentException("Invalid image file");
        Gdx.app.postRunnable(() -> {
            var pixmap = new Pixmap(data, 0, data.length);
            var texture = new Texture(pixmap);
            assets.put(key, texture);
        });
    }

    @Override
    public void unloadAsset(String key) {
        assets.remove(key);
    }

    @Override
    public boolean isLoaded(String key) {
        return assets.containsKey(key) && assets.get(key) != null;
    }

    @Override
    public void dispose() {
        assets.values().forEach(Texture::dispose);
    }

    @Override
    public Collection<String> getAssets() {
        return assets.keySet();
    }
}
