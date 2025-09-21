package com.sepanta.controlkit.agreementkit.view.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sepanta.controlkit.agreementkit.service.model.DataResponse
import com.sepanta.controlkit.agreementkit.theme.Black50
import com.sepanta.controlkit.agreementkit.theme.Black60
import com.sepanta.controlkit.agreementkit.theme.Blue40
import com.sepanta.controlkit.agreementkit.theme.Typography
import com.sepanta.controlkit.agreementkit.view.config.AgreementContract
import com.sepanta.controlkit.agreementkit.view.config.AgreementViewConfig
import com.sepanta.controlkit.agreementkit.view.viewmodel.AgreementViewModel
import com.sepanta.controlkit.agreementkit.theme.White80
import com.sepanta.controlkit.agreementkit.view.ui.widgets.CustomScrollbar

class AgreementPopover1 : AgreementContract {

    @Composable
    override fun ShowView(
        config: AgreementViewConfig,
        response: DataResponse,
        viewModel: AgreementViewModel
    ) {
        Log.i("hjkkhjkhjk", "AgreementPopover1: ")

        val openDialog = viewModel.openDialog.collectAsState()
        if (!openDialog.value) return

        Dialog(
            onDismissRequest = { viewModel.dismissDialog() },
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false,
            )
        ) {

            Surface(
                modifier = config.popupViewLayoutModifier ?: Modifier
                    .fillMaxHeight(0.90f)
                    .fillMaxWidth(0.98f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(
                    config.popupViewCornerRadius ?: 15.dp
                ),
                color = config.popupViewBackGroundColor ?: White80
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    HeaderTitle(config, response)
                    TermsTitle(
                        config, response, Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    )
                    Buttons(config, response, viewModel)
                }


            }

        }

    }

    @Composable
    private fun TermsTitle(
        config: AgreementViewConfig,
        response: DataResponse,
        modifier: Modifier
    ) {
        CustomScrollbar(
            modifier = modifier.padding(16.dp),
            color = config.scrollBarColor,
            textView = {
                config.termsTitleView?.let { textView ->
                    textView((response.description ?: config.termsTitle))
                } ?: Text(
                    response.description ?: config.termsTitle,
                    style = config.termsTitleViewStyle ?: Typography.bodyMedium,
                    textAlign = config.termsTitleViewStyle?.textAlign ?: TextAlign.Start
                    )
            })

    }

    @Composable
    private fun HeaderTitle(
        config: AgreementViewConfig,
        response: DataResponse
    ) {
        Surface(
            modifier = config.headerTitleLayoutModifier ?: Modifier
                .padding(top = 30.dp, end = 5.dp, start = 5.dp)
                .wrapContentSize(),
            color = Transparent,

            ) {

            config.headerTitleView?.let { textView ->
                textView((response.title ?: config.headerTitle))
            } ?: Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 10.dp, start = 10.dp),
                    text = response.title ?: config.headerTitle,
                    style = Typography.titleLarge,
                    color = config.headerTitleColor ?: Typography.titleLarge.color

                )

            }

        }

    }


    @Composable
    private fun Buttons(
        config: AgreementViewConfig,
        response: DataResponse,
        viewModel: AgreementViewModel,

        ) {
        Row(
            modifier = config.buttonsLayoutModifier ?: Modifier.padding(
                start = 24.dp, end = 24.dp, bottom = 34.dp, top = 26.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonDecline(config, viewModel, response)
            Spacer(modifier = Modifier.width(10.dp))
            ButtonAccept(config, viewModel, response)

        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun RowScope.ButtonAccept(
        config: AgreementViewConfig,
        viewModel: AgreementViewModel,
        response: DataResponse

    ) {
        val onClickAction: () -> Unit = {
            viewModel.submitAccept()
        }

        config.acceptButtonView?.let { button ->
            button(onClickAction)
        } ?: Button(
            onClick = onClickAction,
            shape = RoundedCornerShape(config.acceptButtonCornerRadius ?: 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = config.acceptButtonColor ?: Blue40
            ),
            modifier = config.acceptButtonLayoutModifier ?: Modifier
                .weight(1f)
                .height(52.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = response.buttonTitle ?: config.acceptButtonTitle,
                style = Typography.titleMedium,
                color = Color.White
            )
        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun RowScope.ButtonDecline(
        config: AgreementViewConfig,
        viewModel: AgreementViewModel,
        response: DataResponse,

        ) {


        val onClickAction: () -> Unit = {
            viewModel.dismissDialog()
        }
        config.declineButtonView?.let { button ->
            button(onClickAction)
        } ?: Button(
            onClick = onClickAction,
            shape = RoundedCornerShape(config.declineButtonCornerRadius ?: 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = config.declineButtonColor ?: Color.Transparent
            ),
            border = BorderStroke(1.dp, Black50),
            modifier = config.declineButtonLayoutModifier ?: Modifier
                .weight(1f)
                .height(52.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = response.declineButtonTitle ?: config.declineButtonTitle,
                style = Typography.titleMedium,
                color = Black60
            )
        }


    }
}