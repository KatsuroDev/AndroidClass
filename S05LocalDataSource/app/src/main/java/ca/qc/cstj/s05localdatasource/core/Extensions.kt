package ca.qc.cstj.s05localdatasource.core

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout

fun <VH: RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.notifyAllItemChange() {
    notifyItemRangeChanged(0, itemCount)
}

var TextInputLayout.text
    get() = editText!!.text.toString()
    set(value) = editText!!.setText(value)