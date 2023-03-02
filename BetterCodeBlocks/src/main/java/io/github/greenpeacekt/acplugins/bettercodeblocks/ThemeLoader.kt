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

val logger = Logger("べたーこーどぶろっくｗ")

private val colorsByName = HashMap<String, Int>()
private val colorsById = HashMap<Int, Int>()
private val drawableTints = HashMap<Int, Int>()
private val attrs = HashMap<Int, Int>()

object ThemeLoader{
    val themes = ArrayList<String>()
    val ATTR_MAPPINGS = HashMap<String, Array<String>>()
    var overlayAlpha = 0
    
    
    
    
    fun parseColor(json: JSONObject, key: String): Int {
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
    @JvmStatic
    fun loadTheme(theme: Theme): Boolean {
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
            logger.error("ああああえええらー", th)
            return false
        }
        return true
    }
    
    internal fun putAttr(name: String?, color: Int) =
        ATTR_MAPPINGS[name]?.forEach {
            setAttr(it, color)
        }
        
    fun setAttr(attr: String, color: Int) {
        val id = Utils.getResId(attr, "attr")
        if (id == 0) logger.warn("No such attribute: $attr") else attrs[id] = color
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
    
    
   
    @JvmStatic  
    fun clean(){
        colorsByName.clear()
        colorsById.clear()
        drawableTints.clear()
    }
    

}