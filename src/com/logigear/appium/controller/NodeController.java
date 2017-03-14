/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.appium.controller;

import com.logigear.appium.node.AppiumStarter;
import com.logigear.appium.node.NodeConfigGenerator;
import com.logigear.appium.node.NodeModel;
import com.logigear.appium.node.runner.TerminalRunner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tin.tran
 */
public class NodeController {
    
    private NodeConfigGenerator mConfigGenerator = new NodeConfigGenerator();
    private AppiumStarter mAppiumStarter = new AppiumStarter();
    private TerminalRunner mTerminal = new TerminalRunner();
    
    public NodeController() {
       
    }

    public void runNode(NodeModel model) {
        try {
            // connect to grid
            if (model.needGrid()) {
                String nodeConfigPath = mConfigGenerator.getNodeConfigPath(model);
                mAppiumStarter.start(model, nodeConfigPath);
            }
            else {
                mAppiumStarter.start(model);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void startWebKitProxy(NodeModel model) {
       mTerminal.startWebKitProxy(model);
    }
    
    public void saveProperties(Properties properties) {
        OutputStream output = null;
        try {
            output = new FileOutputStream("appiumstarter.properties");
            properties.store(output, null);
        } catch (Exception ex) {
            Logger.getLogger(NodeController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(NodeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean readProperties(Properties properties) {
        boolean result = false;
        InputStream input = null;
        try {
            input = new FileInputStream("appiumstarter.properties");
            properties.load(input);
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(NodeController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(NodeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
