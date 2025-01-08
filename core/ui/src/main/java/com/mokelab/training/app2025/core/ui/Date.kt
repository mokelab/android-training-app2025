package com.mokelab.training.app2025.core.ui

import android.icu.text.DateFormat
import java.util.Date

internal fun Date.format(): String {
    return DateFormat.getDateInstance().format(this)
}