package com.sepanta.controlkit.agreementkit.view.config

import com.sepanta.controlkit.agreementkit.view.ui.AgreementPopover1


enum class AgreementViewStyle {
    Popover1;
    companion object {
        fun checkViewStyle(style: AgreementViewStyle): AgreementContract {
            return when (style) {
                Popover1 -> AgreementPopover1()
            }

        }

    }

}
