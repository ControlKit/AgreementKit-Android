# API Reference

Complete API documentation for AgreementKit Android library.

## 📚 Core Classes

### AgreementKit

Main class for managing agreement dialogs.

```kotlin
class AgreementKit(
    private var config: AgreementServiceConfig,
    context: Context
)
```

#### Methods

##### `showView()`
Displays the agreement dialog.

```kotlin
fun showView()
```

**Example:**
```kotlin
val agreementKit = agreementKitHost(config)
agreementKit.showView()
```

##### `ConfigureComposable()`
Internal composable for configuring the agreement UI.

```kotlin
@Composable
internal fun ConfigureComposable(
    onDismiss: (() -> Unit)? = null,
    onState: ((AgreementState) -> Unit)? = null
)
```

### agreementKitHost

Composable function for creating and managing AgreementKit instances.

```kotlin
@Composable
fun agreementKitHost(
    config: AgreementServiceConfig,
    onDismiss: (() -> Unit)? = null,
    onState: ((AgreementState) -> Unit)? = null
): AgreementKit
```

**Parameters:**
- `config`: Service configuration object
- `onDismiss`: Callback when dialog is dismissed
- `onState`: Callback for state changes

**Returns:** AgreementKit instance

**Example:**
```kotlin
val agreementKit = agreementKitHost(
    config = AgreementServiceConfig(...),
    onDismiss = { /* handle dismissal */ },
    onState = { state -> /* handle state changes */ }
)
```

## 🔧 Configuration Classes

### AgreementServiceConfig

Configuration for the agreement service.

```kotlin
data class AgreementServiceConfig(
    var viewConfig: AgreementViewConfig = AgreementViewConfig(),
    var version: String,
    var name: String,
    var appId: String,
    var lang: String = "en",
    var deviceId: String? = null,
    var timeOut: Long = 5000L,
    var timeRetryThreadSleep: Long = 1000L,
    var maxRetry: Int = 5
)
```

#### Properties

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `viewConfig` | AgreementViewConfig | AgreementViewConfig() | UI configuration |
| `version` | String | - | App version |
| `name` | String | - | Agreement name |
| `appId` | String | - | App identifier |
| `lang` | String | "en" | Language code |
| `deviceId` | String? | null | Custom device ID |
| `timeOut` | Long | 5000L | API timeout (ms) |
| `timeRetryThreadSleep` | Long | 1000L | Retry delay (ms) |
| `maxRetry` | Int | 5 | Max retry attempts |

### AgreementViewConfig

Configuration for the agreement UI.

```kotlin
data class AgreementViewConfig(
    var viewStyle: AgreementViewStyle = AgreementViewStyle.Popover1,
    var popupViewLayoutModifier: Modifier? = null,
    var popupViewBackGroundColor: Color? = null,
    var popupViewCornerRadius: Dp? = null,
    var headerTitle: String = "Terms of Service",
    var headerTitleColor: Color? = null,
    var headerTitleLayoutModifier: Modifier? = null,
    var headerTitleView: @Composable ((String) -> Unit)? = null,
    var buttonsLayoutModifier: Modifier? = null,
    var termsTitleViewStyle: TextStyle? = null,
    var termsTitleView: @Composable ((String) -> Unit)? = null,
    var scrollBarColor: Color? = null,
    var termsTitle: String = "Terms of Service\n",
    var acceptButtonTitle: String = "Accept",
    var declineButtonTitle: String = "Decline",
    var updateButtonTitleColor: Color? = null,
    var acceptButtonColor: Color? = null,
    var declineButtonColor: Color? = null,
    var declineButtonCornerColor: Color? = null,
    var acceptButtonCornerRadius: Dp? = null,
    var declineButtonCornerRadius: Dp? = null,
    var updateButtonBorderColor: Color? = null,
    var acceptButtonLayoutModifier: Modifier? = null,
    var declineButtonLayoutModifier: Modifier? = null,
    var acceptButtonView: @Composable ((() -> Unit) -> Unit)? = null,
    var declineButtonView: @Composable ((() -> Unit) -> Unit)? = null
)
```

#### Properties

##### Dialog Appearance
| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `viewStyle` | AgreementViewStyle | Popover1 | UI style variant |
| `popupViewLayoutModifier` | Modifier? | null | Dialog layout modifier |
| `popupViewBackGroundColor` | Color? | null | Dialog background color |
| `popupViewCornerRadius` | Dp? | null | Dialog corner radius |

##### Header Customization
| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `headerTitle` | String | "Terms of Service" | Header title text |
| `headerTitleColor` | Color? | null | Header title color |
| `headerTitleLayoutModifier` | Modifier? | null | Header layout modifier |
| `headerTitleView` | Composable? | null | Custom header view |

##### Content Styling
| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `termsTitle` | String | "Terms of Service\n" | Default content text |
| `termsTitleViewStyle` | TextStyle? | null | Content text style |
| `termsTitleView` | Composable? | null | Custom content view |
| `scrollBarColor` | Color? | null | Scrollbar color |

##### Button Customization
| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `acceptButtonTitle` | String | "Accept" | Accept button text |
| `declineButtonTitle` | String | "Decline" | Decline button text |
| `acceptButtonColor` | Color? | null | Accept button color |
| `declineButtonColor` | Color? | null | Decline button color |
| `acceptButtonCornerRadius` | Dp? | null | Accept button corner radius |
| `declineButtonCornerRadius` | Dp? | null | Decline button corner radius |
| `acceptButtonLayoutModifier` | Modifier? | null | Accept button layout |
| `declineButtonLayoutModifier` | Modifier? | null | Decline button layout |
| `acceptButtonView` | Composable? | null | Custom accept button |
| `declineButtonView` | Composable? | null | Custom decline button |
| `buttonsLayoutModifier` | Modifier? | null | Buttons container layout |

### AgreementViewStyle

Enum for different view styles.

```kotlin
enum class AgreementViewStyle {
    Popover1
}
```

#### Methods

##### `checkViewStyle()`
Returns the appropriate view implementation.

```kotlin
companion object {
    fun checkViewStyle(style: AgreementViewStyle): AgreementContract
}
```

## 🔄 State Management

### AgreementState

Sealed class representing different states of the agreement.

```kotlin
sealed class AgreementState {
    object Initial : AgreementState()
    object NoContent : AgreementState()
    data class ShowView(val data: DataResponse?) : AgreementState()
    data class Action(val data: String) : AgreementState()
    data class Error(val data: String?) : AgreementState()
    data class ActionError(val data: String?) : AgreementState()
}
```

#### States

| State | Description | Data |
|-------|-------------|------|
| `Initial` | Initial state | - |
| `NoContent` | No agreement content available | - |
| `ShowView` | Agreement content loaded | DataResponse? |
| `Action` | User action performed | String (action type) |
| `Error` | Error occurred | String? (error message) |
| `ActionError` | Action error occurred | String? (error message) |

### AgreementViewModel

ViewModel for managing agreement state and API calls.

```kotlin
class AgreementViewModel(
    private val api: AgreementApi
) : ViewModel()
```

#### Properties

##### `state`
Current agreement state.

```kotlin
val state: StateFlow<AgreementState>
```

##### `openDialog`
Dialog visibility state.

```kotlin
val openDialog: StateFlow<Boolean>
```

##### `cancelButtonEvent`
Event channel for cancel button.

```kotlin
val cancelButtonEvent: Flow<Unit>
```

#### Methods

##### `setConfig()`
Sets the service configuration.

```kotlin
fun setConfig(config: AgreementServiceConfig)
```

##### `getData()`
Fetches agreement data from API.

```kotlin
fun getData()
```

##### `sendAction()`
Sends user action to API.

```kotlin
fun sendAction(action: String)
```

##### `showDialog()`
Shows the agreement dialog.

```kotlin
fun showDialog()
```

##### `submitAccept()`
Handles accept button click.

```kotlin
fun submitAccept()
```

##### `dismissDialog()`
Handles decline button click.

```kotlin
fun dismissDialog()
```

##### `clearState()`
Clears the current state.

```kotlin
fun clearState()
```

##### `triggerForceUpdate()`
Triggers force update event.

```kotlin
fun triggerForceUpdate()
```

## 🌐 API Classes

### AgreementApi

API service for agreement operations.

```kotlin
class AgreementApi(
    private val apiService: ApiService
)
```

#### Methods

##### `getData()`
Fetches agreement data.

```kotlin
suspend fun getData(
    url: String,
    appId: String,
    version: String,
    deviceId: String,
    sdkVersion: String,
    name: String
): NetworkResult<ApiDataResponse?>
```

##### `setAction()`
Sends user action.

```kotlin
suspend fun setAction(
    url: String,
    appId: String,
    version: String,
    deviceId: String,
    sdkVersion: String,
    action: String
): NetworkResult<ActionResponse?>
```

### ApiService

Retrofit interface for API calls.

```kotlin
interface ApiService
```

#### Methods

##### `getData()`
GET request for agreement data.

```kotlin
@GET()
suspend fun getData(
    @Url url: String,
    @Header("x-app-id") appId: String?,
    @Header("x-version") version: String,
    @Header("x-device-uuid") deviceId: String?,
    @Header("x-sdk-version") sdkVersion: String?,
    @Query("name") name: String?
): Response<ApiDataResponse>
```

##### `setAction()`
POST request for user actions.

```kotlin
@FormUrlEncoded
@POST()
suspend fun setAction(
    @Url url: String,
    @Header("x-app-id") appId: String?,
    @Header("x-version") version: String,
    @Header("x-sdk-version") sdkVersion: String,
    @Header("x-device-uuid") deviceId: String?,
    @Field("action") action: String
): Response<ActionResponse>
```

## 📊 Data Models

### DataResponse

Response model for agreement data.

```kotlin
data class DataResponse(
    val id: String? = null,
    val version: Int? = null,
    val title: String? = null,
    val agreementTitle: String? = null,
    val description: String? = null,
    val buttonTitle: String? = null,
    val declineButtonTitle: String? = null,
    val sdkVersion: String? = null,
    val createdAt: String? = null
)
```

#### Properties

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `id` | String? | null | Agreement ID |
| `version` | Int? | null | Agreement version |
| `title` | String? | null | Agreement title |
| `agreementTitle` | String? | null | Agreement title (alternative) |
| `description` | String? | null | Agreement content |
| `buttonTitle` | String? | null | Accept button text |
| `declineButtonTitle` | String? | null | Decline button text |
| `sdkVersion` | String? | null | SDK version |
| `createdAt` | String? | null | Creation timestamp |

### ApiDataResponse

API response wrapper.

```kotlin
data class ApiDataResponse(
    val data: DataResponse?
)
```

### ActionResponse

Response for action requests.

```kotlin
data class ActionResponse(
    val success: Boolean? = null,
    val message: String? = null
)
```

## 🔧 Utility Classes

### NetworkResult

Sealed class for API results.

```kotlin
sealed class NetworkResult<out T> {
    data class Success<T>(val value: T) : NetworkResult<T>()
    data class Error(val error: String?) : NetworkResult<Nothing>()
}
```

### Actions

Enum for user actions.

```kotlin
enum class Actions(val value: String) {
    VIEW("view"),
    ACCEPT("accept"),
    DECLINE("decline")
}
```

### UniqueUserIdProvider

Utility for generating unique user IDs.

```kotlin
object UniqueUserIdProvider
```

#### Methods

##### `getOrCreateUserId()`
Gets or creates a unique user ID.

```kotlin
fun getOrCreateUserId(context: Context): String
```

## 🎨 UI Components

### AgreementContract

Interface for agreement view implementations.

```kotlin
interface AgreementContract
```

#### Methods

##### `ShowView()`
Displays the agreement view.

```kotlin
@Composable
fun ShowView(
    config: AgreementViewConfig,
    response: DataResponse,
    viewModel: AgreementViewModel
)
```

### AgreementPopover1

Default popover implementation.

```kotlin
class AgreementPopover1 : AgreementContract
```

### CustomScrollbar

Custom scrollbar component.

```kotlin
@Composable
fun CustomScrollbar(
    modifier: Modifier = Modifier,
    color: Color? = null,
    textView: @Composable () -> Unit
)
```

### HtmlText

HTML text component.

```kotlin
@Composable
fun HtmlText(
    html: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default
)
```

## 🛠️ Factory Classes

### AgreementViewModelFactory

Factory for creating AgreementViewModel instances.

```kotlin
class AgreementViewModelFactory(
    private val api: AgreementApi
) : ViewModelProvider.Factory
```

#### Methods

##### `create()`
Creates a ViewModel instance.

```kotlin
override fun <T : ViewModel> create(modelClass: Class<T>): T
```

## 🔒 Security Classes

### ApiHandler

Error handler for API calls.

```kotlin
class ApiHandler
```

### ThrowOnHttpErrorCallAdapterFactory

Call adapter factory for HTTP error handling.

```kotlin
class ThrowOnHttpErrorCallAdapterFactory : CallAdapter.Factory
```

## 📱 Theme Classes

### Color

Theme colors.

```kotlin
object Color
```

### Theme

Material theme configuration.

```kotlin
object Theme
```

### Type

Typography configuration.

```kotlin
object Type
```

## 🔧 Constants

### BuildConfig

Build configuration constants.

```kotlin
object BuildConfig
```

#### Properties

| Property | Type | Description |
|----------|------|-------------|
| `LIB_VERSION_CODE` | int | Library version code |
| `LIB_VERSION_NAME` | String | Library version name |
| `API_URL` | String | API endpoint URL |

---

## 📝 Notes

- All Composable functions should be called from within a Composable context
- State management uses Kotlin Coroutines and StateFlow
- API calls are handled asynchronously with proper error handling
- The library automatically handles device ID generation and storage
- All UI components follow Material Design 3 guidelines

---

For more examples, see [Usage Examples](USAGE_EXAMPLES.md)
