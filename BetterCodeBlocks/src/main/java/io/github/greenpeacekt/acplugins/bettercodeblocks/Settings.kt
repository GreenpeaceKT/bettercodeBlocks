package io.github.greenpeacekt.acplugins.bettercodeblocks

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.aliucord.Constants
import com.aliucord.api.SettingsAPI
import com.discord.app.AppBottomSheet
import com.discord.views.CheckedSetting
import com.discord.utilities.color.ColorCompat
import com.lytefast.flexinput.R
import io.github.greenpeacekt.acplugins.BetterCodeBlocks
import io.github.greenpeacekt.acplugins.bettercodeblocks.LangNode
 
class Settings(private val settings: SettingsAPI) : AppBottomSheet() {
    override fun getContentViewResId() = 0
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View {
        val context = inflater.context
        val layout = linearLayout(context)
        layout.setBackgroundColor(ColorCompat.getThemedColor(context, R.b.colorBackgroundPrimary))

        layout.addView(com.aliucord.Utils.createCheckedSetting(context, CheckedSetting.ViewType.SWITCH, "Developer mode", null).apply {
            val key = "dev"
            isChecked = settings.getBool(key, false)
            setOnCheckedListener { settings.setBool(key, it) }
        })
      return layout
    }
    companion object {
        fun get(settings: SettingsAPI): Boolean {
            return settings.getBool("dev",true)
        }
    }
}