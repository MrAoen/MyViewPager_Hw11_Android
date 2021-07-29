package com.example.myviewpager_hw11_android

import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.preference.PreferenceManager


fun RefreshFragment(view: View?, prefix: String) {
    view?.apply {
        val txtField = this.findViewById<TextView>(
            context.resources.getIdentifier(
                prefix + "text",
                "id",
                context.packageName
            )
        )
        val tabLayout = this.findViewById<FrameLayout>(
            context.resources.getIdentifier(
                prefix,
                "id",
                context.packageName
            )
        )
        val prefs = PreferenceManager.getDefaultSharedPreferences(this.context)
        txtField.text = prefs.getString(prefix + "TextValue", "No text defined")
        txtField.textSize = prefs.getString(prefix + "TextSize", "16")?.toFloat() ?: 16F
        tabLayout.setBackgroundColor(Color.parseColor(prefs.getString(prefix + "color", "#000000")))
    }
}
