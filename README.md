# StartAppiumNode
A GUI tool is used to run appium server by commandline and able to connect to [the grid server](https://github.com/trantrungtin/AppiumGridServer).



### Parameter Guide
#### Main Window
![start-appium-node-screen-1](https://cloud.githubusercontent.com/assets/4379558/25312391/fdc09284-2841-11e7-91ad-1fc6c97619db.png)
#### Device Information
* **Platform**: Which mobile OS platform to use
* **Device Name**: The kind of mobile device or emulator to use
* **UDID**: Unique device identifier of the connected physical device
* **Platform Version**: Mobile OS version
#### Appium
* **Address**: Appium address
* **Port**: Appium port
* **Grid Server**: [Grid server](https://github.com/trantrungtin/AppiumGridServer) address
* **Connect to Grid**: Turn on/off the flag that indicates to need a grid or not
* **Need a Log**: Provide a log for each appium server
* **Other Flags**: Support to add another flags that are not present on the main window
### iOS
* **webkit-proxy-port**: Local port used for communication with ios-webkit-debug-proxy
* **Start Webkit Proxy**: Start iOS WebKit Debug Proxy, to install this proxy click [here](https://github.com/appium/appium/blob/master/docs/en/advanced-concepts/ios-webkit-debug-proxy.md)
### Android
* **ChromeDriver Full Path**: ChromeDriver executable full path
* **Bootstrap port**: Port to use on device to talk to Appium
* **ChromeDriver port**: Port upon which ChromeDriver will run
