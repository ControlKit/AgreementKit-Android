# Setup Guide

This guide will help you integrate AgreementKit into your Android project.

## 📋 Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24+ (Android 7.0)
- Kotlin 2.2.10+
- Jetpack Compose enabled project

## 🚀 Installation

### Step 1: Add JitPack Repository

Add the JitPack repository to your project's root `build.gradle` file:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add Dependency

Add the AgreementKit dependency to your app module's `build.gradle`:

```gradle
dependencies {
    implementation 'com.github.YourUsername:AgreementKit:0.0.1'
}
```

### Step 3: Enable Compose (if not already enabled)

Make sure your app's `build.gradle` has Compose enabled:

```gradle
android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}
```

### Step 4: Add Internet Permission

Add internet permission to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 🔧 Basic Setup

### 1. Initialize in Your Activity

```kotlin
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sepanta.controlkit.agreementkit.agreementKitHost
import com.sepanta.controlkit.agreementkit.service.config.AgreementServiceConfig
import com.sepanta.controlkit.agreementkit.view.config.AgreementViewConfig
import com.sepanta.controlkit.agreementkit.view.config.AgreementViewStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            YourAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val agreementKit = agreementKitHost(
                        config = AgreementServiceConfig(
                            version = "1.0.0",
                            appId = "your-unique-app-id",
                            name = "Privacy Policy",
                            viewConfig = AgreementViewConfig(
                                viewStyle = AgreementViewStyle.Popover1
                            )
                        ),
                        onDismiss = {
                            // Handle when user dismisses the dialog
                        },
                        onState = { state ->
                            // Handle state changes
                            when (state) {
                                is AgreementState.ShowView -> {
                                    // Agreement content loaded successfully
                                }
                                is AgreementState.Error -> {
                                    // Handle errors
                                }
                                // ... other states
                            }
                        }
                    )
                    
                    // Your app content here
                    YourAppContent()
                    
                    // Show agreement when needed
                    LaunchedEffect(Unit) {
                        agreementKit.showView()
                    }
                }
            }
        }
    }
}
```

### 2. Configure API Endpoint (Optional)

Create a `local.properties` file in your project root to configure the API endpoint:

```properties
API_URL=https://your-api.com/terms-and-conditions
```

If not provided, the library will use the default endpoint.

## 🎨 Theme Integration

### Material 3 Theme

The library automatically adapts to your app's Material 3 theme:

```kotlin
@Composable
fun YourAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(), // or lightColorScheme()
        typography = Typography,
        content = content
    )
}
```

### Custom Theme Colors

You can override the library's default colors:

```kotlin
AgreementViewConfig(
    popupViewBackGroundColor = MaterialTheme.colorScheme.surface,
    headerTitleColor = MaterialTheme.colorScheme.primary,
    acceptButtonColor = MaterialTheme.colorScheme.primary,
    declineButtonColor = MaterialTheme.colorScheme.outline
)
```

## 🔄 State Management Integration

### With ViewModel

```kotlin
class MainViewModel : ViewModel() {
    private val _agreementState = MutableStateFlow<AgreementState?>(null)
    val agreementState = _agreementState.asStateFlow()
    
    fun handleAgreementState(state: AgreementState) {
        _agreementState.value = state
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val agreementState by viewModel.agreementState.collectAsState()
    
    val agreementKit = agreementKitHost(
        config = config,
        onState = viewModel::handleAgreementState
    )
    
    // Handle agreement state
    LaunchedEffect(agreementState) {
        when (agreementState) {
            is AgreementState.Action -> {
                // User accepted/declined
                if (agreementState.data == "accept") {
                    // Navigate to main app
                }
            }
            // ... other states
        }
    }
}
```

## 🌐 Backend Setup

### API Endpoints

Your backend should provide these endpoints:

#### GET /terms-and-conditions
Fetches agreement content.

**Headers:**
- `x-app-id`: Your app identifier
- `x-version`: App version
- `x-device-uuid`: Device identifier
- `x-sdk-version`: Library version

**Query Parameters:**
- `name`: Agreement name (e.g., "Privacy Policy")

**Response:**
```json
{
    "id": "agreement-123",
    "title": "Privacy Policy",
    "description": "Your privacy policy content...",
    "buttonTitle": "Accept",
    "declineButtonTitle": "Decline",
    "version": 1,
    "createdAt": "2024-01-01T00:00:00Z"
}
```

#### POST /terms-and-conditions/{id}
Sends user action.

**Headers:**
- `x-app-id`: Your app identifier
- `x-version`: App version
- `x-device-uuid`: Device identifier
- `x-sdk-version`: Library version

**Body:**
```json
{
    "action": "accept" // or "decline", "view"
}
```

## 🧪 Testing Setup

### Unit Tests

```kotlin
class AgreementKitTest {
    @Test
    fun `test agreement configuration`() {
        val config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "test-app-id",
            name = "Test Agreement"
        )
        
        assertThat(config.version).isEqualTo("1.0.0")
        assertThat(config.appId).isEqualTo("test-app-id")
    }
}
```

### UI Tests

```kotlin
@Test
fun testAgreementDialog() {
    composeTestRule.setContent {
        val agreementKit = agreementKitHost(testConfig)
        agreementKit.showView()
    }
    
    composeTestRule.onNodeWithText("Accept").assertIsDisplayed()
    composeTestRule.onNodeWithText("Decline").assertIsDisplayed()
}
```

## 🚨 Troubleshooting

### Common Issues

#### 1. Build Errors
- Ensure you have the correct Kotlin version
- Check that Compose is properly enabled
- Verify JitPack repository is added

#### 2. Runtime Errors
- Check internet permission is added
- Verify API endpoint is accessible
- Ensure app ID is correctly configured

#### 3. UI Issues
- Check Material 3 theme is properly set up
- Verify Compose version compatibility
- Ensure proper state management

### Debug Mode

Enable debug logging:

```kotlin
AgreementServiceConfig(
    // ... other config
    timeOut = 10000L, // Increase timeout for debugging
    maxRetry = 1 // Reduce retries for faster debugging
)
```

## 📱 Sample Project

Check out the included sample app in the `app` module for a complete implementation example.

## 🆘 Getting Help

If you encounter issues:

1. Check the [FAQ](FAQ.md)
2. Search [GitHub Issues](https://github.com/YourUsername/AgreementKit/issues)
3. Create a new issue with detailed information
4. Join our [Discord community](https://discord.gg/your-invite)

---

Next: [Configuration Guide](CONFIGURATION.md)
