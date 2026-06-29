# Authorization

Auth API is available via `account` property of `PressReader`. Normally Host app performs PressReader authorization on start or before access to `PressReader` features is required.
```kotlin
val account = PressReader.instance.account
```

## Authorization by  token

```
account.authorize(token: String, completion: Callback)
```

## Authorization by  service name

Authorization by service name allows working with SDK based on service name only, without token. In order to achieve this behavior the next configuration has to be done:

In `values/pressreader_sdk.xml` of your app add `service_name` string type entry with your service name value.

## Errors

In case of `success=false` any generic exception can be returned, but we also define the following specific `errors: ServerError(val code: Int)`, `ServerErrorText(val description: String)`, `AuthorizationError`.

General errors list:

```
class CannotPerformOperation 
class ActivationError 
class AuthorizationError 
class PublicationNotFound 
class ServerError(val code: Int) 
class ServerErrorText(val description: String)
```

## Initialization

OEM SDK:
```kotlin
class GAppEx : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        PressReader.init(this)
    }
}
```

Reader SDK:
```kotlin
class PressReaderSdkApplication : Application() {

    /**
     * LiveData field that gets triggered when PressReader initialization is completed.
     */
    val pressReaderReadyLiveData: MutableLiveData<PressReader> = MutableLiveData()

    override fun onCreate() {
        super.onCreate()

        // Initialize PressReader in the Application class.
        initializePressReader()
    }

    private fun initializePressReader() {
        val trackerList: MutableList<AnalyticsTracker> = ArrayList()
        trackerList.add(CustomTracker())

        PressReader.init(
            app = this,
            params = PressReader.Params(trackerList),
            onReady = {
                it.setTheme(AppCompatDelegate.MODE_NIGHT_YES)
                pressReaderReadyLiveData.postValue(it)
            },
            onError = {
                Timber.e(it)
            },
        )
    }
}

class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_main)

        // Subscribe to PressReader initialization from Application class
        val app = application as PressReaderSdkApplication
        app.pressReaderReadyLiveData.observe(this) { pressReader ->
            init()
            addDownloadCallback()
        }
    }
}
```


# Observing SDK state

Upon initialisation PRSDK performs activation and other tasks necessary for its normal work.
There are several LiveDatas and flags which allow you to get the current state of the SDK:


| **Properties** | **Purpose** |
|--|--|
|`isActivatedLiveData` / `isActivated`  | is true when SDK is activated and is going to load the main catalog |
|`activationErrorLiveData` / `activationError` | is filled when something went wrong during activation. The message has the full description in this case. If you want to try run activation again you should call `PressReader.getInstance().requestActivation()` |
|`isCatalogLoadedLiveData` / `isCatalogLoaded`  | it true when Catalog is loaded |

# Download

After the instance of PressReader SDK is authorized the issues downloading becomes possible. The downloading process is manageable via catalog item's download property that conforms to Download interface described below.

```kotlin
interface Download {
    var state: DownloadState
    var progress: Int
    var error: Throwable?

    fun start()
    fun pause()
    fun cancel()
}
```
where `DownloadState` is
```kotlin
enum class DownloadState {
        Stop, // the downloading wasn’t started or was stopped due to error
        Progress, //  the downloading is in progress
        Pause, // the downloading is paused
        Ready // downloading was successfully completed
    }
```

## Access download property

You access specific download for a `cid` / `date` pair via corresponding item in catalog:
```kotlin
val download = PressReader.instance.catalog.item(cid, date)
```
<br>

## Start download
```kotlin
download.start()
```

## Pause download
```kotlin
download.pause()
```
To resume download call `download.start()` method again.

## Cancel download
```kotlin
download.cancel()
```

## Download state and progress observation
Downloading state and progress can be accessed via `state`, `progress` and `error` properties.
Additionally `download` object conforms to observation interface that allows to attach handlers to follow download progress in real time.
```kotlin
interface Download { 
    fun addCallback(callback: Callback)
    fun removeCallback(callback: Callback)
    fun removeAllCallbacks()
}
```
where `Callback` is
```kotlin
interface Callback {
        fun onUpdate(state: DownloadState, progress: Int, error: Throwable?)
    }
```

## Download error processing
`AuthorizationError` is returned when there’s a problem with authorization and re-authorization is required.
`ServerError` / `ServerErrorText` is returned when a problem happened on server side.
<br><br>

## Open the downloaded issue (Reader API)

```kotlin
val issue = PressReader.instance.catalog.item(cid, date)
issue.open()
```
the `open` method can return false in case of an error (re-downloading is required in this case).
<br><br><br>

# Downloaded
The management of downloaded catalog items can be done via `downloaded` property of `catalog`
```swift
val downloaded = PressReader.instance.catalog.downloaded
```
where `downloaded` is
```kotlin
interface Downloaded {
    // returns the list of downloaded items 
    val items: List<Item>
    // deletes downloaded item
    fun delete(item: Item)
    fun deleteAll()
}
```
where `Item` is
```
Item {
  var cid: String
  var date: Date
  var title: String
  var size: Long?
  var download: Download
}
```
Please check [Download](#download) section for the information how to access the item's download state and open it.

## State observation
`Downloaded` object conforms to the observation interface and “calls back” on items deletion or insertion.
```
Downloaded {
  fun addCallback(callback: Runnable)
  fun removeCallback(callback: Runnable)
  fun removeAllCallbacks()
}
```
# Article API
There is a method to open single article view by its ID without downloading whole publication.
```
fun openArticle(id: String)
```

# Puzzles API
There is API to open puzzle game by its ID. If something went wrong during executionof openPuzzle() method, it with return Result.failure(Exception). If everything puzzleId is correct and authorized via jwt user is allowed to have access to Puzzle games, a view with puzzle is instantly opened and the method returns Result.Success()
```
suspend fun openPuzzle(
        activity: Activity,
        puzzleId: String,
): Result<Unit>
```
# Logs

The `getLogs` asyncronous method is available to collect device logs and upload to server
```
fun getLogs(completion: LogsCallback)

interface LogsCallback {
    fun onComplete(linkToUploadedLogs: String?, additionalInfo: String?, error: Throwable?)
}
```
`completion` is a callback executed after obtaining all required information including linkToUploadedLogs, additionalInfo - some textual information we usually provide along with logs inside feedback email, like OS version, device model, device id, some internal component versions (SDK version) and crash stack if available.
<br><br><br>

# Analytics

A list of tracker classes can be passed to PressReader SDK `init` method to track events happening when publication is viewed:
```
val trackerList: MutableList<AnalyticsTracker> = ArrayList()
trackerList.add(CustomTracker())
PressReader.init(Application.this, PressReader.Params(trackerList))
```
where `CustomTracker` inherits from `ReadingViewAnalyticsTracker` and overrides the methods corresponding to the events to be logged.

## Event list

|Event name|Event parameters|Description|
|---|---|---|
|`OpenIssueForReading`|`issue`| Publication issue opened for reading |
|`IssuePage`|`issue`,`pageNumber`|Issue page is changed (reading view)|
|`IssueTextFlow`|`issue`|Issue article feed is presented (text view)|
|`ArticleView`|`article`|Full Article Text view presented|
|`Translated`|`fromLanguage`,`toLanguage`|Translated article presented|
|`ListenView`|`issue`|text-to-speach view presented|

where `fromLanguage`, `toLanguage` are ISO language codes,
`issue` - object containing the information about the issue:

```kotlin
data class TrackingIssue(val cid: String, val title: String?, val slug: String?, val latestIssueDate: Date?, val analyticsName: String?)
```
`pageNumber` - page number, integer,

`article` - object containing the information about the article:
```kotlin
class TrackingArticle(val sourceSlug: String?, val headline: String?, val date: Date?)
```
# Application Permissions

If you don’t need the `com.android.vending.BILLING` permission in your app, i.e. you don’t have Google Play In-app Purchases, you can exclude it adding the following code to the application’s `AndroidManifest.xml` file:

```xml
<uses-permission
        android:name="com.android.vending.BILLING"
        tools:node="remove" />
```
