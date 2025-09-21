# Usage Examples

This guide provides comprehensive examples of how to use AgreementKit in various scenarios.

## 🚀 Basic Examples

### Simple Privacy Policy
```kotlin
@Composable
fun SimplePrivacyPolicy() {
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Privacy Policy",
            viewConfig = AgreementViewConfig()
        )
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

### Terms of Service with Custom Styling
```kotlin
@Composable
fun CustomTermsOfService() {
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Terms of Service",
            viewConfig = AgreementViewConfig(
                headerTitle = "Terms of Service",
                acceptButtonTitle = "I Agree",
                declineButtonTitle = "Cancel",
                acceptButtonColor = Color(0xFF4CAF50),
                popupViewCornerRadius = 20.dp
            )
        ),
        onDismiss = {
            // Handle dismissal
        }
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

## 🎯 Real-World Scenarios

### 1. App Launch Agreement
Show agreement when the app launches for the first time:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MyAppTheme {
                val isFirstLaunch = remember {
                    getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                        .getBoolean("is_first_launch", true)
                }
                
                if (isFirstLaunch) {
                    FirstLaunchAgreement()
                } else {
                    MainAppContent()
                }
            }
        }
    }
}

@Composable
fun FirstLaunchAgreement() {
    val context = LocalContext.current
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Welcome Agreement",
            viewConfig = AgreementViewConfig(
                headerTitle = "Welcome to Our App",
                acceptButtonTitle = "Get Started",
                declineButtonTitle = "Exit App"
            )
        ),
        onDismiss = {
            // Exit app if declined
            (context as Activity).finish()
        },
        onState = { state ->
            when (state) {
                is AgreementState.Action -> {
                    if (state.data == "accept") {
                        // Mark as not first launch
                        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                            .edit()
                            .putBoolean("is_first_launch", false)
                            .apply()
                    }
                }
            }
        }
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

### 2. Feature-Specific Agreements
Show different agreements based on the feature being accessed:

```kotlin
@Composable
fun FeatureAgreement(feature: String) {
    val agreementKit = agreementKitHost(
        config = when (feature) {
            "camera" -> AgreementServiceConfig(
                version = "1.0.0",
                appId = "com.example.myapp",
                name = "Camera Permission Agreement",
                viewConfig = AgreementViewConfig(
                    headerTitle = "Camera Access",
                    acceptButtonTitle = "Allow Camera",
                    declineButtonTitle = "Skip"
                )
            )
            "location" -> AgreementServiceConfig(
                version = "1.0.0",
                appId = "com.example.myapp",
                name = "Location Permission Agreement",
                viewConfig = AgreementViewConfig(
                    headerTitle = "Location Access",
                    acceptButtonTitle = "Allow Location",
                    declineButtonTitle = "Skip"
                )
            )
            else -> AgreementServiceConfig(
                version = "1.0.0",
                appId = "com.example.myapp",
                name = "General Agreement"
            )
        }
    )
    
    LaunchedEffect(feature) {
        agreementKit.showView()
    }
}
```

### 3. Version-Based Agreements
Show agreements when the app is updated:

```kotlin
@Composable
fun VersionBasedAgreement() {
    val context = LocalContext.current
    val currentVersion = "1.2.0"
    val lastAcceptedVersion = remember {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getString("last_accepted_version", "1.0.0") ?: "1.0.0"
    }
    
    if (currentVersion != lastAcceptedVersion) {
        val agreementKit = agreementKitHost(
            config = AgreementServiceConfig(
                version = currentVersion,
                appId = "com.example.myapp",
                name = "Updated Terms",
                viewConfig = AgreementViewConfig(
                    headerTitle = "Updated Terms of Service",
                    acceptButtonTitle = "Accept Updates",
                    declineButtonTitle = "Keep Previous Version"
                )
            ),
            onState = { state ->
                when (state) {
                    is AgreementState.Action -> {
                        if (state.data == "accept") {
                            // Update accepted version
                            context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                                .edit()
                                .putString("last_accepted_version", currentVersion)
                                .apply()
                        }
                    }
                }
            }
        )
        
        LaunchedEffect(Unit) {
            agreementKit.showView()
        }
    }
}
```

## 🎨 Advanced UI Examples

### 1. Custom Themed Agreement
```kotlin
@Composable
fun ThemedAgreement() {
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Themed Agreement",
            viewConfig = AgreementViewConfig(
                popupViewBackGroundColor = MaterialTheme.colorScheme.surface,
                headerTitleColor = MaterialTheme.colorScheme.primary,
                acceptButtonColor = MaterialTheme.colorScheme.primary,
                declineButtonColor = MaterialTheme.colorScheme.outline,
                popupViewCornerRadius = 24.dp,
                acceptButtonCornerRadius = 12.dp,
                declineButtonCornerRadius = 12.dp,
                scrollBarColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )
        )
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

### 2. Custom Button Views
```kotlin
@Composable
fun CustomButtonAgreement() {
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Custom Buttons Agreement",
            viewConfig = AgreementViewConfig(
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
        )
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

### 3. Custom Content View
```kotlin
@Composable
fun CustomContentAgreement() {
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Custom Content Agreement",
            viewConfig = AgreementViewConfig(
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
                        
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider(color = MaterialTheme.colorScheme.outline)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        
                        item {
                            Text(
                                text = "Key Points:",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "• Your data is secure\n• We respect your privacy\n• You can opt out anytime",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            )
        )
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

## 🔄 State Management Examples

### 1. With ViewModel
```kotlin
class AgreementViewModel : ViewModel() {
    private val _agreementState = MutableStateFlow<AgreementState?>(null)
    val agreementState = _agreementState.asStateFlow()
    
    private val _isAgreementAccepted = MutableStateFlow(false)
    val isAgreementAccepted = _isAgreementAccepted.asStateFlow()
    
    fun handleAgreementState(state: AgreementState) {
        _agreementState.value = state
        
        when (state) {
            is AgreementState.Action -> {
                if (state.data == "accept") {
                    _isAgreementAccepted.value = true
                }
            }
            is AgreementState.Error -> {
                // Handle error
            }
        }
    }
}

@Composable
fun AgreementWithViewModel() {
    val viewModel: AgreementViewModel = hiltViewModel()
    val agreementState by viewModel.agreementState.collectAsState()
    val isAccepted by viewModel.isAgreementAccepted.collectAsState()
    
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "ViewModel Agreement"
        ),
        onState = viewModel::handleAgreementState
    )
    
    LaunchedEffect(Unit) {
        if (!isAccepted) {
            agreementKit.showView()
        }
    }
    
    // Handle agreement state changes
    LaunchedEffect(agreementState) {
        when (agreementState) {
            is AgreementState.Action -> {
                if (agreementState.data == "accept") {
                    // Navigate to main content
                }
            }
        }
    }
}
```

### 2. With Navigation
```kotlin
@Composable
fun AgreementWithNavigation() {
    val navController = rememberNavController()
    
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Navigation Agreement"
        ),
        onState = { state ->
            when (state) {
                is AgreementState.Action -> {
                    if (state.data == "accept") {
                        navController.navigate("main_content")
                    } else {
                        navController.navigate("welcome_screen")
                    }
                }
            }
        }
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

## 🌍 Multi-language Examples

### 1. Language Selection
```kotlin
@Composable
fun MultiLanguageAgreement() {
    var selectedLanguage by remember { mutableStateOf("en") }
    
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = when (selectedLanguage) {
                "es" -> "Política de Privacidad"
                "fr" -> "Politique de Confidentialité"
                "de" -> "Datenschutzrichtlinie"
                else -> "Privacy Policy"
            },
            lang = selectedLanguage,
            viewConfig = AgreementViewConfig(
                headerTitle = when (selectedLanguage) {
                    "es" -> "Política de Privacidad"
                    "fr" -> "Politique de Confidentialité"
                    "de" -> "Datenschutzrichtlinie"
                    else -> "Privacy Policy"
                },
                acceptButtonTitle = when (selectedLanguage) {
                    "es" -> "Aceptar"
                    "fr" -> "Accepter"
                    "de" -> "Akzeptieren"
                    else -> "Accept"
                },
                declineButtonTitle = when (selectedLanguage) {
                    "es" -> "Rechazar"
                    "fr" -> "Refuser"
                    "de" -> "Ablehnen"
                    else -> "Decline"
                }
            )
        )
    )
    
    Column {
        // Language selector
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("en" to "English", "es" to "Español", "fr" to "Français", "de" to "Deutsch")
                .forEach { (code, name) ->
                    FilterChip(
                        onClick = { selectedLanguage = code },
                        label = { Text(name) },
                        selected = selectedLanguage == code
                    )
                }
        }
        
        // Show agreement
        LaunchedEffect(selectedLanguage) {
            agreementKit.showView()
        }
    }
}
```

## 📱 Responsive Design Examples

### 1. Screen Size Adaptation
```kotlin
@Composable
fun ResponsiveAgreement() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Responsive Agreement",
            viewConfig = AgreementViewConfig(
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
                },
                buttonsLayoutModifier = when {
                    screenWidth < 600.dp -> Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    else -> Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                }
            )
        )
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
}
```

## 🔧 Error Handling Examples

### 1. Network Error Handling
```kotlin
@Composable
fun AgreementWithErrorHandling() {
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    
    val agreementKit = agreementKitHost(
        config = AgreementServiceConfig(
            version = "1.0.0",
            appId = "com.example.myapp",
            name = "Error Handling Agreement",
            timeOut = 5000L,
            maxRetry = 3
        ),
        onState = { state ->
            when (state) {
                is AgreementState.Error -> {
                    showError = true
                    errorMessage = state.data ?: "Unknown error occurred"
                }
                is AgreementState.NoContent -> {
                    showError = true
                    errorMessage = "No agreement content available"
                }
            }
        }
    )
    
    LaunchedEffect(Unit) {
        agreementKit.showView()
    }
    
    if (showError) {
        AlertDialog(
            onDismissRequest = { showError = false },
            title = { Text("Error") },
            text = { Text(errorMessage) },
            confirmButton = {
                TextButton(onClick = { showError = false }) {
                    Text("OK")
                }
            }
        )
    }
}
```

## 🧪 Testing Examples

### 1. Unit Test
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
        assertThat(config.name).isEqualTo("Test Agreement")
    }
    
    @Test
    fun `test view configuration`() {
        val viewConfig = AgreementViewConfig(
            headerTitle = "Test Title",
            acceptButtonTitle = "Test Accept",
            declineButtonTitle = "Test Decline"
        )
        
        assertThat(viewConfig.headerTitle).isEqualTo("Test Title")
        assertThat(viewConfig.acceptButtonTitle).isEqualTo("Test Accept")
        assertThat(viewConfig.declineButtonTitle).isEqualTo("Test Decline")
    }
}
```

### 2. UI Test
```kotlin
@Test
fun testAgreementDialog() {
    composeTestRule.setContent {
        val agreementKit = agreementKitHost(
            AgreementServiceConfig(
                version = "1.0.0",
                appId = "test-app-id",
                name = "Test Agreement"
            )
        )
        agreementKit.showView()
    }
    
    // Verify dialog is displayed
    composeTestRule.onNodeWithText("Test Agreement").assertIsDisplayed()
    
    // Verify buttons are present
    composeTestRule.onNodeWithText("Accept").assertIsDisplayed()
    composeTestRule.onNodeWithText("Decline").assertIsDisplayed()
    
    // Test button interactions
    composeTestRule.onNodeWithText("Accept").performClick()
    // Add assertions for expected behavior
}
```

---

Next: [API Reference](API_REFERENCE.md)
