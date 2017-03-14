/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.appium.node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tin.tran
 */
public class NodeConfigGenerator {
public String getNodeConfigPath(NodeModel model) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        ClassLoader classLoader = getClass().getClassLoader();
        //File tmpJsonFile = new File(classLoader.getResource("com/logigear/appium/node/tmpNodeConfig.json").getFile());       
        InputStream in = getClass().getResourceAsStream("/com/logigear/appium/node/tmpNodeConfig.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Object nodeJson = parser.parse(reader);
        JSONObject nodeObj = (JSONObject) nodeJson;
        
        // capabilities
        JSONArray capabilities = (JSONArray) nodeObj.get("capabilities");
        JSONObject capability = (JSONObject) capabilities.get(0);
        capability.put("browserName", model.getBrowserName());
        capability.put("version", model.getPlatformVersion());
        
        
        String platform = model.getPlatform();
        // the grid just understands "MAC" instead "iOS"
        if (platform.equalsIgnoreCase("iOs")) {
            platform = "MAC";
        }
        
        capability.put("platform", platform);
        capability.put("platformName", model.getPlatform());
        capability.put("deviceName", model.getDeviceName());
        
        // configuration
        JSONObject configuration = (JSONObject) nodeObj.get("configuration");
        configuration.put("port", model.getAppiumPort());
        configuration.put("url", "http://" + model.getAppiumAddress() + ":" + model.getAppiumPort() + "/wd/hub");
        configuration.put("hubHost", model.getAppiumGridServer());
        configuration.put("hub", "http://" + model.getAppiumGridServer() + ":4444/grid/register");
        
        File temp = File.createTempFile("nodeconfig", ".json");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            bw.write(nodeObj.toJSONString().replace("\\/", "/"));
        }
        return temp.getAbsolutePath();
    }
}
