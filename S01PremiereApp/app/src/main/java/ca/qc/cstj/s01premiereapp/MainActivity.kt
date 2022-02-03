package ca.qc.cstj.s01premiereapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPremier = findViewById<Button>(R.id.btnPremier)
        val btnTelephoner = findViewById<Button>(R.id.btnTelephone)
        val btnSms = findViewById<Button>(R.id.btnSms)
        val btnJeu = findViewById<Button>(R.id.btnJeu)
        val edtName = findViewById<EditText>(R.id.edtName)


        btnPremier.setOnClickListener {
            Toast.makeText(this, "hihihihihihihihhi", Toast.LENGTH_SHORT).show()
        }

        btnTelephoner.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:450-436-1580"))
            startActivity(phoneIntent)
        }

        btnSms.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:450-436-1580"))
            smsIntent.putExtra("sms_body", "Bonjour de ${edtName.text}")
            startActivity(smsIntent)
        }

        btnJeu.setOnClickListener {
            val gameIntent = SecondActivity.newIntent(this, edtName.text.toString())
            startActivity(gameIntent)
        }
    }
}