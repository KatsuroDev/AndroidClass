package ca.qc.cstj.s01premiereapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast

const val MIN_VALUE = 0
const val MAX_VALUE = 100

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val txvWelcome = findViewById<TextView>(R.id.txvWelcome)
        val nbpNumber = findViewById<NumberPicker>(R.id.nbpNumber)
        val btnTestNumber = findViewById<Button>(R.id.btnTestNumber)

        txvWelcome.text = getString(R.string.deviner_le_nombre, intent.getStringExtra("EXTRA_SECOND_ACTIVITY_NAME"))

        val theNumber = (0..100).random()

        nbpNumber.minValue = MIN_VALUE
        nbpNumber.maxValue = MAX_VALUE

        btnTestNumber.setOnClickListener {
            when {
                nbpNumber.value == theNumber -> {
                    Toast.makeText(this, getString(R.string.bravo), Toast.LENGTH_LONG).show()
                }
                nbpNumber.value > theNumber -> {
                    Toast.makeText(this,getString(R.string.number_lower), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, getString(R.string.number_greater), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Static
    companion object {
        fun newIntent(context: Context, name: String) : Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("EXTRA_SECOND_ACTIVITY_NAME", name)
            return intent

        }
    }
}