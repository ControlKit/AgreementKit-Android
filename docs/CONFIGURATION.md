# Configuration Guide

This guide covers all configuration options available in AgreementKit.

## 🔧 Service Configuration

The `AgreementServiceConfig` class controls the core behavior of the library.

### Basic Configuration

```kotlin
AgreementServiceConfig(
    version = "1.0.0",                    // Required: Your app version
    appId = "your-unique-app-id",         // Required: Unique app identifier
    name = "Privacy Policy",              // Required: Agreement name
    lang = "en",                          // Optional: Language code (default: "en")
    deviceId = "custom-device-id",        // Optional: Custom device ID
    timeOut = 5000L,                      // Optional: API timeout in ms (default: 5000)
    timeRetryThreadSleep = 1000L,         // Optional: Retry delay in ms (default: 1000)
    maxRetry = 5,                         // Optional: Max retry attempts (default: 5)
    viewConfig = AgreementViewConfig(...) // Required: UI configuration
)
```

### Configuration Properties

| Property | Type | Required | Default | Description |
|----------|------|----------|---------|-------------|
| `version` | String | ✅ | - | Your application version |
| `appId` | String | ✅ | - | Unique identifier for your app |
| `name` | String | ✅ | - | Name of the agreement (e.g., "Privacy Policy") |
| `lang` | String | ❌ | "en" | Language code for localization |
| `deviceId` | String? | ❌ | null | Custom device identifier |
| `timeOut` | Long | ❌ | 5000L | API request timeout in milliseconds |
| `timeRetryThreadSleep` | Long | ❌ | 1000L | Delay between retry attempts |
| `maxRetry` | Int | ❌ | 5 | Maximum number of retry attempts |
| `viewConfig` | AgreementViewConfig | ✅ | - | UI configuration object |

### Example Configurations

#### Basic Privacy Policy
```kotlin
val privacyConfig = AgreementServiceConfig(
    version = "1.0.0",
    appId = "com.yourcompany.yourapp",
    name = "Privacy Policy",
    viewConfig = AgreementViewConfig()
)
```

#### Terms of Service with Custom Settings
```kotlin
val termsConfig = AgreementServiceConfig(
    version = "2.1.0",
    appId = "com.yourcompany.yourapp",
    name = "Terms of Service",
    lang = "es", // Spanish
    timeOut = 10000L, // 10 seconds
    maxRetry = 3,
    viewConfig = AgreementViewConfig(
        headerTitle = "Términos de Servicio",
        acceptButtonTitle = "Aceptar",
        declineButtonTitle = "Rechazar"
    )
)
```

## 🎨 View Configuration

The `AgreementViewConfig` class controls the appearance and behavior of the UI.

### Basic View Configuration

```kotlin
AgreementViewConfig(
    viewStyle = AgreementViewStyle.Popover1,
    
    // Dialog appearance
    popupViewLayoutModifier = null,
    popupViewBackGroundColor = null,
    popupViewCornerRadius = null,
    
    // Header customization
    headerTitle = "Terms of Service",
    headerTitleColor = null,
    headerTitleLayoutModifier = null,
    headerTitleView = null,
    
    // Content styling
    termsTitle = "Terms of Service\n",
    termsTitleViewStyle = null,
    termsTitleView = null,
    scrollBarColor = null,
    
    // Button customization
    acceptButtonTitle = "Accept",
    declineButtonTitle = "Decline",
    acceptButtonColor = null,
    declineButtonColor = null,
    acceptButtonCornerRadius = null,
    declineButtonCornerRadius = null,
    acceptButtonLayoutModifier = null,
    declineButtonLayoutModifier = null,
    acceptButtonView = null,
    declineButtonView = null,
    
    // Layout modifiers
    buttonsLayoutModifier = null
)
```

### View Configuration Properties

#### Dialog Appearance
| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `viewStyle` | AgreementViewStyle | Popover1 | UI style variant |
| `popupViewLayoutModifier` | Modifier? | null | Custom dialog layout |
| `popupViewBackGroundColor` | Color? | null | Dialog background color |
| `popupViewCornerRadius` | Dp? | null | Dialog corner radius |

#### Header Customization
| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `headerTitle` | String | "Terms of Service" | Header title text |
| `headerTitleColor` | Color? | null | Header title color |
| `headerTitleLayoutModifier` | Modifier? | null | Header layout modifier |
| `headerTitleView` | Composable? | null | Custom header view |

#### Content Styling
| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `termsTitle` | String | "Terms of Service\n" | Default content text |
| `termsTitleViewStyle` | TextStyle? | null | Content text style |
| `termsTitleView` | Composable? | null | Custom content view |
| `scrollBarColor` | Color? | null | Scrollbar color |

#### Button Customization
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

## 🎯 View Styles

Currently, the library supports one view style:

### Popover1
A modern dialog-style popup with:
- Rounded corners
- Material Design 3 styling
- Scrollable content area
- Two-button layout (Accept/Decline)

```kotlin
AgreementViewConfig(
    viewStyle = AgreementViewStyle.Popover1
)
```

## 🎨 Styling Examples

### Material 3 Theme Integration
```kotlin
AgreementViewConfig(
    popupViewBackGroundColor = MaterialTheme.colorScheme.surface,
    headerTitleColor = MaterialTheme.colorScheme.onSurface,
    acceptButtonColor = MaterialTheme.colorScheme.primary,
    declineButtonColor = MaterialTheme.colorScheme.outline,
    popupViewCornerRadius = 16.dp
)
```

### Custom Color Scheme
```kotlin
AgreementViewConfig(
    popupViewBackGroundColor = Color(0xFFF5F5F5),
    headerTitleColor = Color(0xFF333333),
    acceptButtonColor = Color(0xFF4CAF50),
    declineButtonColor = Color.Transparent,
    scrollBarColor = Color(0xFFCCCCCC)
)
```

### Custom Typography
```kotlin
AgreementViewConfig(
    termsTitleViewStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = Color(0xFF666666),
        fontFamily = FontFamily.SansSerif
    )
)
```

## 🔧 Advanced Customization

### Custom Header View
```kotlin
AgreementViewConfig(
    headerTitleView = { title ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
)
```

### Custom Content View
```kotlin
AgreementViewConfig(
    termsTitleView = { content ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 24.sp
                )
            }
            // Add custom content sections
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Additional Information",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
)
```

### Custom Button Views
```kotlin
AgreementViewConfig(
    acceptButtonView = { onClick ->
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "I Accept",
                style = MaterialTheme.typography.titleMedium
            )
        }
    },
    declineButtonView = { onClick ->
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF666666)
            ),
            border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Cancel",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
)
```

### Custom Layout Modifiers
```kotlin
AgreementViewConfig(
    popupViewLayoutModifier = Modifier
        .fillMaxHeight(0.85f)
        .fillMaxWidth(0.95f)
        .padding(16.dp),
    
    headerTitleLayoutModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 16.dp),
    
    buttonsLayoutModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 16.dp)
        .height(56.dp)
)
```

## 🌍 Localization

### Multi-language Support
```kotlin
// English
val englishConfig = AgreementServiceConfig(
    version = "1.0.0",
    appId = "your-app-id",
    name = "Privacy Policy",
    lang = "en",
    viewConfig = AgreementViewConfig(
        headerTitle = "Privacy Policy",
        acceptButtonTitle = "Accept",
        declineButtonTitle = "Decline"
    )
)

// Spanish
val spanishConfig = AgreementServiceConfig(
    version = "1.0.0",
    appId = "your-app-id",
    name = "Política de Privacidad",
    lang = "es",
    viewConfig = AgreementViewConfig(
        headerTitle = "Política de Privacidad",
        acceptButtonTitle = "Aceptar",
        declineButtonTitle = "Rechazar"
    )
)

// French
val frenchConfig = AgreementServiceConfig(
    version = "1.0.0",
    appId = "your-app-id",
    name = "Politique de Confidentialité",
    lang = "fr",
    viewConfig = AgreementViewConfig(
        headerTitle = "Politique de Confidentialité",
        acceptButtonTitle = "Accepter",
        declineButtonTitle = "Refuser"
    )
)
```

## 🔄 Dynamic Configuration

### Runtime Configuration Updates
```kotlin
class AgreementManager {
    private var currentConfig: AgreementServiceConfig? = null
    
    fun updateConfiguration(newConfig: AgreementServiceConfig) {
        currentConfig = newConfig
    }
    
    fun getConfigurationForUser(userType: String): AgreementServiceConfig {
        return when (userType) {
            "premium" -> createPremiumConfig()
            "basic" -> createBasicConfig()
            else -> createDefaultConfig()
        }
    }
    
    private fun createPremiumConfig(): AgreementServiceConfig {
        return AgreementServiceConfig(
            version = "1.0.0",
            appId = "your-app-id",
            name = "Premium Terms",
            viewConfig = AgreementViewConfig(
                headerTitle = "Premium Terms of Service",
                acceptButtonColor = Color(0xFF9C27B0), // Purple for premium
                popupViewBackGroundColor = Color(0xFFF3E5F5)
            )
        )
    }
}
```

## 📱 Responsive Design

### Screen Size Adaptations
```kotlin
@Composable
fun createResponsiveConfig(): AgreementViewConfig {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    
    return AgreementViewConfig(
        popupViewLayoutModifier = when {
            screenWidth < 600.dp -> Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.9f)
            else -> Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.8f)
        },
        popupViewCornerRadius = when {
            screenWidth < 600.dp -> 12.dp
            else -> 20.dp
        }
    )
}
```

---

Next: [Usage Examples](USAGE_EXAMPLES.md)
