/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.appium.node;

import com.logigear.appium.node.runner.ICommandRunner;
import com.logigear.appium.node.runner.RunnerFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tin.tran
 */
public class AppiumStarter {
    
    public AppiumStarter() {
        mRunner = new RunnerFactory().get();
    }

    public void start(NodeModel model, String nodeConfigPath) {
        String command = getGridCommand(model, nodeConfigPath);
        String appiumBatFile = mRunner.generateRunableCommandFile(command, "appium");
        mRunner.run(appiumBatFile);  
    }
    
    // start without grid
    public void start(NodeModel model) {
        String command = getCommand(model);
        String appiumBatFile = mRunner.generateRunableCommandFile(command, "appium");
        mRunner.run(appiumBatFile);
    }
    
    // appium -a 127.0.0.1 -p %port% --default-capabilities "{\"deviceName\":\"Galaxy S6 - English\",\"udid\":\"%UDID%\",\"noReset\":true,\"platformVersion\":\"5.0\"}"
    // --chromedriver-executable F:\\chromedriver\\chromedriver51.exe --bootstrap-port 4728 --nodeconfig %~dp0%nodeconfig%
    private String getGridCommand(NodeModel model, String nodeConfigPath) {
        String command = getCommand(model)
                + " --nodeconfig " + nodeConfigPath;    
        return command;
    }
    
    private String getCommand(NodeModel model) {
        String command = mRunner.getTitle("appium - " + model.title()) + "\n"//"title \""+ model.title() +"\"\n"
                + "appium"
                + " -a " + model.getAppiumAddress()
                + " -p " + model.getAppiumPort()
                + " --default-capabilities " + model.getDefaultCapabilities();
        if (model.android()) {
            command += " --chromedriver-executable " + model.getChromeDriverPath();
            command += " --bootstrap-port " + model.getBootstrapPort();
            command += " --chromedriver-port " + model.getChromDriverPort();
        }
        else if (model.iOS()) {
            command += " --webkit-debug-proxy-port " + model.getWebKitProxyPort();
        }
        
        if (model.needLog()) {
            command += " --log " + getAppiumLog(model);
        }
        
        final String otherFlags = model.getOtherFlags();
        if (!otherFlags.isEmpty()) {
            command += " " + otherFlags;
        }
        
        return command;
    }
    
    private String getAppiumLog(NodeModel model) {
        String name = model.getDeviceName();
        String simpleDate = new SimpleDateFormat("yyyyMMddHHmm'.log'").format(new Date());
        String executionPath = System.getProperty("user.dir");
        String appiumLog = executionPath + File.separator + name.replaceAll("\\s+", "") + simpleDate;
        return appiumLog;
    }
    
    private ICommandRunner mRunner = null;
    
}
