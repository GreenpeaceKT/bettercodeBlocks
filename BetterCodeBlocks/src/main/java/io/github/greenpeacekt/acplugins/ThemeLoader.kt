package io.github.greenpeacekt.acplugins

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

object ThemeLoader {
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
    
    private fun loadTheme(theme: Theme): Boolean {
        ResourceManager.overlayAlpha = 0
        try {
            if (!theme.convertIfLegacy())
                theme.update()

            val json = theme.json()
            
            json.optJSONObject("colors")?.run {
                if (has("brand_500"))
                    ResourceManager.putDrawableTint(
                        "ic_nitro_rep",
                        parseColor(this, "brand_500")
                    )
                keys().forEach {
                    val v = parseColor(this, it)
                    ResourceManager.putColor(it, v)
                    ResourceManager.putAttr(it, v)
                }
            }
        }
    }
}
