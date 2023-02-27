package io.github.greenpeacekt.acplugins.bettercodeblocks

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import com.aliucord.api.SettingsAPI
import com.aliucord.Utils
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.*
import com.aliucord.utils.DimenUtils
import com.aliucord.views.TextInput
import com.discord.app.AppBottomSheet
import com.discord.databinding.WidgetSettingsBinding
import com.discord.stores.`StoreExperiments$getExperimentalAlpha$1`
import com.discord.utilities.color.ColorCompat
import com.discord.views.CheckedSetting
import com.discord.widgets.settings.SettingsViewModel
import com.discord.widgets.settings.WidgetSettings
import com.discord.widgets.settings.developer.*
import com.lytefast.flexinput.R
import java.lang.reflect.Field

import io.github.greenpeacekt.acplugins.BetterCodeBlocks
import io.github.greenpeacekt.acplugins.bettercodeblocks.LangNode
 
class Settings(private val settings: SettingsAPI) : AppBottomSheet() {
    override fun getContentViewResId() = 0
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View {
        val context = inflater.context
        val layout = LinearLayout(context)
        layout.setBackgroundColor(ColorCompat.getThemedColor(context, R.b.colorBackgroundPrimary))

        layout.addView(Utils.createCheckedSetting(context, CheckedSetting.ViewType.SWITCH, "Developer mode", null).apply {
            val key = "dev"
            isChecked = settings.getBool(key, false)
            setOnCheckedListener { settings.setBool(key, it) }
        })
      return layout
    }
    companion object {
        fun get(settings: SettingsAPI, lang: String): Boolean {
            val realLang = GrammarLocatorImpl.realLanguageName(lang)
            if (lang == null) return true
            return getbool(realLang, true) && (BetterCodeBlocks.grammarLocator.grammar(BetterCodeBlocks.prism4j, lang) != null)
        }
        fun getop(settings: SettingsAPI): Boolean {
            return settings.getBool("dev", true)
        }
    }
}