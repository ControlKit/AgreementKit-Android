package com.sepanta.controlkit.agreementexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

        enableEdgeToEdge()
        setContent {

            Surface(Modifier.fillMaxSize()) {

                val agreementKit = agreementKitHost(
                    AgreementServiceConfig(
                        version = "1.0.0",
                        appId = "9fee1663-e80e-46ad-8cd9-357263375a9c",
                        name = "Privacy Policy1",
                        viewConfig = AgreementViewConfig(
                            AgreementViewStyle.Popover1,

                        )
                    ), onDismiss = {

                    },
                )
                agreementKit.showView()
            }


        }
    }
}
