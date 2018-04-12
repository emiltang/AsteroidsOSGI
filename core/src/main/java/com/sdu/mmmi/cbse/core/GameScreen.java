/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package com.sdu.mmmi.cbse.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dk.sdu.mmmi.cbse.api.IPlugin;
import dk.sdu.mmmi.cbse.api.IProcessor;
import dk.sdu.mmmi.cbse.api.IWorld;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.badlogic.gdx.Gdx.*;
import static com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.graphics.GL20.GL_DEPTH_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.radDeg;
import static org.osgi.service.component.annotations.ReferenceCardinality.MULTIPLE;
import static org.osgi.service.component.annotations.ReferencePolicy.DYNAMIC;
import static org.osgi.service.component.annotations.ReferencePolicyOption.GREEDY;

/**
 * @author Emil
 */
@Component(immediate = true)
public class GameScreen implements ApplicationListener {

    @Reference(cardinality = MULTIPLE, policy = DYNAMIC, policyOption = GREEDY)
    private volatile List<IPlugin> plugins = new CopyOnWriteArrayList<>();

    @Reference(cardinality = MULTIPLE, policy = DYNAMIC, policyOption = GREEDY)
    private volatile List<IProcessor> processors = new CopyOnWriteArrayList<>();

    @Reference
    private ILocalAssetManager assetManager;

    @Reference
    private IWorld world;

    private SpriteBatch batch;
    private Viewport viewport;

    @Activate
    public void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "my-gdx-game";
        cfg.useGL30 = true;
        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {
        viewport = new FitViewport(IWorld.WIDTH, IWorld.HEIGHT);
        batch = new SpriteBatch();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("bg5.jpg")) {
            assetManager.loadAsset("bg", stream.readAllBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

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
        processors.forEach(processor -> processor.process(graphics.getDeltaTime()));

        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        batch.begin();
        batch.draw(assetManager.getAsset("bg"), 0, 0, IWorld.WIDTH, IWorld.HEIGHT);
        world.getEntities().forEach(e -> {
            Texture t = assetManager.getAsset(e.getAsset());
            if (t != null) {
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
            }
        });
        batch.end();

        if (input.isKeyJustPressed(Keys.T)) {
            world.getEntities().forEach(iEntity -> System.out.println(
                    "id: " + iEntity.hashCode() + "\n"
                            + "name: " + iEntity.getAsset() + "\n"
                            + "x: " + iEntity.getX() + "\n"
                            + "y: " + iEntity.getX() + "\n"
                            + "type: " + iEntity.getClass() + "\n"
            ));
//            Collection<String> aseets = assetManager.getAssets();
//            if (aseets.isEmpty()) {
//                System.out.println("no assets");
//            } else {
//                aseets.forEach(System.out::println);
//            }
            System.out.println("Processors");
            processors.forEach(System.out::println);
            System.out.println("Plugins");
            plugins.forEach(System.out::println);

        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetManager.dispose();
    }
}
