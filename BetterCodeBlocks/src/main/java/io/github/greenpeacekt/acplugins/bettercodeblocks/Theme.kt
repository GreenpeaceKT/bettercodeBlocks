package io.github.greenpeacekt.acplugins.bettercodeblocks

import com.aliucord.Http
import com.aliucord.Utils
import com.aliucord.updater.Updater
import com.discord.stores.StoreStream
import com.discord.utilities.user.UserUtils
import org.json.JSONObject
import java.io.File
import java.io.IOException

class ThemeException(override val message: String) : IOException(message)

class Theme(
    val file: File
) {
    var name: String
    
    init{
        name = "bcb"
    }
}