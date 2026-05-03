package com.example.mynotes

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : Activity() {
    private lateinit var preview: TextView
    private lateinit var input: EditText
    private lateinit var fontSpinner: Spinner
    private lateinit var textColorSpinner: Spinner
    private lateinit var fontSizeSpinner: Spinner
    private lateinit var noteColorSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preview = findViewById(R.id.previewNote)
        input = findViewById(R.id.inputReminder)
        fontSpinner = findViewById(R.id.spinnerFont)
        textColorSpinner = findViewById(R.id.spinnerTextColor)
        fontSizeSpinner = findViewById(R.id.spinnerFontSize)
        noteColorSpinner = findViewById(R.id.spinnerNoteColor)

        setupSpinner(fontSpinner, NotePrefs.fonts)
        setupSpinner(textColorSpinner, NotePrefs.textColors)
        setupSpinner(fontSizeSpinner, NotePrefs.fontSizes)
        setupSpinner(noteColorSpinner, NotePrefs.noteColors)

        loadSavedValues()
        bindPreviewUpdates()

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val text = input.text.toString().trim().ifEmpty { "Tulis reminder Anda di sini" }
            NotePrefs.save(
                this,
                text,
                selected(fontSpinner),
                selected(textColorSpinner),
                selected(fontSizeSpinner),
                selected(noteColorSpinner)
            )
            updatePreview()
            MyNotesWidgetProvider.updateAll(this)
            Toast.makeText(this, "MyNotes berhasil disimpan dan widget diperbarui", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupSpinner(spinner: Spinner, values: List<String>) {
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
    }

    private fun loadSavedValues() {
        input.setText(NotePrefs.getText(this))
        fontSpinner.setSelection(NotePrefs.fonts.indexOf(NotePrefs.getFont(this)).coerceAtLeast(0))
        textColorSpinner.setSelection(NotePrefs.textColors.indexOf(NotePrefs.getTextColorLabel(this)).coerceAtLeast(0))
        fontSizeSpinner.setSelection(NotePrefs.fontSizes.indexOf(NotePrefs.getFontSizeLabel(this)).coerceAtLeast(0))
        noteColorSpinner.setSelection(NotePrefs.noteColors.indexOf(NotePrefs.getNoteColorLabel(this)).coerceAtLeast(0))
        updatePreview()
    }

    private fun bindPreviewUpdates() {
        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = updatePreview()
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
        fontSpinner.onItemSelectedListener = listener
        textColorSpinner.onItemSelectedListener = listener
        fontSizeSpinner.onItemSelectedListener = listener
        noteColorSpinner.onItemSelectedListener = listener
        input.setOnFocusChangeListener { _, _ -> updatePreview() }
    }

    private fun updatePreview() {
        val text = input.text.toString().trim().ifEmpty { "Jangan lupa rapat jam 09.00" }
        preview.text = text
        preview.setTextColor(NotePrefs.textColor(selected(textColorSpinner)))
        preview.textSize = when (selected(fontSizeSpinner)) {
            "Kecil" -> 18f
            "Besar" -> 28f
            else -> 22f
        }
        preview.typeface = Typeface.create(selected(fontSpinner), Typeface.BOLD)
        val bg = android.graphics.drawable.GradientDrawable().apply {
            setColor(NotePrefs.noteColor(selected(noteColorSpinner)))
            cornerRadius = 28f * resources.displayMetrics.density
        }
        preview.background = bg
    }

    private fun selected(spinner: Spinner): String = spinner.selectedItem?.toString() ?: ""
}
