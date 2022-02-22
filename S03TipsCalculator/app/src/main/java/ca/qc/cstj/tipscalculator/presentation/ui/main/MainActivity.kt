package ca.qc.cstj.tipscalculator.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import ca.qc.cstj.tipscalculator.R
import ca.qc.cstj.tipscalculator.core.Formatter
import ca.qc.cstj.tipscalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            try {
                val subtotal = binding.tilSubtotal.editText!!.text.toString().toDouble()
                val tips = binding.tilTips.editText!!.text.toString().toInt()
                viewModel.calculate(subtotal, tips)
            } catch (ex: NumberFormatException)
            {
                Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show()
            }


        }

        viewModel.tipsCalculation.observe(this) {

            with(binding)
            {
                lblTips.text = Formatter.toMoneyFormat(it.tipsAmount)
                lblTPS.text = Formatter.toMoneyFormat(it.TPS)
                lblTVQ.text = Formatter.toMoneyFormat(it.TVQ)
                lblTotal.text = Formatter.toMoneyFormat(it.total)
            }

        }
    }
}