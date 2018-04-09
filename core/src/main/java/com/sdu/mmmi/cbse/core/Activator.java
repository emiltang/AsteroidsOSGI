/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdu.mmmi.cbse.core;

import dk.sdu.mmmi.cbse.api.IWorld;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 *
 * @author Emil
 */
public class Activator implements BundleActivator {

    private IWorld world;

    public synchronized void setWorld(IWorld world) {
        this.world = world;
    }

    public synchronized void removeWorld(IWorld world) {
        this.world = null;
    }

    @Override
    public void start(BundleContext context) throws Exception {

        System.out.println("Start bundle");
        new Thread(() -> {
            while (true) {
                if (((System.currentTimeMillis() / 10000) & 1) == 0) {
                    if (world != null) {
                        System.out.println("not null");
                    } else {
                        System.out.println("actually null");
                    }
                }
            }
        }).start();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        //TODO add deactivation code here
    }
}
