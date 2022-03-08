package ca.qc.cstj.s05localdatasource.core

import androidx.recyclerview.widget.RecyclerView

fun <VH: RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.notifyAllItemChange() {
    notifyItemRangeChanged(0, itemCount)
}