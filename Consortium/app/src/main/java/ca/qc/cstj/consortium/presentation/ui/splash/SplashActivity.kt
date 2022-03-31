package ca.qc.cstj.consortium.presentation.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.core.Formatter
import ca.qc.cstj.consortium.core.text
import ca.qc.cstj.consortium.databinding.ActivitySplashBinding
import ca.qc.cstj.consortium.domain.models.Trader
import ca.qc.cstj.consortium.presentation.ui.deliveries.DeliveriesActivity
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.trader.observe(this) {
            println("hihihihi")
            binding.tilTraderName.text = it.name
            binding.txvZuscum.text = Formatter.toDecimalFormat(it.zuscum.toDouble())
            binding.txvWusnyx.text = Formatter.toDecimalFormat(it.wusnyx.toDouble())
            binding.txvJasmalt.text = Formatter.toDecimalFormat(it.jasmalt.toDouble())
            binding.txvIaspyx.text = Formatter.toDecimalFormat(it.iaspyx.toDouble())
            binding.txvBlierium.text = Formatter.toDecimalFormat(it.blierium.toDouble())
        }

        binding.btnOpen.setOnClickListener {
            if(binding.tilTraderName.text.isNotEmpty()){
                viewModel.save(Trader(binding.tilTraderName.text,
                    viewModel.trader.value!!.zuscum,
                    viewModel.trader.value!!.wusnyx,
                    viewModel.trader.value!!.jasmalt,
                    viewModel.trader.value!!.iaspyx,
                    viewModel.trader.value!!.blierium))
                startActivity(DeliveriesActivity.newIntent(this, binding.tilTraderName.text))
            }
            else
                Toast.makeText(this, getString(R.string.emptyTraderNameError), Toast.LENGTH_SHORT).show()
        }

        binding.btnLoad.setOnClickListener {
            viewModel.load()
            viewModel.save(binding.tilTraderName.text,
                viewModel.trader.value!!.zuscum,
                viewModel.trader.value!!.wusnyx,
                viewModel.trader.value!!.jasmalt,
                viewModel.trader.value!!.iaspyx,
                viewModel.trader.value!!.blierium)
        }

        binding.btnUpload.setOnClickListener {
            viewModel.deleteAll()
            Toast.makeText(this, getString(R.string.deliveryUploadedMessage), Toast.LENGTH_SHORT).show()
        }

    }
}