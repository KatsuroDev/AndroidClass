package ca.qc.cstj.consortium.presentation.ui.deliveries

import android.app.Application
import androidx.lifecycle.*
import ca.qc.cstj.consortium.data.AppDatabase
import ca.qc.cstj.consortium.data.repositories.DeliveryRepository
import ca.qc.cstj.consortium.domain.models.Delivery
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DeliveriesViewModel(application: Application) : AndroidViewModel(application) {

    private val _deliveries = MutableLiveData<List<Delivery>>()
    val deliveries : LiveData<List<Delivery>> get() = _deliveries

    private val deliveryRepository = AppDatabase.getDatabase(application).deliveryRepository()

    init {
        viewModelScope.launch {
            deliveryRepository.retrieveAll().collect {
                _deliveries.value = it
            }
        }
    }
}