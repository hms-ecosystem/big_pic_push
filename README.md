# Bit Picture Data Push

## Table of Contents
- [Introduction](#introduction)
- [Setup](#setup)
- [Environment Requirements](#environment-requirements)
- [License](#license)
- [Acknowledgements](#acknowledgements)
- [Questions](#questions)

## Introduction  
This demo showcases the integration process and functions of Huawei Push Kit with supporting big picture. Below screenshots are the app home page and received big picture data push message on Huawei HMS(Huawei mobile service) Phone Mate 30 pro.

![image](https://user-images.githubusercontent.com/57116184/200088521-89e943f0-fa0d-4702-93f5-71a992a22198.png)
## Setup
A Huawei Developer Account is needed to access and configure Huawei Push Kit. You will need to create an account if you don't already have one. Please refer to this  [guide](https://developer.huawei.com/consumer/en/doc/help/registerandlogin-0000001052613847).

1. Install Android Studio on your computer. Use Android Studio to open the project-level **build.gradle** file of the sample code.  
2. Create an app in AppGallery Connect and configure the app information. For details, please refer to [Configuring App Information in AppGallery Connect](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/config-agc-0000001050033072?ha_source=hms1).
3. Create and configure your products in AppGallery Connect. For details, please refer to [Configuring Your Products](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/config-product-0000001050033076?ha_source=hms1).
4. Import the demo to Android Studio 3.0 or later and build the demo.  
5. Configure the sample code:  
* Download the **agconnect-services.json** file of your app from AppGallery Connect, and add the file to the app-level directory of the demo.  
* Add the signing certificate and add configurations to the app-level **build.gradle** file.  
* Open the AndroidManifest file and change the package name to your app package name.  
* Replace **xxxxx.xxx or xxxxx** in the app-level build.gradle(line 30/31/32/33) with your own file, keyAlias, keyPassword and storePasssords. For details about how to obtain the public key, please refer to [Generating a Signing Certificate Fingerprint](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-config-agc-0000001050170137).
6. Run the sample code on an Android device or simulator.
7. Refer to the [documentation](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-basic-sendtestmsg-0000001087842114) for sending test messages.
![image](https://user-images.githubusercontent.com/57116184/212972495-37953076-e5b2-402c-90d3-a7216e5c970f.png)


## Environment Requirements  

Minumum Android SDK 22 and JDK 1.8 are recommended.

## License
NA

## Acknowledgements
Some codes in this project has been borrowed from Huawei official website [Huawei Push](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/service-introduction-0000001050040060) and some public website posts. Thanks for their working. 

## Questions
If you have a questions - [Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services) is the best place for any programming inquiries. Be sure to include the tag `huawei-mobile-services`.
