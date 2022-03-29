package ca.qc.cstj.s06remotedatasource.presentation.ui.planet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.data.repositories.FuelPlanetRepository
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import kotlinx.coroutines.launch

class PlanetListViewModel : ViewModel() {

    private val planetRepository = FuelPlanetRepository()

    private val _planets = MutableLiveData<Resource<List<Planet>>>()
    val planets : LiveData<Resource<List<Planet>>> get() = _planets

    init {
        viewModelScope.launch {
            _planets.value = planetRepository.retrieveAll()
        }
    }

}