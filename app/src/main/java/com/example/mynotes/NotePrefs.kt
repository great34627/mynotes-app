package com.example.mynotes

import android.content.Context

object NotePrefs {
    private const val PREF = "mynotes_preferences"
    private const val KEY_TEXT = "text"
    private const val KEY_FONT = "font"
    private const val KEY_TEXT_COLOR = "text_color"
    private const val KEY_FONT_SIZE = "font_size"
    private const val KEY_NOTE_COLOR = "note_color"

    val fonts = listOf("Inter", "Plus Jakarta Sans", "Outfit", "Poppins", "Bricolage Grotesque")
    val textColors = listOf("Hitam", "Putih")
    val fontSizes = listOf("Kecil", "Sedang", "Besar")
    val noteColors = listOf("Kuning Terang", "Pink Terang", "Hijau Lime", "Biru Cyan", "Ungu Lavender")

    fun save(context: Context, text: String, font: String, textColor: String, fontSize: String, noteColor: String) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit()
            .putString(KEY_TEXT, text)
            .putString(KEY_FONT, font)
            .putString(KEY_TEXT_COLOR, textColor)
            .putString(KEY_FONT_SIZE, fontSize)
            .putString(KEY_NOTE_COLOR, noteColor)
            .apply()
    }

    fun getText(context: Context) = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        .getString(KEY_TEXT, "Jangan lupa rapat jam 09.00") ?: "Jangan lupa rapat jam 09.00"
    fun getFont(context: Context) = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        .getString(KEY_FONT, fonts[0]) ?: fonts[0]
    fun getTextColorLabel(context: Context) = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        .getString(KEY_TEXT_COLOR, textColors[0]) ?: textColors[0]
    fun getFontSizeLabel(context: Context) = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        .getString(KEY_FONT_SIZE, fontSizes[1]) ?: fontSizes[1]
    fun getNoteColorLabel(context: Context) = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        .getString(KEY_NOTE_COLOR, noteColors[0]) ?: noteColors[0]

    fun textColor(label: String): Int = if (label == "Putih") 0xFFFFFFFF.toInt() else 0xFF111111.toInt()
    fun noteColor(label: String): Int = when (label) {
        "Pink Terang" -> 0xFFFF5DA2.toInt()
        "Hijau Lime" -> 0xFFB6FF3B.toInt()
        "Biru Cyan" -> 0xFF18D5FF.toInt()
        "Ungu Lavender" -> 0xFFC9A7FF.toInt()
        else -> 0xFFFFF44F.toInt()
    }
    fun fontSp(label: String): Float = when (label) {
        "Kecil" -> 20f
        "Besar" -> 34f
        else -> 27f
    }
}
