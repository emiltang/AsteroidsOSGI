package com.sdu.mmmi.cbse.core;

import com.badlogic.gdx.graphics.Texture;
import dk.sdu.mmmi.cbse.api.IAssetManager;

import java.util.Collection;

/**
 * wat
 */
public interface ILocalAssetManager extends IAssetManager {

    Texture getAsset(String key);

    void dispose();

    Collection<String> getAssets();
}
