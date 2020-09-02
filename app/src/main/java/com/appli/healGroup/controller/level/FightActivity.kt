package com.appli.healGroup.controller.level

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.appli.healGroup.R
import com.appli.healGroup.model.level.GenerateLevel
import com.appli.healGroup.model.heal.GenerateHeal
import kotlinx.android.synthetic.main.activity_fight.view.*


class FightActivity : AppCompatActivity() {

    var bigdmg = 0
    var smalldmg = 0
    var basicdmg = 0
    var select = 1
    var timeToEnd = 100
    // Informations
    lateinit var niveauText: TextView
    lateinit var delaiText: TextView
    // Personnages
    lateinit var caracter1: ImageView
    lateinit var caracter2: ImageView
    lateinit var caracter3: ImageView
    // Barre de santé
    lateinit var life1: ProgressBar
    lateinit var life2: ProgressBar
    lateinit var life3: ProgressBar
    lateinit var lifeText1: TextView
    lateinit var lifeText2: TextView
    lateinit var lifeText3: TextView
    // Barre d'infos
    lateinit var manaBarre: ProgressBar
    lateinit var incantBarre: ProgressBar
    // Barres de dégats enemi
    lateinit var enemiCast1: ProgressBar
    lateinit var enemiCast2: ProgressBar
    // Boutons de heal
    lateinit var heal1: Button
    lateinit var heal2: Button
    lateinit var heal3: Button
    lateinit var heal4: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fight)
        basicdmg = GenerateLevel().getLevel((intent.getStringExtra("LEVEL"))).basicDmg
        bigdmg = GenerateLevel().getLevel((intent.getStringExtra("LEVEL"))).bigDmg
        smalldmg = GenerateLevel().getLevel((intent.getStringExtra("LEVEL"))).smallDmg
        generateView()
        modifView()
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Soignez votre groupe pendant 100 secondes pour obtenir la victoire.")
            .setCancelable(true)
            .setPositiveButton("Commencer", DialogInterface.OnClickListener {
                    dialog, id -> dialogAction(dialog, true)
            })
            .setNegativeButton("Retour", DialogInterface.OnClickListener {
                    dialog, id -> finish()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Commencer le combat")
        // show alert dialog
        alert.show()
    }

    fun dialogAction(dialog: DialogInterface, b: Boolean) {
        if (b) {
            dialog.cancel()
            beginFight()
        }
    }

    /**
     * Relie les objets de la Vue au controller
     */
    fun generateView() {
        lifeText1 = findViewById(R.id.life_text_1)
        lifeText2 = findViewById(R.id.life_text_2)
        lifeText3 = findViewById(R.id.life_text_3)
        enemiCast1 = findViewById(R.id.dmg_barre_1)
        enemiCast2 = findViewById(R.id.dmg_barre_2)
        manaBarre = findViewById(R.id.mana_barre)
        incantBarre = findViewById(R.id.incant_barre)
        delaiText = findViewById(R.id.delai_text)
        niveauText = findViewById(R.id.niveau_text)
        caracter1 = findViewById(R.id.caracter_1)
        caracter2 = findViewById(R.id.caracter_2)
        caracter3 = findViewById(R.id.caracter_3)
        life1 = findViewById(R.id.life_1)
        life2 = findViewById(R.id.life_2)
        life3 = findViewById(R.id.life_3)
        heal1 = findViewById(R.id.heal_1)
        heal2 = findViewById(R.id.heal_2)
        heal3 = findViewById(R.id.heal_3)
        heal4 = findViewById(R.id.heal_4)
    }

    /**
     * Méthode pour modifier l'affichage des éléments de la vue
     */
    fun modifView() {
        enemiCast1.getProgressDrawable().setColorFilter(
            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        enemiCast2.getProgressDrawable().setColorFilter(
            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        manaBarre.getProgressDrawable().setColorFilter(
            Color.CYAN, android.graphics.PorterDuff.Mode.SRC_IN);
        manaBarre.progress = 100
        var listLife = listOf(life1, life2, life3)
        for (l in listLife) {
            l.progress = 100
            l.getProgressDrawable().setColorFilter(
                Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        var listCaracter = listOf(caracter1, caracter2, caracter3)
        niveauText.text = "Niveau : " + intent.getStringExtra("LEVEL")
        var selectId = 0
        for (c in listCaracter) {
            selectId++
            c.setBackgroundColor(-12303292)
            c.setOnClickListener {
                for (all in listCaracter) {
                    all.setBackgroundColor(-12303292)
                }
                c.setBackgroundColor(-3355444)
                when(c.id) {
                    R.id.caracter_1 -> select = 1
                    R.id.caracter_2 -> select = 2
                    R.id.caracter_3 -> select = 3
                }
            }
        }
        var listLifeText = listOf(lifeText1, lifeText2, lifeText3)
        var listHeal = listOf(heal1, heal2, heal3, heal4)
        for (heal in listHeal)
            when(heal.id) {
                R.id.heal_1 -> heal.setOnClickListener {
                    var oldSelect = select
                    disableButton()
                    val smallHeal = Handler(Looper.getMainLooper())
                    var castTime = 0
                    smallHeal.post(object : Runnable {
                        override fun run() {
                           castTime += 20
                            if (castTime < 2000) {
                                incantBarre.progress += 1
                                smallHeal.postDelayed(this, 20)
                            } else if ( castTime == 2000 ) {
                                manaBarre.progress -= GenerateHeal().getHeal(1).cost
                                incantBarre.progress = 0
                                listLife[oldSelect-1].progress += GenerateHeal().getHeal(1).heal
                                listLifeText[oldSelect-1].text = listLife[oldSelect-1].progress.toString() + "/100"
                                enableButton()
                            }
                        }
                    })
                }
                R.id.heal_2 -> heal.setOnClickListener {
                    var oldSelect = select
                    disableButton()
                    val fastHeal = Handler(Looper.getMainLooper())
                    var castTime = 0
                    fastHeal.post(object : Runnable {
                        override fun run() {
                            castTime += 20
                            if (castTime < 1000) {
                                incantBarre.progress += 2
                                fastHeal.postDelayed(this, 20)
                            } else if ( castTime == 1000 ) {
                                manaBarre.progress -= GenerateHeal().getHeal(2).cost
                                incantBarre.progress = 0
                                listLife[oldSelect-1].progress += GenerateHeal().getHeal(2).heal
                                listLifeText[oldSelect-1].text = listLife[oldSelect-1].progress.toString() + "/100"
                                enableButton()
                            }
                        }
                    })
                }
                R.id.heal_3 -> heal.setOnClickListener {
                    var oldSelect = select
                    manaBarre.progress -= GenerateHeal().getHeal(3).cost
                    listLife[oldSelect-1].progress += GenerateHeal().getHeal(3).heal
                    listLifeText[oldSelect-1].text = listLife[oldSelect-1].progress.toString() + "/100"
                    val cdInstaHeal = Handler(Looper.getMainLooper())
                    heal3.isEnabled = false
                    heal3.setBackgroundColor(Color.GRAY)
                    var castTime = 0
                    cdInstaHeal.post(object : Runnable {
                        override fun run() {
                            castTime += 1
                            if (castTime < 7) {
                                heal3.text = (7-castTime).toString()
                                cdInstaHeal.postDelayed(this, 1000)
                            } else if ( castTime == 7 ) {
                                heal3.text = "+30/0sec/-2"
                                if (heal1.isEnabled) {
                                    heal3.isEnabled = true
                                    heal3.setBackgroundColor(Color.WHITE)
                                }
                            }
                        }
                    })
                }
                R.id.heal_4 -> heal.setOnClickListener {

                }
            }
    }

    /**
     * Méthode pour lancer le début du combat
     */
    fun beginFight() {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if (timeToEnd == 0) {
                    System.out.println("Terminé")
                } else {
                    timeToEnd -= 1
                    delaiText.text = "Temps restant : "+ timeToEnd
                    mainHandler.postDelayed(this, 1000)
                }
            }
        })
        val castEnemi = Handler(Looper.getMainLooper())
        var listLife = listOf(life1, life2, life3)
        var listLifeText = listOf(lifeText1, lifeText2, lifeText3)
        val autoDmg = Handler(Looper.getMainLooper())
        autoDmg.post(object : Runnable {
            override fun run() {
                if (timeToEnd == 0) {
                    System.out.println("Terminé")
                } else {
                    val randomInteger = (1..3).shuffled().first()
                    listLife[randomInteger-1].progress -= basicdmg
                    manaBarre.progress += 1
                    listLifeText[randomInteger-1].text = listLife[randomInteger-1].progress.toString() + "/100"
                    autoDmg.postDelayed(this, 3000)
                }
            }
        })
        castEnemi.post(object : Runnable {
            override fun run() {
                if (timeToEnd != 0) {
                    enemiCast1.progress += 4
                    enemiCast2.progress += 1
                    if (enemiCast1.progress >= 100){
                        for (l in listLife) {
                            l.progress -= smalldmg
                        }
                        var posi = 0
                        for (l in listLifeText) {
                            l.text = listLife[posi].progress.toString() + "/100"
                            posi++
                        }
                        enemiCast1.progress = 0
                    }
                    if (enemiCast2.progress >= 100){
                        for (l in listLife) {
                            l.progress -= bigdmg
                        }
                        var posi = 0
                        for (l in listLifeText) {
                            l.text = listLife[posi].progress.toString() + "/100"
                            posi++
                        }
                        enemiCast2.progress = 0
                    }
                    castEnemi.postDelayed(this, 200)
                }
            }
        })
    }

    fun disableButton(){
        var listHeal = listOf(heal1, heal2, heal3, heal4)
        for (b in listHeal) {
            b.isEnabled = false
            b.setBackgroundColor(Color.GRAY)
        }
    }

    fun enableButton(){
        var listHeal = listOf(heal1, heal2, heal3, heal4)
        for (b in listHeal) {
            if (b.id != R.id.heal_3) {
                b.isEnabled = true
                b.setBackgroundColor(Color.WHITE)
            }
            if (heal3.text == "+30/0sec/-2" && b.id == R.id.heal_3) {
                b.isEnabled = true
                b.setBackgroundColor(Color.WHITE)
            }
        }
    }
}