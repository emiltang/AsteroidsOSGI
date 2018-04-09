/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.sdu.mmmi.cbse.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Gdx.graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import static com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration.getDesktopDisplayMode;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.math.MathUtils.radDeg;
import dk.sdu.mmmi.cbse.api.IPlugin;
import dk.sdu.mmmi.cbse.api.IProcessor;
import dk.sdu.mmmi.cbse.api.IWorld;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emil
 */
public class GameScreen implements ApplicationListener {

    private AssetManager assetManager;
    private IWorld world;
    private SpriteBatch batch;
    private Viewport viewport;
    private Texture bg;
    private FPSLogger fpsLogger;
    private final List<IPlugin> plugins = new ArrayList<>();
    private final List<IProcessor> processors = new ArrayList<>();

    public GameScreen() {
        System.out.println("Starting game");
        init();
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public final void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "my-gdx-game";
        cfg.setFromDisplayMode(getDesktopDisplayMode());
        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {
        assetManager = new AssetManager();
        viewport = new FitViewport(IWorld.WIDTH, IWorld.HEIGHT);
        batch = new SpriteBatch();
        bg = new Texture("bg5.jpg");
        fpsLogger = new FPSLogger();
        viewport.apply(true);
        batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    @Override
    public void resize(final int x, final int y) {
        viewport.update(x, y, true);
    }

    @Override
    public void render() {
        // Update processors
        processors.forEach(p -> p.process(graphics.getDeltaTime()));

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();
        batch.draw(bg, 0, 0, IWorld.WIDTH, IWorld.HEIGHT);
        world.getEntities().forEach(e -> {
            Texture t = assetManager.getAsset(e.getAsset());
            batch.draw(
                    t,
                    e.getX() - t.getWidth() / 2, e.getY() - t.getHeight() / 2,
                    t.getWidth() / 2, t.getHeight() / 2,
                    t.getWidth(), t.getHeight(),
                    1, 1,
                    e.getRotation() * radDeg,
                    0, 0,
                    t.getWidth(), t.getHeight(),
                    false, false
            );
        });
        batch.end();
        fpsLogger.log();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        plugins.forEach(IPlugin::stop);
        bg.dispose();
        assetManager.dispose();
    }

    public void addPlugin(IPlugin plugin) {
        plugins.add(plugin);
        plugin.start();
    }

    public void removePlugin(IPlugin plugin) {
        plugins.remove(plugin);
        plugin.start();
    }

    public void addProcessor(IProcessor processor) {
        processors.add(processor);
    }

    public void removeProcessor(IProcessor processor) {
        processors.remove(processor);
    }

    public void setWorld(IWorld world) {
        this.world = world;
    }

    public void removeWorld(IWorld world) {
        this.world = null;
    }
}
