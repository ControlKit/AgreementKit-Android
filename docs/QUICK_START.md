# Quick Start Guide

Get up and running with AgreementKit in just a few minutes!

## 🚀 5-Minute Setup

### Step 1: Add Dependency

Add to your app's `build.gradle`:

```gradle
dependencies {
    implementation 'com.github.ControlKit:AgreementKit-Android:0.0.1'
}
```

### Step 2: Basic Implementation

```kotlin
import com.sepanta.controlkit.agreementkit.agreementKitHost
import com.sepanta.controlkit.agreementkit.service.config.AgreementServiceConfig
import com.sepanta.controlkit.agreementkit.view.config.AgreementViewConfig
import com.sepanta.controlkit.agreementkit.view.config.AgreementViewStyle

@Composable
fun MyApp() {
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "your-app-id",
            name = "Privacy Policy",
            viewConfig = AgreementViewConfig()
        )
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

### Step 3: Add Internet Permission

Add to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

That's it! 🎉

## 🎨 Customize Your Agreement

### Change Colors and Text

```kotlin
AgreementViewConfig(
    headerTitle = "Welcome to Our App",
    acceptButtonTitle = "Get Started",
    declineButtonTitle = "Cancel",
    acceptButtonColor = Color(0xFF4CAF50),
    popupViewCornerRadius = 20.dp
)
```

### Add Custom Styling

```kotlin
AgreementViewConfig(
    popupViewBackGroundColor = MaterialTheme.colorScheme.surface,
    headerTitleColor = MaterialTheme.colorScheme.primary,
    acceptButtonColor = MaterialTheme.colorScheme.primary,
    declineButtonColor = MaterialTheme.colorScheme.outline
)
```

## 🔄 Handle User Actions

```kotlin
agreementKitHost(
    config = config,
    onState = { state ->
        when (state) {
            is AgreementState.Action -> {
                if (state.data == "accept") {
                    // User accepted - proceed to app
                } else {
                    // User declined - handle accordingly
                }
            }
        }
    }
)
```

## 🌍 Multi-language Support

```kotlin
AgreementServiceConfig(
    version = "1.0.0",
    appId = "your-app-id",
    name = "Privacy Policy",
    lang = "es", // Spanish
    viewConfig = AgreementViewConfig(
        headerTitle = "Política de Privacidad",
        acceptButtonTitle = "Aceptar",
        declineButtonTitle = "Rechazar"
    )
)
```

## 📱 Common Use Cases

### App Launch Agreement
```kotlin
@Composable
fun AppLaunch() {
    val isFirstLaunch = remember { 
        // Check if first launch
        true 
    }
    
    if (isFirstLaunch) {
        val agreementKit = agreementKitHost(
            AgreementServiceConfig(
                version = "1.0.0",
                appId = "your-app-id",
                name = "Welcome Agreement"
            )
        )
        LaunchedEffect(Unit) {
            agreementKit.showView()
        }
    }
}
```

### Feature-Specific Agreement
```kotlin
@Composable
fun CameraFeature() {
    val agreementKit = agreementKitHost(
        AgreementServiceConfig(
            version = "1.0.0",
            appId = "your-app-id",
            name = "Camera Permission",
            viewConfig = AgreementViewConfig(
                headerTitle = "Camera Access",
                acceptButtonTitle = "Allow Camera",
                declineButtonTitle = "Skip"
            )
        )
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

## 🔧 Configuration Options

### Service Configuration
```kotlin
AgreementServiceConfig(
    version = "1.0.0",           // Your app version
    appId = "your-app-id",       // Unique app identifier
    name = "Privacy Policy",     // Agreement name
    lang = "en",                 // Language code
    timeOut = 5000L,            // API timeout (ms)
    maxRetry = 5                // Max retry attempts
)
```

### View Configuration
```kotlin
AgreementViewConfig(
    viewStyle = AgreementViewStyle.Popover1,
    headerTitle = "Terms of Service",
    acceptButtonTitle = "Accept",
    declineButtonTitle = "Decline",
    popupViewCornerRadius = 15.dp,
    acceptButtonColor = Color.Blue
)
```

## 🚨 Troubleshooting

### Common Issues

**Dialog doesn't show:**
- Check if you called `agreementKit.showView()`
- Verify internet permission is added
- Ensure API endpoint is accessible

**Network errors:**
- Increase timeout: `timeOut = 10000L`
- Check your API endpoint URL
- Verify backend is running

**UI doesn't match theme:**
- Use Material theme colors:
  ```kotlin
  popupViewBackGroundColor = MaterialTheme.colorScheme.surface
  ```

## 📚 Next Steps

- **Full Setup**: [Setup Guide](SETUP.md)
- **Configuration**: [Configuration Guide](CONFIGURATION.md)
- **Examples**: [Usage Examples](USAGE_EXAMPLES.md)
- **API Reference**: [API Reference](API_REFERENCE.md)
- **FAQ**: [Frequently Asked Questions](FAQ.md)

## 🆘 Need Help?

- 📧 Email: support@yourcompany.com
- 🐛 Issues: [GitHub Issues](https://github.com/ControlKit/AgreementKit-Android/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/ControlKit/AgreementKit-Android/discussions)

---

**Ready to go?** Check out the [complete documentation](README.md) for advanced features and customization options!
