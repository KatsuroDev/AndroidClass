package ca.qc.cstj.consortium.presentation.ui.deliveries

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.core.notifyAllItemChange
import ca.qc.cstj.consortium.databinding.ActivityDeliveriesBinding
import ca.qc.cstj.consortium.presentation.adapters.DeliveryRecyclerViewAdapter
import ca.qc.cstj.consortium.presentation.ui.newDelivery.NewDeliveryActivity

class DeliveriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeliveriesBinding
    private val viewModel: DeliveriesViewModel by viewModels()

    private lateinit var deliveryRecyclerViewAdapter: DeliveryRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDeliveriesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        deliveryRecyclerViewAdapter = DeliveryRecyclerViewAdapter(listOf())
        binding.rcvDeliveries.layoutManager = LinearLayoutManager(this)
        binding.rcvDeliveries.adapter = deliveryRecyclerViewAdapter

        binding.txvWelcome.text = getString(R.string.welcomeMessage, intent.getStringExtra("EXTRA_TRADER_NAME"))

        viewModel.deliveries.observe(this) {
            deliveryRecyclerViewAdapter.deliveries = it
            deliveryRecyclerViewAdapter.notifyAllItemChange()
        }

        binding.btnAddDelivery.setOnClickListener {
            startActivity(NewDeliveryActivity.newIntent(this))
        }
    }
    companion object {
        fun newIntent(context: Context, traderName: String) : Intent {
            val intent = Intent(context, DeliveriesActivity::class.java)
            intent.putExtra("EXTRA_TRADER_NAME", traderName)
            return intent
        }
    }
}