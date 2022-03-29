package ca.qc.cstj.s06remotedatasource.core

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout

fun <T> MutableLiveData<T>.notify() {
    this.value = this.value
}

fun <VH: RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.notifyAllItemChanged() {
    notifyItemRangeChanged(0, itemCount)
}

var TextInputLayout.text
    get() = editText!!.text.toString()
    set(value) = editText!!.setText(value)