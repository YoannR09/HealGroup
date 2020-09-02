package com.appli.healGroup.controller.level

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.appli.healGroup.R

class LevelActivity : AppCompatActivity(){

    lateinit var buttonNiveau1: Button
    lateinit var buttonNiveau2: Button
    lateinit var buttonNiveau3: Button
    lateinit var buttonNiveau4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        buttonNiveau1 = findViewById(R.id.niveau_1)
        buttonNiveau2 = findViewById(R.id.niveau_2)
        buttonNiveau3 = findViewById(R.id.niveau_3)
        buttonNiveau4 = findViewById(R.id.niveau_4)
        var listButton = listOf(buttonNiveau1, buttonNiveau2, buttonNiveau3, buttonNiveau4)
        for (b in listButton) {
            b.setOnClickListener {
                val intent = Intent(this,FightActivity::class.java)
                when (b.id) {
                    R.id.niveau_1 -> intent.putExtra("LEVEL","1")
                    R.id.niveau_2 -> intent.putExtra("LEVEL","2")
                    R.id.niveau_3 -> intent.putExtra("LEVEL","3")
                    R.id.niveau_4 -> intent.putExtra("LEVEL","4")
                }
                startActivity(intent)
            }
        }
    }
}