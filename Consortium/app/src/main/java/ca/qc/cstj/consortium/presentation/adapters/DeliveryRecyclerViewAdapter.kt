package ca.qc.cstj.consortium.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.core.Formatter
import ca.qc.cstj.consortium.databinding.ItemDeliveryBinding
import ca.qc.cstj.consortium.domain.models.Delivery

class DeliveryRecyclerViewAdapter(var deliveries : List<Delivery>)
    : RecyclerView.Adapter<DeliveryRecyclerViewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_delivery, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val delivery = deliveries[position]
        holder.bind(delivery)
    }

    override fun getItemCount(): Int = deliveries.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemDeliveryBinding.bind(view)

        fun bind(delivery: Delivery)
        {
            with(binding)
            {
                txvZuscum.text = Formatter.toDecimalFormat(delivery.zuscum.toDouble())
                txvWusnyx.text = Formatter.toDecimalFormat(delivery.wusnyx.toDouble())
                txvJasmalt.text = Formatter.toDecimalFormat(delivery.jasmalt.toDouble())
                txvIaspyx.text = Formatter.toDecimalFormat(delivery.iaspyx.toDouble())
                txvBlierium.text = Formatter.toDecimalFormat(delivery.blierium.toDouble())
            }
        }
    }
}