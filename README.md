# pr-android-sdk

Distribution
============

The latest PressReader SDK can be downloaded from [release page](https://github.com/pressreader/pr-android-sdk/releases). Make sure to request access to GitHub repository. For receiving the SDK release notifications, subscribe to Watch → Custom → Releases event.
Additionally, you may want to review the ["PressReader SDK - release policies"](https://pressreader.atlassian.net/wiki/x/GAA70w).

Installation
============

PressReader SDK is distributed as a set of Android Archived Libraries (aar). To add SDK to your application you need to:

*   put PressReader SDK folder to the root folder of your Android application project

*   add the path as a maven url in your build.gradle:


**build.gradle**

```groovy
repositories {    
	mavenCentral()    
	maven {        
		url "../sdk-dist"    
		}   
	}
```
*   add the new dependency:


**build.gradle**

```groovy
// to integrate reader PressReader SDK
dependencies { implementation('com.newspaperdirect.pressreader.android:sdk:6.0@aar') { transitive = true }}
```

for the Branded Edition flow, add the following in **build.gradle**
```groovy
dependencies { implementation('com.newspaperdirect.pressreader.android:sdk_oem:6.0@aar') { transitive = true }}
```

*   copy ```pressreader_sdk.xml``` from the sample into your app

## API
Explore the features of the PRSDK through the [PressReader SDK - API](/docs/API.md)

## Configuration
Explore the ways to configure PRSDK to meet your needs through the [PressReader SDK - Configuration](/docs/Configuration.md)

# Sample

Sample apps is provided with SDK package in folders `app_sdk` (PressReader flow) and `app_sdk_oem` (Branded Edition flow).

# Local Server

To use the `app_sdk` sample with a local server, define the local server URL in resources:

`<string  name="local_server_url">http://192.168.0.1</string>`

# List of 3rd party libraries

(excluding Google Android libraries)

Odyssey - pdf rendering library, propietary to PressReader  
OpenSSL [GitHub - openssl/openssl: TLS/SSL and crypto library](https://github.com/openssl/openssl)  
Dagger-Android [GitHub - google/dagger: A fast dependency injector for Android and Java.](https://github.com/google/dagger)  
Conductor [GitHub - bluelinelabs/Conductor: A small, yet full-featured framework that allows building View-based Android applications](https://github.com/bluelinelabs/Conductor)  
Flurry [GitHub - flurry/flurry-android-sdk: Flurry Android SDK API reference documentation](https://github.com/flurry/flurry-android-sdk)  
Glide [GitHub - bumptech/glide: An image loading and caching library for Android focused on smooth scrolling](https://github.com/bumptech/glide)  
Joda-Time [GitHub - dlew/joda-time-android: Joda-Time library with Android specialization](https://github.com/dlew/joda-time-android)  
rxJava [GitHub - ReactiveX/RxJava: RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.](https://github.com/ReactiveX/RxJava)  
rxAndroid [GitHub - ReactiveX/RxAndroid: RxJava bindings for Android](https://github.com/ReactiveX/RxAndroid)  
Moshi [GitHub - square/moshi: A modern JSON library for Kotlin and Java.](https://github.com/square/moshi)  
Minidns [https://mvnrepository.com/artifact/org.minidns/minidns-hla/0.3.0](https://mvnrepository.com/artifact/org.minidns/minidns-hla/0.3.0 "https://mvnrepository.com/artifact/org.minidns/minidns-hla/0.3.0")  
SwipeRevealLayout [GitHub - chthai64/SwipeRevealLayout: Easy, flexible and powerful Swipe Layout for Android](https://github.com/chthai64/SwipeRevealLayout)  
FlowLayouts [https://mvnrepository.com/artifact/org.apmem.tools/layouts/1.10](https://mvnrepository.com/artifact/org.apmem.tools/layouts/1.10 "https://mvnrepository.com/artifact/org.apmem.tools/layouts/1.10")


Optional:  
Facebook-Login [https://mvnrepository.com/artifact/com.facebook.android/facebook-login/8.1.0](https://mvnrepository.com/artifact/com.facebook.android/facebook-login/8.1.0 "https://mvnrepository.com/artifact/com.facebook.android/facebook-login/8.1.0")  
Twitter [https://mvnrepository.com/artifact/com.twitter.sdk.android/twitter/3.3.0](https://mvnrepository.com/artifact/com.twitter.sdk.android/twitter/3.3.0 "https://mvnrepository.com/artifact/com.twitter.sdk.android/twitter/3.3.0")  
NanoHttpd [GitHub - NanoHttpd/nanohttpd: Tiny, easily embeddable HTTP server in Java.](https://github.com/NanoHttpd/nanohttpd)  
KDispatcher [GitHub - Rasalexman/KDispatcher: Simple and light-weight event dispatcher for Kotlin](https://github.com/Rasalexman/KDispatcher)
