package io.github.greenpeacekt.acplugins.bettercodeblocks

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.renderscript.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.aliucord.*
import com.aliucord.utils.ReflectUtils
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

import android.content.Context
import android.graphics.Typeface
import com.aliucord.Utils
import com.lytefast.flexinput.R

private val colorsByName = HashMap<String, Int>()
private val colorsById = HashMap<Int, Int>()
private val drawableTints = HashMap<Int, Int>()

object Theme{
    val themes = ArrayList<Theme>()
    
    var overlayAlpha = 0
    
    
    private fun parseColor(json: JSONObject, key: String): Int {
        val v = json.getString(key)
        return if (v.startsWith("system_")) {
            if (Build.VERSION.SDK_INT < 31)
                throw UnsupportedOperationException("system_ colours are only supported on Android 12.")

            try {
                ContextCompat.getColor(
                    Utils.appContext,
                    ReflectUtils.getField(android.R.color::class.java, null, v) as Int
                )
            } catch (th: Throwable) {
                throw IllegalArgumentException("No such color: $v")
            }
        } else v.toInt()
    }
    
    private fun loadTheme(theme: Theme): Boolean {
        overlayAlpha = 0
        try {

            val json = JSONObject()
            json.put("colors",json.put("primary_630", -16777216))

            json.optJSONObject("colors")?.run {
                if (has("brand_500"))
                    putDrawableTint(
                        "ic_nitro_rep",
                        parseColor(this, "brand_500")
                    )
                keys().forEach {
                    val v = parseColor(this, it)
                    putColor(it, v)
                    putAttr(it, v)
                }
            }

        } catch (th: Throwable) {
            logger.error("Failed to load theme ${theme.name}", th)
            return false
        }
        return true
    }
    
    
    internal fun putDrawableTint(name: String, color: Int) {
        val id = Utils.getResId(name, "drawable")
        if (id != 0)
            drawableTints[id] = color
        else
            logger.warn("Unrecognised drawable $name")
    }
    
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
    
    internal fun putAttr(name: String?, color: Int) =
        ATTR_MAPPINGS[name]?.forEach {
            setAttr(it, color)
        }
        
    fun clean(){
        colorsByName.clear()
        colorsById.clear()
        drawableTints.clear()
    }
    

}