package io.github.greenpeacekt.acplugins.bettercodeblocks

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.renderscript.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.aliucord.*
import com.aliucord.Utils
import com.aliucord.utils.ReflectUtils
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

val logger = Logger("べたーこーどくろっくｗ")
val ATTR_MAPPINGS = HashMap<String, Array<String>>()
var overlayAlpha = 0

object ThemeLoader{
     val themes = ArrayList<Theme>()

    
    
    
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
    
    fun loadThemes(shouldLoad: Boolean) {
        themes.clear()
        
        val json = JSONObject()
            val colors = JSONObject()
            colors.put("primary_630", -16777216)
            json.put("colors", colors)
        
        try {
            if (shouldLoad ) {
                loadTheme(json)
            }
        } catch (th: Throwable) {
            logger.error("Failed to load theme from JSON", th)
        }

    }
    
    
    private static fun loadTheme(json: JSONObject): Boolean {
        overlayAlpha = 0
        try {
            json.optJSONObject("colors")?.run {
            optString("brand_500")?.let {
                ResourceManager.putDrawableTint(
                    "ic_nitro_rep",
                    parseColor(this, it)
                )
            }
            keys().forEach {
                val v = parseColor(this, it)
                ResourceManager.putColor(it, v)
                ResourceManager.putAttr(it, v)
                }
            }

        } catch (th: Throwable) {
            logger.error("ああああえええらー", th)
            return false
        }
        return true
    }
    
    
    

}