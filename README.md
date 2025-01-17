# WiFi Aware Checker Android App

This is a fork of app https://github.com/getditto/wifi-aware-checker to add a couple more functionalities:
  - Refreshing the app swiping down(https://github.com/getditto/wifi-aware-checker/pull/4)
  - Checking the current availability of Wifi Aware, if it is ready to use in the moment(https://github.com/getditto/wifi-aware-checker/pull/3)

This app quickly checks if your Android device supports WiFi Aware. Learn more about WiFi Aware here:
[https://developer.android.com/guide/topics/connectivity/wifi-aware](https://developer.android.com/guide/topics/connectivity/wifi-aware).

WiFi Aware is similar to Apple Wireless Direct, also referred to as AWDL. This is a peer to peer WiFi system that can make ad-hoc connections. The underlying specification is called Neighbor Awareness Networking. You can learn more about this on the [WiFi Alliance Website here](https://www.wi-fi.org/discover-wi-fi/wi-fi-aware)

## Why is this app useful?

WiFi Aware is similar to Apple Wireless Direct, which is famously used in AirDrop. WiFi aware is introduced in the Android 8.0 (API level 26) or higher operating systems. _However, it's up to the manufacturer to enable WiFi Aware at the firmware level._

_This means that you may have a device like a OnePlus 5T running Android 10 but WiFi Aware is still unavailable_.

Run this app and quickly find out if WiFi Aware is available on your device. 
WiFi Aware is an upcoming supported transport at [Ditto](https://www.ditto.live)

## Running this app

1. `git clone` this repository
2. Run this project on your Android 8.0 or higher device with Android studio! 

![WiFi Aware Checker](https://media.giphy.com/media/kfXxeX3zidS1yzKVgC/giphy.gif)
