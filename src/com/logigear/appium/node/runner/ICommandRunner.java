/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.appium.node.runner;

import com.logigear.appium.node.NodeModel;

/**
 *
 * @author test
 */
public interface ICommandRunner {
    void run(String path);
    String generateRunableCommandFile(String command, String fileName);
    String getTitle(String title);
    
}
