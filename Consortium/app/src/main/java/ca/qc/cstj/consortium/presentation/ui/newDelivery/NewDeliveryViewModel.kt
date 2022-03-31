package ca.qc.cstj.consortium.presentation.ui.newDelivery

import android.app.Application
import androidx.lifecycle.*
import ca.qc.cstj.consortium.data.AppDatabase
import ca.qc.cstj.consortium.data.repositories.DeliveryRepository
import ca.qc.cstj.consortium.data.repositories.TraderRepository
import ca.qc.cstj.consortium.domain.models.Delivery
import ca.qc.cstj.consortium.domain.models.Trader
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewDeliveryViewModel(application: Application) : AndroidViewModel(application) {
    private val _trader = MutableLiveData<Trader>()
    val trader : LiveData<Trader> = _trader

    private val traderRepository = TraderRepository(application)
    private val deliveryRepository = AppDatabase.getDatabase(application).deliveryRepository()

    init {
        viewModelScope.launch {
            traderRepository.trader.collect {
                _trader.value = it
            }
        }
    }
    fun createDelivery(zuscum: Float,
                       wusnyx: Float,
                       jasmalt: Float,
                       iaspyx: Float,
                       blierium: Float)
    {
        val delivery = Delivery(zuscum, wusnyx, jasmalt, iaspyx, blierium)
        viewModelScope.launch {
            deliveryRepository.create(delivery)
        }
    }
}