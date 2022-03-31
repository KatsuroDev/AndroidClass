package ca.qc.cstj.s06remotedatasource.presentation.ui.planet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s06remotedatasource.core.LoadingResource
import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.data.repositories.FuelPlanetRepository
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlanetListViewModel : ViewModel() {

    private val planetRepository = FuelPlanetRepository()

    private val _planets = MutableLiveData<Resource<List<Planet>>>()
    val planets : LiveData<Resource<List<Planet>>> get() = _planets

    private val _planetsLoading = MutableLiveData<LoadingResource<List<Planet>>>()
    val planetsLoading : LiveData<LoadingResource<List<Planet>>> get() = _planetsLoading

    init {
        viewModelScope.launch {
            //_planets.value = planetRepository.retrieveAll()
//            planetRepository.retrieveAllWithUpdate().collect {
//                _planets.value = it
//            }
            planetRepository.retrieveAllWithLoading().collect {
                _planetsLoading.value = it
            }
        }
    }

}