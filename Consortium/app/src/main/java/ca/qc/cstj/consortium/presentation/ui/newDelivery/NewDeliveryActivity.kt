package ca.qc.cstj.consortium.presentation.ui.newDelivery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.core.Formatter
import ca.qc.cstj.consortium.databinding.ActivityNewDeliveryBinding
import ca.qc.cstj.consortium.presentation.ui.deliveries.DeliveriesActivity
import com.google.android.material.snackbar.Snackbar

class NewDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewDeliveryBinding

    private val viewModel: NewDeliveryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.trader.observe(this) {
            with(binding) {
                sldElementZ.isEnabled = (it.zuscum > 0)
                sldElementWu.isEnabled = (it.wusnyx > 0)
                sldElementJa.isEnabled = (it.jasmalt > 0)
                sldElementI.isEnabled = (it.iaspyx > 0)
                sldElementB.isEnabled = (it.blierium > 0)

                sldElementZ.valueTo = if(it.zuscum > 0) it.zuscum else 1F
                sldElementWu.valueTo = if(it.wusnyx > 0) it.wusnyx else 1F
                sldElementJa.valueTo = if(it.jasmalt > 0) it.jasmalt else 1F
                sldElementI.valueTo = if(it.iaspyx > 0) it.iaspyx else 1F
                sldElementB.valueTo = if(it.blierium > 0) it.blierium else 1F

                sldElementZ.value = it.zuscum
                sldElementWu.value = it.wusnyx
                sldElementJa.value = it.jasmalt
                sldElementI.value = it.iaspyx
                sldElementB.value = it.blierium

                txvAmountElementZ.text = Formatter.toDecimalFormat(it.zuscum.toDouble())
                txvAmountElementWu.text = Formatter.toDecimalFormat(it.wusnyx.toDouble())
                txvAmountElementJa.text = Formatter.toDecimalFormat(it.jasmalt.toDouble())
                txvAmountElementI.text = Formatter.toDecimalFormat(it.iaspyx.toDouble())
                txvAmountElementB.text = Formatter.toDecimalFormat(it.blierium.toDouble())
                }
            }

        with(binding) {
            sldElementZ.addOnChangeListener { _, value, _ -> txvAmountElementZ.text = Formatter.toDecimalFormat(value.toDouble())}
            sldElementWu.addOnChangeListener { _, value, _ -> txvAmountElementWu.text = Formatter.toDecimalFormat(value.toDouble())}
            sldElementJa.addOnChangeListener { _, value, _ -> txvAmountElementJa.text = Formatter.toDecimalFormat(value.toDouble())}
            sldElementI.addOnChangeListener { _, value, _ -> txvAmountElementI.text = Formatter.toDecimalFormat(value.toDouble())}
            sldElementB.addOnChangeListener { _, value, _ -> txvAmountElementB.text = Formatter.toDecimalFormat(value.toDouble())}

            btnSaveDelivery.setOnClickListener {
                with(binding){
                    val zuscum = sldElementZ.value
                    val wusnyx = sldElementWu.value
                    val jasmalt = sldElementJa.value
                    val iaspyx = sldElementI.value
                    val blierium = sldElementB.value
                    if(zuscum == 0F &&
                        wusnyx == 0F &&
                        jasmalt == 0F &&
                        iaspyx == 0F &&
                        blierium == 0F)
                            Toast.makeText(this@NewDeliveryActivity, getString(R.string.emptyDeliveryError), Toast.LENGTH_SHORT).show()
                    else{
                        viewModel.createDelivery(zuscum, wusnyx, jasmalt, iaspyx, blierium)
                        Toast.makeText(this@NewDeliveryActivity, getString(R.string.deliveryCreation), Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }

        }
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, NewDeliveryActivity::class.java)
        }
    }
}