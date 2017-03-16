/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.appium.node;

import com.logigear.appium.APPCONST;

/**
 *
 * @author tin.tran
 */
public class NodeModel {
    public NodeModel(String strPlatform, 
            String strDeviceName,
            String strUDID, 
            String strPlatformVersion,
            String strAppiumAddress,
            String strAppiumPort, 
            String strChromeDriverPath, 
            String strAppiumGridServer,
            String strWebkitProxyPort,
            boolean bNeedGrid,
            String strBootstrapPort,
            String strChromeDriverPort,
            boolean bNeedALog,
            String strOtherFlags) 
    {
        this.mPlatform = strPlatform;
        this.mDeviceName = strDeviceName;
        this.mUDID = strUDID;
        this.mPlatformVersion = strPlatformVersion;
        this.mAppiumAddress = strAppiumAddress;
        this.mAppiumPort = strAppiumPort;
        this.mChromeDriverPath = strChromeDriverPath;
        this.mAppiumGridServer = strAppiumGridServer;
        this.mWebKitProxyPort = strWebkitProxyPort;
        this.mNeedGrid = bNeedGrid;
        this.mBootstrapPort = strBootstrapPort;
        this.mChromeDriverPort = strChromeDriverPort;
        this.mNeedALog = bNeedALog;
        this.mOtherFlags = strOtherFlags;
    }   
    

    public String getPlatform() {
        return mPlatform;
    }

    public String getDeviceName() {
        return mDeviceName;
    }

    public String getUDID() {
        return mUDID;
    }

    public String getPlatformVersion() {
        return mPlatformVersion;
    }

    public String getAppiumPort() {
        return mAppiumPort;
    }

    public String getChromeDriverPath() {
        return mChromeDriverPath;
    }

    public String getAppiumGridServer() {
        return mAppiumGridServer;
    }
    
    public String getBrowserName() {
        if (mPlatform.equalsIgnoreCase("android")) {
            return "Chrome";
        }
        else if (mPlatform.equalsIgnoreCase("ios")) {
            return "Safari";
        }        
        return "";
    }
    
    public String getDefaultCapabilities() {
        return "\"{\\\"deviceName\\\":\\\"" + mDeviceName +
                "\\\",\\\"udid\\\":\\\"" + mUDID + 
                "\\\",\\\"platformVersion\\\":\\\"" + mPlatformVersion + 
                "\\\",\\\"platformName\\\":\\\"" + mPlatform + 
                "\\\",\\\"noReset\\\":\\\"" + "true" + 
                "\\\",\\\"browserName\\\":\\\"" + (mPlatform.equalsIgnoreCase("android") ? "Chrome" : "Safari") + "\\\"}\"";
    }
    
    public String title() {
        return (mDeviceName + " " + mUDID);
    }
    
    public String getAppiumAddress() {
        return mAppiumAddress;
    }
    
    public String getWebKitProxyPort() {
        return mWebKitProxyPort;
    }
    
    public boolean needGrid() {
        return mNeedGrid;
    }
    
    public boolean needLog() {
        return mNeedALog;
    }
    
    public String getBootstrapPort() {
        return mBootstrapPort;
    }
    
    public String getChromDriverPort() {
        return mChromeDriverPort;
    }
    
    public String getOtherFlags() {
        return mOtherFlags;
    }
    
    public boolean iOS() {
        return mPlatform.equalsIgnoreCase(APPCONST.IOS);
    }
    
    public boolean android() {
        return mPlatform.equalsIgnoreCase(APPCONST.ANDROID);
    }
    
    private String mPlatform;
    private String mDeviceName;
    private String mUDID;
    private String mPlatformVersion;
    private String mAppiumAddress;
    private String mAppiumPort;
    private String mChromeDriverPath;
    private String mAppiumGridServer;
    private String mWebKitProxyPort;
    private boolean mNeedGrid;
    private String mBootstrapPort;
    private String mChromeDriverPort;
    private boolean mNeedALog;
    private String mOtherFlags;
}
