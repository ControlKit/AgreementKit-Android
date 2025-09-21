package com.sepanta.controlkit.agreementkit.view.config


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class AgreementViewConfig(
    var viewStyle: AgreementViewStyle = AgreementViewStyle.Popover1,
    var popupViewLayoutModifier: Modifier? = null,
    var popupViewBackGroundColor: Color? = null,
    var popupViewCornerRadius: Dp? = null,

    var headerTitle: String = "Terms of Service",
    var headerTitleColor: Color? = null,
    var headerTitleLayoutModifier:   Modifier? = null,
    var headerTitleView: @Composable ((String) -> Unit)? = null,

    var buttonsLayoutModifier:   Modifier? = null,

    var termsTitleViewStyle: TextStyle? = null,
    var termsTitleView: @Composable ((String) -> Unit)? = null,
    var scrollBarColor: Color? = null,

    var termsTitle: String = ("Terms of Service\n"),

    var acceptButtonTitle: String = "Accept",
    var declineButtonTitle: String = "Decline",
    var updateButtonTitleColor: Color? = null,
    var acceptButtonColor: Color? = null,
    var declineButtonColor: Color? = null,
    var declineButtonCornerColor: Color? = null,
    var acceptButtonCornerRadius: Dp? = null,
    var declineButtonCornerRadius: Dp? = null,
    var updateButtonBorderColor: Color? = null,
    var acceptButtonLayoutModifier:   Modifier? = null,
    var declineButtonLayoutModifier:   Modifier? = null,

    var acceptButtonView: (@Composable (libraryOnClick: () -> Unit) -> Unit)? = null,
    var declineButtonView: (@Composable (libraryOnClick: () -> Unit) -> Unit)? = null,
)
