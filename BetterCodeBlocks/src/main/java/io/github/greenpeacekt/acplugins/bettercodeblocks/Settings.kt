package io.github.greenpeacekt.acplugins.bettercodeblocks

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.aliucord.Constants
import com.aliucord.api.SettingsAPI
import com.aliucord.fragments.SettingsPage
import com.discord.views.CheckedSetting
import com.lytefast.flexinput.R
import io.github.greenpeacekt.acplugins.BetterCodeBlocks

 
class Settings(private val settings: SettingsAPI) : SettingsPage() {
    @SuppressLint("SetTextI18n")
    override fun onViewBound(view: View) {
        super.onViewBound(view)
        setActionBarTitle("BetterCodeBlocks")
        setPadding(0)

        val context = view.context
        val layout = linearLayout

        layout.addView(com.aliucord.Utils.createCheckedSetting(context, CheckedSetting.ViewType.SWITCH, "Developer mode", null).apply {
            val key = "dev"
            isChecked = settings.getBool(key, false)
            setOnCheckedListener { settings.setBool(key, it) }
        })
    }
    companion object {
        fun get(settings: SettingsAPI): Boolean {
            val keya = "dev"
            return settings.getBool(keya, true)
        }
    }
}