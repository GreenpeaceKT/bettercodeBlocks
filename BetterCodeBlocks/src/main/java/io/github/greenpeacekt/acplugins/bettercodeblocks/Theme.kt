package io.github.greenpeacekt.acplugins.bettercodeblocks

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.core.graphics.ColorUtils
import com.aliucord.Utils
import com.lytefast.flexinput.R
import java.io.File

private var colorToName = HashMap<Int, String>()

private val fonts = HashMap<Int, Typeface>()
private val colorsByName = HashMap<String, Int>()
private val colorsById = HashMap<Int, Int>()
private val drawableTints = HashMap<Int, Int>()
private val attrs = HashMap<Int, Int>()
private val raws = HashMap<Int, File>()

object Theme{
    
    fun getNameByColor(color: Int) = colorToName[color]
    fun getColorForName(name: String) = colorsByName[name]
    fun getColorForId(id: Int) = colorsById[id]
    
    @JvmStatic
    fun putColor(name: String, color: Int) {
        val id = Utils.getResId(name, "color")
        if (id != 0) {
            colorsById[id] = color
            colorsByName[name] = color
        } else {
            when (name) {
                "statusbar", "input_background", "active_channel", "blocked_bg" -> colorsByName[name] = color
            }
        }
    }
}