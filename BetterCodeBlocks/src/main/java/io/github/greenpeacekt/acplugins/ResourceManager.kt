package io.github.greenpeacekt.acplugins

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.core.graphics.ColorUtils
import com.aliucord.Utils
import com.lytefast.flexinput.R
import java.io.File

object ResourceManager {

    val logger = Logger("Themer")
    val ATTR_MAPPINGS = HashMap<String, Array<String>>()
    private val colorsByName = HashMap<String, Int>()
    private val colorsById = HashMap<Int, Int>()

    internal fun putColor(name: String, color: Int) {
        val id = Utils.getResId(name, "color")
        if (id != 0) {
            colorsById[id] = color
            colorsByName[name] = color
        } else {
            when (name) {
                "statusbar", "input_background", "active_channel", "blocked_bg" -> colorsByName[name] = color
                else -> logger.warn("Unrecognised colour $name")
            }
        }
    }

    internal fun putColors(names: Array<String>, color: Int) = names.forEach {
        putColor(it, color)
    }
    
    internal fun putAttr(name: String?, color: Int) =
        ATTR_MAPPINGS[name]?.forEach {
            setAttr(it, color)
        }

    internal fun putAttrs(attrs: Array<String>, color: Int) {
        attrs.forEach {
            if (it.startsWith("__alpha_10_")) {
                val prefixLen = 11
                setAttr(it.substring(prefixLen), ColorUtils.setAlphaComponent(color, 0x1a))
            } else setAttr(it, color)
        }
    }

    private fun setAttr(attr: String, color: Int) {
        val id = Utils.getResId(attr, "attr")
        if (id == 0) logger.warn("No such attribute: $attr") else attrs[id] = color
    }
    
    fun clean() {
        colorsByName.clear()
        colorsById.clear()
        attrs.clear()
    }
}