# Frequently Asked Questions (FAQ)

## 🚀 Getting Started

### Q: How do I add AgreementKit to my project?

A: Add the JitPack repository and dependency to your `build.gradle`:

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.YourUsername:AgreementKit:0.0.1'
}
```

### Q: What are the minimum requirements?

A: 
- Android SDK 24+ (Android 7.0)
- Kotlin 2.2.10+
- Jetpack Compose enabled project
- Internet permission

### Q: Do I need to enable Compose in my project?

A: Yes, make sure your `build.gradle` has:

```gradle
android {
    buildFeatures {
        compose = true
    }
}
```

## 🔧 Configuration

### Q: How do I configure the API endpoint?

A: Create a `local.properties` file in your project root:

```properties
API_URL=https://your-api.com/terms-and-conditions
```

### Q: Can I use custom colors and styling?

A: Yes, you can customize almost everything:

```kotlin
AgreementViewConfig(
    popupViewBackGroundColor = Color.White,
    headerTitleColor = Color.Black,
    acceptButtonColor = Color.Blue,
    popupViewCornerRadius = 20.dp
)
```

### Q: How do I support multiple languages?

A: Set the `lang` parameter in your configuration:

```kotlin
AgreementServiceConfig(
    lang = "es", // Spanish
    viewConfig = AgreementViewConfig(
        headerTitle = "Política de Privacidad",
        acceptButtonTitle = "Aceptar"
    )
)
```

## 🎨 UI Customization

### Q: Can I create custom button views?

A: Yes, you can provide custom Composable functions:

```kotlin
AgreementViewConfig(
    acceptButtonView = { onClick ->
        Button(onClick = onClick) {
            Icon(Icons.Default.Check, contentDescription = null)
            Text("Custom Accept")
        }
    }
)
```

### Q: How do I customize the content area?

A: Use the `termsTitleView` property:

```kotlin
AgreementViewConfig(
    termsTitleView = { content ->
        LazyColumn {
            item { Text(content) }
            // Add your custom content
        }
    }
)
```

### Q: Can I change the dialog size and position?

A: Yes, use the `popupViewLayoutModifier`:

```kotlin
AgreementViewConfig(
    popupViewLayoutModifier = Modifier
        .fillMaxWidth(0.8f)
        .fillMaxHeight(0.7f)
)
```

## 🔄 State Management

### Q: How do I handle user actions?

A: Use the `onState` callback:

```kotlin
agreementKitHost(
    config = config,
    onState = { state ->
        when (state) {
            is AgreementState.Action -> {
                if (state.data == "accept") {
                    // User accepted
                } else if (state.data == "decline") {
                    // User declined
                }
            }
        }
    }
)
```

### Q: How do I integrate with ViewModel?

A: Pass the state handling to your ViewModel:

```kotlin
val viewModel: MyViewModel = hiltViewModel()

val agreementKit = agreementKitHost(
    config = config,
    onState = viewModel::handleAgreementState
)
```

### Q: Can I show the agreement conditionally?

A: Yes, control when to show it:

```kotlin
var showAgreement by remember { mutableStateOf(false) }

if (showAgreement) {
    val agreementKit = agreementKitHost(config)
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

## 🌐 API Integration

### Q: What API endpoints do I need?

A: Your backend should provide:

1. **GET** `/terms-and-conditions` - Fetch agreement content
2. **POST** `/terms-and-conditions/{id}` - Send user actions

### Q: What headers are required?

A: The library automatically adds these headers:
- `x-app-id`: Your app identifier
- `x-version`: App version
- `x-device-uuid`: Device identifier
- `x-sdk-version`: Library version

### Q: What's the expected response format?

A: Your API should return:

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

## 🛠️ Troubleshooting

### Q: The dialog doesn't show up

A: Check these common issues:
1. Make sure you call `agreementKit.showView()`
2. Verify your API endpoint is accessible
3. Check that you have internet permission
4. Ensure your app ID is correctly configured

### Q: I get network errors

A: Try these solutions:
1. Increase the timeout: `timeOut = 10000L`
2. Check your API endpoint URL
3. Verify your backend is running
4. Check network connectivity

### Q: The UI doesn't match my app's theme

A: Customize the colors to match your theme:

```kotlin
AgreementViewConfig(
    popupViewBackGroundColor = MaterialTheme.colorScheme.surface,
    headerTitleColor = MaterialTheme.colorScheme.onSurface,
    acceptButtonColor = MaterialTheme.colorScheme.primary
)
```

### Q: The agreement content is not loading

A: Check these:
1. Verify your API is returning the expected format
2. Check the `name` parameter matches your backend
3. Ensure your app ID is correct
4. Check the network logs for errors

## 🔒 Security

### Q: Is user data secure?

A: Yes, the library includes:
- Data encryption using Android Security Crypto
- Secure storage for device IDs
- Proper API authentication headers

### Q: How is the device ID generated?

A: The library automatically generates a unique device ID using:
- Android ID
- Secure random generation
- Encrypted storage

### Q: Can I use a custom device ID?

A: Yes, set it in your configuration:

```kotlin
AgreementServiceConfig(
    deviceId = "your-custom-device-id"
)
```

## 📱 Platform Support

### Q: Does it work on tablets?

A: Yes, the library is responsive and adapts to different screen sizes.

### Q: What about different screen orientations?

A: The dialog automatically adapts to orientation changes.

### Q: Is it compatible with different Android versions?

A: Yes, it supports Android 7.0+ (API 24+).

## 🧪 Testing

### Q: How do I test the agreement flow?

A: Use the provided test utilities:

```kotlin
@Test
fun testAgreementFlow() {
    val config = AgreementServiceConfig(
        version = "1.0.0",
        appId = "test-app-id",
        name = "Test Agreement"
    )
    
    // Test your configuration
    assertThat(config.version).isEqualTo("1.0.0")
}
```

### Q: Can I mock the API responses?

A: Yes, use MockWebServer for testing:

```kotlin
val mockServer = MockWebServer()
mockServer.enqueue(MockResponse().setBody(testResponse))
```

## 🚀 Performance

### Q: Does it impact app performance?

A: The library is lightweight and optimized:
- Minimal dependencies
- Efficient state management
- Optimized UI rendering

### Q: How much memory does it use?

A: The library uses minimal memory and automatically cleans up resources.

### Q: Does it affect app startup time?

A: No, the library only loads when you call `showView()`.

## 🔄 Updates

### Q: How do I update to a new version?

A: Update the version in your `build.gradle`:

```gradle
implementation 'com.github.YourUsername:AgreementKit:0.0.2'
```

### Q: Are updates backward compatible?

A: We follow semantic versioning. Minor updates are backward compatible.

### Q: How do I migrate from an older version?

A: Check the [CHANGELOG](CHANGELOG.md) for migration instructions.

## 💬 Support

### Q: Where can I get help?

A: 
- 📧 Email: support@yourcompany.com
- 🐛 Issues: [GitHub Issues](https://github.com/YourUsername/AgreementKit/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/YourUsername/AgreementKit/discussions)

### Q: How do I report a bug?

A: Create a GitHub issue with:
- Library version
- Android version
- Steps to reproduce
- Expected vs actual behavior
- Logs and screenshots

### Q: Can I contribute to the project?

A: Yes! We welcome contributions. See our [Contributing Guide](CONTRIBUTING.md).

---

Still have questions? [Contact us](mailto:support@yourcompany.com) or [create an issue](https://github.com/YourUsername/AgreementKit/issues).
