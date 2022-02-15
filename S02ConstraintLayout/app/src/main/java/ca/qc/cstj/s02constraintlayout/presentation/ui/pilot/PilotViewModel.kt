package ca.qc.cstj.s02constraintlayout.presentation.ui.pilot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.qc.cstj.s02constraintlayout.domain.models.Pilot


class PilotViewModel : ViewModel() {
    private val _pilot = MutableLiveData<Pilot>()
    val pilot : LiveData<Pilot> get() = _pilot

    init {
        _pilot.value = Pilot("Bee Zoon", 10)
    }
}