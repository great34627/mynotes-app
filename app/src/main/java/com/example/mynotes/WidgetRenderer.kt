package com.example.mynotes

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import kotlin.math.min

object WidgetRenderer {
    fun createNoteBitmap(context: Context, width: Int = 900, height: Int = 520): Bitmap {
        val text = NotePrefs.getText(context)
        val fontName = NotePrefs.getFont(context)
        val noteColor = NotePrefs.noteColor(NotePrefs.getNoteColorLabel(context))
        val textColor = NotePrefs.textColor(NotePrefs.getTextColorLabel(context))
        val textSize = NotePrefs.fontSp(NotePrefs.getFontSizeLabel(context)) * context.resources.displayMetrics.scaledDensity

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.TRANSPARENT)

        val shadowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = 0x33000000
            maskFilter = BlurMaskFilter(26f, BlurMaskFilter.Blur.NORMAL)
        }
        val noteRect = RectF(54f, 48f, width - 54f, height - 62f)
        canvas.drawRoundRect(noteRect, 44f, 44f, shadowPaint)

        val notePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = noteColor }
        canvas.drawRoundRect(noteRect, 44f, 44f, notePaint)

        val shinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0x22FFFFFF }
        canvas.drawRoundRect(RectF(80f, 72f, width - 80f, 155f), 34f, 34f, shinePaint)

        val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = textColor
            this.textSize = textSize
            typeface = Typeface.create(fontName, Typeface.BOLD)
        }
        val contentWidth = (noteRect.width() - 96).toInt()
        val staticLayout = StaticLayout.Builder.obtain(text, 0, text.length, paint, contentWidth)
            .setAlignment(Layout.Alignment.ALIGN_CENTER)
            .setLineSpacing(0f, 1.05f)
            .setIncludePad(false)
            .setMaxLines(5)
            .build()
        val textHeight = staticLayout.height
        val top = noteRect.top + (noteRect.height() - textHeight) / 2
        canvas.save()
        canvas.translate(noteRect.left + 48f, min(top, noteRect.top + 105f))
        staticLayout.draw(canvas)
        canvas.restore()
        return bitmap
    }
}
