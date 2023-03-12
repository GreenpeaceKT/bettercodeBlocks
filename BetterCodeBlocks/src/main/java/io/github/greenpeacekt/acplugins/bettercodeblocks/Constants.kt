package io.github.greenpeacekt.acplugins.bettercodeblocks

import com.aliucord.Constants
import java.io.File
import java.util.regex.Pattern

fun initAttrMappings() {
    ATTR_MAPPINGS.clear()
    val map = mapOf(
        pairOf("brand_new") to arrayOf("color_brand"),
        pairOf("brand_new_360", "brand_new_500") to arrayOf("colorControlBrandForeground"),
        pairOf("brand_new_260", "brand_new_530") to arrayOf("theme_chat_mention_foreground"),
        pairOf("brand_new_500") to arrayOf("color_brand_500"),
        pairOf("brand_360", "brand_500") to arrayOf("colorControlActivated"),
        pairOf("brand_500_alpha_20", "brand_new_160") to arrayOf("theme_chat_mention_background"),
        pairOf("link", "link_light") to arrayOf("colorTextLink"),
        pairOf("mention_highlight") to arrayOf("theme_chat_mentioned_me"),

        pairOf("primary_dark_600", "white") to arrayOf("colorBackgroundPrimary"),
        pairOf("primary_dark_800", "white") to arrayOf(
            "colorSurface",
            "colorBackgroundFloating",
            "colorTabsBackground"
        ),
        pairOf("primary_600", "primary_300") to arrayOf("primary_600", "theme_chat_spoiler_inapp_bg"),
        pairOf("primary_630", "white_1") to arrayOf("theme_chat_code"),
        pairOf("primary_660", "white_2") to arrayOf("primary_660", "theme_chat_codeblock_border"),
        pairOf("primary_700", "primary_light_200") to arrayOf(
            "primary_700",
            "colorBackgroundTertiary",
            "colorBackgroundSecondary",
        ),
        pairOf("primary_800", "primary_200") to arrayOf("primary_800"),
        pairOf("primary_900", "primary_100") to arrayOf("primary_900"),
        pairOf("primary_300", "primary_700") to arrayOf("theme_chat_spoiler_bg"),
        pairOf("black_alpha_10", "white_alpha_10") to arrayOf("theme_chat_spoiler_bg_visible")
    )

    with(ATTR_MAPPINGS) {
        val isLightMode = currentTheme == "light"
        map.forEach { (k, v) ->
            val key = if (isLightMode) k.second else k.first
            if (containsKey(key)) {
                put(key, arrayOf(*get(key)!!, *v))
            } else put(key, v)
        }
    }
}
