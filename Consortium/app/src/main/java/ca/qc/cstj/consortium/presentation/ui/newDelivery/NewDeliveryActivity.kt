package ca.qc.cstj.consortium.presentation.ui.newDelivery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.core.Formatter
import ca.qc.cstj.consortium.databinding.ActivityNewDeliveryBinding
import ca.qc.cstj.consortium.presentation.ui.deliveries.DeliveriesActivity

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

                sldElementZ.valueTo = it.zuscum
                sldElementWu.valueTo = it.wusnyx
                sldElementJa.valueTo = it.jasmalt
                sldElementI.valueTo = it.iaspyx
                sldElementB.valueTo = it.blierium

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
                    val wusnyx = sldElementZ.value
                    val jasmalt = sldElementJa.value
                    val iaspyx = sldElementI.value
                    val blierium = sldElementB.value
                    viewModel.createDelivery(zuscum, wusnyx, jasmalt, iaspyx, blierium)
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