package ca.qc.cstj.tipscalculator.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.qc.cstj.tipscalculator.core.Constants
import ca.qc.cstj.tipscalculator.domain.models.TipsCalculation

class MainViewModel : ViewModel() {

    private val _tipsCalculation = MutableLiveData<TipsCalculation>()
    val tipsCalculation : LiveData<TipsCalculation> get() = _tipsCalculation

    fun calculate(subtotal:Double, tips: Int)
    {
        _tipsCalculation.value = TipsCalculation(subtotal, tips / Constants.PERCENT as Float)
    }

}