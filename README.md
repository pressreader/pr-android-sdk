# pr-android-sdk

Distribution
============

The latest PressReader SDK can be downloaded from [release page](https://github.com/pressreader/pr-android-sdk/releases). Make sure to request access to GitHub repository. For receiving the SDK release notifications, subscribe to Watch → Custom → Releases event. Additionally, you can find 'PressReader SDK - release policies' here: https://pressreader.atlassian.net/wiki/x/GAA70w

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
// to integrate full PressReader SDK
dependencies { implementation('com.newspaperdirect.pressreader.android:sdk_full:6.0@aar') { transitive = true }}
// to integrate reader-only PressReader SDK
dependencies { implementation('com.newspaperdirect.pressreader.android:sdk:6.0@aar') { transitive = true }}
```

for the Branded Edition flow, add the following in **build.gradle**
```groovy
dependencies { implementation('com.newspaperdirect.pressreader.android:sdk_oem:6.0@aar') { transitive = true }}
```

*   copy ```pressreader_sdk.xml``` from the sample into your app

# Initialization

Call `PressReader.init` in your Application

**GAppEx.java**

```java
public class GAppEx extends MultiDexApplication { 
	@Override public void onCreate() { 
		super.onCreate(); 
		PressReader.init(this); 
	} 
}
```

PressReader SDK can store all downloaded newspapers in an external storage. If it’s required you should call `PressReader.getInstance().setInternalStorageAvailable(true)` after initialization.

## Observing SDK state

After initialization PressReader SDK starts device activation and catalog loading with newspapers. There are several LiveDatas and flags which allow you to get the current state of the SDK:

| **Properties** | **Purpose** |
|--|--|
|`isActivatedLiveData` / `isActivated`  | is true when SDK is activated and is going to load the main catalog |
|`activationErrorLiveData` / `activationError` | is filled when something went wrong during activation. The message has the full description in this case. If you want to try run activation again you should call `PressReader.getInstance().requestActivation()` |
|`isCatalogLoadedLiveData` / `isCatalogLoaded`  | it true when Catalog is loaded |


# Executing commands

PressReaders SDK allows to leverage DeepLinking functionality using `PressReader.getInstance().execute`. If a link requires parameters you need to pass them in a map. Also It allows you to specify a callback which will return a non null value if an error occur.

**Example:**

```java
HashMap<String,  String> params = new HashMap<>(); 
params.put("cid",  "9001"); 
params.put("date",  "20191105"); 
PressReader.getInstance().execute("order", params,  
	new  PressReader.ExecuteCallback()  { 
		@Override public  void  completed(@Nullable  Throwable throwable)  {
			Log.d(TAG,  "deeplink throwable: "  + throwable); 
			} 
		}
	);
```

To pass a token there is a convenient method: `PressReader.getInstance().sendToken`.

# Full Screen PressReader UI

To start a fullscreen PressReader UI start PressReader Intent this way:

```java
Intent intent = new Intent(MainActivity.this, com.newspaperdirect.sdk.MainActivity.class); 
startActivity(intent);
```

For Branded Editions flow use the following instead:

```java
Intent intent = new Intent(MainActivity.this, com.newspaperdirect.pressreader.android.oem.Main.class); 
startActivity(intent);
```

If you need to customize some resources please checkout `pressreader_sdk.xml`.

# Common configuration settings

Most of the settings resides in `pressreader_sdk_config.xml`

### UI Customizations

| **Name** | **Description** | **Is Required** |
|--|--|--|
| `color_brand` | Main accent color (tint colour of buttons, highlights, some other action controls.) | yes |
| `color_brand_dark` | Secondary accent color |  |
| `color_background_dark` | Catalog background |  |
| `brand_gradient_start_color` | Start gradient colour of brand modal screens (Branded Edition Splash Screen, Onboarding, Subscription, etc.). |  |
| `brand_gradient_end_color` | End gradient colour of brand modal screens |  |
| `is_popup_article_view_allowed` | Show article in popup on tablet when it fits the screen |  |

### Graphic resources

| **Name** | **Description** | **Is Required** |
|--|--|--|
| `@drawable/logo.png` | Splash screen logo image | yes |
| `@drawable/splash_image_background.png` | Splash screen background image | yes |
| `@drawable/logo_image_placeholder.png` | Loading image placeholder | yes |
| `@drawable/oem_logo_toolbar.png` | Toolbar logo image | yes |

**Please note:** If these graphic resources aren’t overriden, then SDK will use default one which aren’t related to any certain publisher. In order to override mentioned graphic resources an integrator of SDK should create corresponsing png files and add them at least to drawable folder. Also, we recommend to create and put resources for all main Android densities such as mdpi, hdpi etc.

### Service configuration params

| **Name** | **Description** |
|--|--|
| `service_name` | Pressreader system registered service name. This has to be provided by Pressreader company. |
| `privacy_policy_url` | Link to online privacy policy |
| `terms_of_use_url` | Link to online terms of use |
| `registration_url` | Link to online account creation page if account creation has to be managed by third-party provider. (optional) |
| `password_recovery_url` | Link to online password recovery page, if account is managed by third-party provider |
| `twitter_url` | Link to partner Twitter account |
| `youtube_url` | Link to partner’s Youtube channel |
| `facebook_url` | Link to partner’s Facebook page |
| `instagram_url` | Link to partner’s Instagram page |
| `pref_feedback_email` | Support contact e-mail |
| `pref_feedback_phone_number` | Support contact phone |
| `pref_feedback_show_support` | Show contact support section in settings |
| `enroll_in_onboarding` | Enabled onboarding during initial first launch |

### Navigation bar customization

The parameters allow to show/hide buttons on the navigation bar.

| **Name** | **Description** |
|--|--|
| `sdk_exit_button` | Show the button which closes PressReader Activity |
| `sdk_exit_button_text` | Exit button caption |
| `sdk_homefeed_button` | Show the home feed button |
| `sdk_catalog_button` | Show the publications catalog button |
| `sdk_mylibrary_button` | Show my library button |
| `sdk_accounts_button` | Show accounts button for the PressReader flow or accounts UI on More screen in the Branded Edition flow |
| `sdk_settings_button` | Show settings button for the PressReader flow or settings UI on More screen in the Branded Edition flow |
| `sdk_bookmarks_button` | Show bookmarks button for the PressReader flow |

### Feature flags

| **Name** | **Description** | **Default** |
|--|--|--|
| `bookmarks_enabled` | Enable bookmarking articles |
| `bookmarks_enabled` | Enable bookmarking articles |
| `comments_enabled` | Enables commenting feature | true |
| `new_payment_flow_supported` | Enables new payment flow. Checkout “New Payment Flow” section to customize string resources according your requirements. |
| `enable_social_signin` | Enabled sign-in with social providers.  Need to provide `facebook_app_id`, `facebook_app_secret`  |
| `hide_register` | Disable creating account in application |
| `hide_sharing` | Disable sharing content in application |
| `is_free_app` | Removes creating/sign-in options in app. All content should be free. |
| `favourites_enabled` | Enabled/Disable favorited newpaper titles |
| `radio_support` | Enable/Disable listen feature |
| `translate_enabled` | Enable/Disable translation feature |
| `single_title_mode` | Enable single newspaper mode for Home screen |
| `newsfeed_api` | Home RSS article provider (should be provider by Pressreader): 0 - no RSS article; 2 - RSS source based on favorited newspaper title. Each newspaper title should have configured its own RSS channel; 3 - All Rss sources are imported into single RSS channel |
| `vote_enabled` | Enable/disable voting functionality for an article |
| `hyphenation_disabled` | Enable/disable hyphenation for article details |
| `reader_external_links_enable` | Enable/disable external links navigation and rendering for Article Details and Replica | true |

# Statistics and Custom Tracker

In PressReader you can inject your custom statistic tracker to log PressReader related events:
```java
List<PressReaderAnalyticsTracker> trackerList = new ArrayList<>(); 
trackerList.add(new CustomTracker()); 
PressReader.init(this, new PressReader.Params(trackerList));
```
In `CustomTracker` you can override events you need.

```java
class CustomTracker extends PressReaderAnalyticsTracker { 
	public CustomTracker() { 
		super(); 
		// TODO: initialize your statistics here 
		} 
	@Override public  void  PR_Catalog(@NotNull  Activity context,  @NotNull  String path)  { // log event } 
	...
```
# Home JSON Configuration

To configure the home or catalog screen you need to create a folder “res/raw” and add a json file home_config.json (for home) or pubhub_config.json(for catalog).

# Dark/Light Theme configuration:

Starting from Android SDK version 6.6.2, both Dark and Light modes are supported by default. The SDK will inherit theme from the parent application to ensure the correct mode is applied.

To enable Theme configuration from the SDK's settings section, override the following configuration field:

`<string name="theme_switching_enabled">true</string>`

Note, if this option is enabled, the SDK will operate based on user settings within the SDK and will not consider the parent application's mode.

To force the SDK to use any of the available modes, you can use:

```java
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  - Light Theme 
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);  - Dark Theme 
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);  - Match Device
```
You can also obtain the current mode settings using:
```kotlin
when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) { 
	Configuration.UI_MODE_NIGHT_YES -> ... 
	Configuration.UI_MODE_NIGHT_NO -> ... 
	Configuration.UI_MODE_NIGHT_UNDEFINED -> ... 
	else -> ... 
}
```

# New Payment Flow

If you enable `new_payment_flow_supported` you will likely want to override some string resources:

```xml
<string  name="go_premium"><![CDATA[Go Premium]]></string> 
<string  name="go_premium_ad1"><![CDATA[Subscribe & choose from thousand of issues]]></string> 
<string  name="go_premium_ad2"><![CDATA[Try free for {7} days]]></string> 
<string  name="go_premium_ad2_line2"><![CDATA[Regularly <a href=\"\">{price}/month</a>]]></string> 
<string  name="go_premium_ad2_notrial"><![CDATA[Use on up to 5 devices]]></string> 
<string  name="go_premium_ad3"><![CDATA[Cancel anytime]]></string> 
<string  name="go_premium_buy_with_pressreader"><![CDATA[Buy with PressReader]]></string> 
<string  name="go_premium_get_this_issue"><![CDATA[Get this issue]]></string> 
<string  name="go_premium_issue_subscription_details_format"><![CDATA[New issues %1$s]]></string> 
<string  name="go_premium_issue_subscription_payment_period"><![CDATA[/month]]></string> 
<string  name="go_premium_price"><![CDATA[{price}/month]]></string> 
<string  name="go_premium_start_free_trial"><![CDATA[Start free trial]]></string>
```

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
