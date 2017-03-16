/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.appium.controller;

import com.logigear.appium.APPCONST;
import com.logigear.appium.node.NodeModel;
import com.logigear.enviroment.Enviroment;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author test
 */
public class UIDeviceModel {
    private JComboBox<String> cbxPlatform;
    private JTextField txtDeviceName;
    private JTextField txtUDID;
    private JTextField txtPlatformVersion;
    private JTextField txtAppiumAddress;
    private JTextField txtAppiumPort;
    private JTextField txtAppiumGridServer;
    private JCheckBox chbxConnectToGrid;
    private JTextField txtWebKitProxyPort;
    private JTextField txtChromeDriverFullPath;
    private JButton btnRun;
    private JButton btnStartWebKitProxy;
    private JPanel androidPanel;
    private JPanel iosPanel;
    private JTextField txtBootstrapPort;
    private JCheckBox chbxAutoBootstrapPort;
    private JCheckBox chbxAutoAppiumPort;
    private JTextField txtChromeDriverPort;
    private JCheckBox chbxAutoChromeDriverPort;
    private JCheckBox chbxNeedALog;

    public UIDeviceModel() {
    }
    
    public void loadProperties(Properties properties) {
        
        if (properties == null) {
            selectAndroid();
            return;
        }
        
        String platform = properties.getProperty("platform");
        if (platform.equalsIgnoreCase("android")) {
            cbxPlatform.setSelectedItem("Android");
            selectAndroid();
        }
        else {
            cbxPlatform.setSelectedItem("iOS");
            selectIOS();
        }
        
        String deviceName = properties.getProperty("deviceName");
        txtDeviceName.setText(deviceName);
        
        String udid = properties.getProperty("udid");
        txtUDID.setText(udid);
        
        String platformVersion = properties.getProperty("platformVersion");
        txtPlatformVersion.setText(platformVersion);
        
        String appiumAddress = properties.getProperty("appiumAddress");
        txtAppiumAddress.setText(appiumAddress);
        
        String appiumPort = properties.getProperty("appiumPort");
        txtAppiumPort.setText(appiumPort);
        
        String chromeDriverFullPath = properties.getProperty("chromeDriverFullPath");
        txtChromeDriverFullPath.setText(chromeDriverFullPath);
        
        String appiumGridServer = properties.getProperty("appiumGridServer");
        txtAppiumGridServer.setText(appiumGridServer);
        
        String needGrid = properties.getProperty("needGrid");
        if (needGrid.equalsIgnoreCase("true")) {
            chbxConnectToGrid.setSelected(true);
        }
        else {
            chbxConnectToGrid.setSelected(false);
        }
        
        String webkitProxyPort = properties.getProperty("webkitProxyPort");
        txtWebKitProxyPort.setText(webkitProxyPort);
        
        String bootstrapPort = properties.getProperty("bootstrapPort");
        txtBootstrapPort.setText(bootstrapPort);
        
        String chromeDriverPort = properties.getProperty("chromeDriverPort");
        txtChromeDriverPort.setText(chromeDriverPort);
        
    }
    
    public Properties getProperties() {
        NodeModel model = getModel();
        Properties props = new Properties();
        props.setProperty("platform", model.getPlatform());
        props.setProperty("deviceName", model.getDeviceName());
        props.setProperty("udid", model.getUDID());
        props.setProperty("platformVersion", model.getPlatformVersion());
        props.setProperty("appiumAddress", model.getAppiumAddress());
        props.setProperty("appiumPort", model.getAppiumPort());
        props.setProperty("chromeDriverFullPath", model.getChromeDriverPath());
        props.setProperty("appiumGridServer", model.getAppiumGridServer());
        props.setProperty("needGrid", model.needGrid() ? "true" : "false");
        props.setProperty("webkitProxyPort", model.getWebKitProxyPort());
        props.setProperty("bootstrapPort", model.getBootstrapPort());
        props.setProperty("chromeDriverPort", model.getChromDriverPort());
        return props;
    }
    
    public NodeModel getModel() {
        final String strPlatform = cbxPlatform.getSelectedItem().toString();
        final String strDeviceName = txtDeviceName.getText();
        final String strUDID = txtUDID.getText();
        final String strPlatformVersion = txtPlatformVersion.getText();
        final String strAppiumAddress = txtAppiumAddress.getText();
        final String strAppiumPort = txtAppiumPort.getText();
        final String strChromeDriverPath = txtChromeDriverFullPath.getText();
        final String strAppiumGridServer = txtAppiumGridServer.getText();
        final String strWebKitProxyPort = txtWebKitProxyPort.getText();
        final boolean bNeedGrid = chbxConnectToGrid.isSelected();
        final String strBootstrapPort = txtBootstrapPort.getText();
        final String strChromeDriverPort = txtChromeDriverPort.getText();
        final boolean bNeedALog = chbxNeedALog.isSelected();
        
        NodeModel model = new NodeModel(strPlatform, 
                strDeviceName, 
                strUDID, 
                strPlatformVersion, 
                strAppiumAddress,
                strAppiumPort, 
                strChromeDriverPath, 
                strAppiumGridServer,
                strWebKitProxyPort,
                bNeedGrid,
                strBootstrapPort,
                strChromeDriverPort,
                bNeedALog
        );
        return model;
    }
    
    public void selectAndroid() {
        enableAndroid();
        disableIOS();
    }
    
    public void selectIOS() {
        enableIOS();
        disableAndroid();
    }
    
    private void enableAndroid() {
        cbxPlatform.setSelectedItem(APPCONST.ANDROID);
        txtChromeDriverFullPath.setEnabled(true);
        txtBootstrapPort.setEnabled(true);
        chbxAutoBootstrapPort.setEnabled(true);
        txtChromeDriverPort.setEnabled(true);
        chbxAutoChromeDriverPort.setEnabled(true);
        androidPanel.setEnabled(true);
    }
    
    private void disableAndroid() {
        txtChromeDriverFullPath.setEnabled(false);
        txtBootstrapPort.setEnabled(false);
        chbxAutoBootstrapPort.setEnabled(false);
        txtChromeDriverPort.setEnabled(false);
        chbxAutoChromeDriverPort.setEnabled(false);
        androidPanel.setEnabled(false);
    }
    
    private void enableIOS() {
        cbxPlatform.setSelectedItem(APPCONST.IOS);
        txtWebKitProxyPort.setEnabled(true);
        btnStartWebKitProxy.setEnabled(true);
        iosPanel.setEnabled(true);
    }
    
    private void disableIOS() {
        txtWebKitProxyPort.setEnabled(false);
        btnStartWebKitProxy.setEnabled(false);
        iosPanel.setEnabled(false);
    }
    
    
    //set member
    public void setCbxPlatform(JComboBox<String> cbxPlatform) {
        this.cbxPlatform = cbxPlatform;
    }

    public void setTxtDeviceName(JTextField txtDeviceName) {
        this.txtDeviceName = txtDeviceName;
    }

    public void setTxtUDID(JTextField txtUDID) {
        this.txtUDID = txtUDID;
    }

    public void setTxtPlatformVersion(JTextField txtPlatformVersion) {
        this.txtPlatformVersion = txtPlatformVersion;
    }

    public void setTxtAppiumAddress(JTextField txtAppiumAddress) {
        this.txtAppiumAddress = txtAppiumAddress;
    }

    public void setTxtAppiumPort(JTextField txtAppiumPort) {
        this.txtAppiumPort = txtAppiumPort;
    }

    public void setTxtAppiumGridServer(JTextField txtAppiumGridServer) {
        this.txtAppiumGridServer = txtAppiumGridServer;
    }

    public void setChbxConnectToGrid(JCheckBox chbxConnectToGrid) {
        this.chbxConnectToGrid = chbxConnectToGrid;
    }

    public void setTxtWebKitProxyPort(JTextField txtWebKitProxyPort) {
        this.txtWebKitProxyPort = txtWebKitProxyPort;
    }

    public void setTxtChromeDriverFullPath(JTextField txtChromeDriverFullPath) {
        this.txtChromeDriverFullPath = txtChromeDriverFullPath;
    }

    public void setBtnRun(JButton btnRun) {
        this.btnRun = btnRun;
    }

    public void setBtnStartWebKitProxy(JButton btnStartWebKitProxy) {
        this.btnStartWebKitProxy = btnStartWebKitProxy;
    }
    
    public void setAndroidPanel(JPanel androidPanel) {
        this.androidPanel = androidPanel;
    }
    
    public void setIosPanel(JPanel iosPanel) {
        this.iosPanel = iosPanel;
    }

    public void setBootstrapPort(JTextField txtBootstrapPort) {
        this.txtBootstrapPort = txtBootstrapPort;
    }

    
    public void setChboxAutoAppiumPort(JCheckBox chbxAutoAppiumPort) {
        this.chbxAutoAppiumPort = chbxAutoAppiumPort;
    }

    public void setChBoxAutoBootstrapPort(JCheckBox chbxAutoBootstrapPort) {
        this.chbxAutoBootstrapPort = chbxAutoBootstrapPort;
    }

    public void increasePort() {
        if (chbxAutoAppiumPort.isSelected()) {
            increasePort(txtAppiumPort);
        }
        
        if (chbxAutoBootstrapPort.isSelected()) {
            increasePort(txtBootstrapPort);
        }
        
        if (chbxAutoChromeDriverPort.isSelected()) {
            increasePort(txtChromeDriverPort);
        }
    }
    
    private void increasePort(JTextField jTextField) {
        try {
            String sPort = jTextField.getText();
            int iPort = Integer.parseInt(sPort);
            ++iPort;
            sPort = "" + iPort;
            jTextField.setText(sPort);
        }
        catch(Exception e) {}
    }

    public void setTxtChromeDriverPort(JTextField txtChromeDriverPort) {
        this.txtChromeDriverPort = txtChromeDriverPort;
    }

    public void setChBoxAutoChromeDriverPort(JCheckBox chbxAutoChromeDriverPort) {
        this.chbxAutoChromeDriverPort = chbxAutoChromeDriverPort;
    }

    public void setChBoxNeedALog(JCheckBox chboxNeedALog) {
        this.chbxNeedALog = chboxNeedALog;
    }
}
