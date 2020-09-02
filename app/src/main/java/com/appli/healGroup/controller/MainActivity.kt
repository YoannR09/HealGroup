package com.appli.healGroup.controller

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.appli.healGroup.R
import com.appli.healGroup.controller.level.LevelActivity


class MainActivity : AppCompatActivity() {

    lateinit var textView: EditText
    lateinit var buttonLancer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.edit_text)
        buttonLancer = findViewById(R.id.button_lancer)
        buttonLancer.isEnabled = false
        textView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                buttonLancer.isEnabled = textView.length() > 0
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        buttonLancer.setOnClickListener {
            val intent = Intent(this, LevelActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "test")
            }
            startActivity(intent)
        }
    }

}