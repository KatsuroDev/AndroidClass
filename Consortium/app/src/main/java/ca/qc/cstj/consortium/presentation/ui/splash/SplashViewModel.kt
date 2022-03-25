package ca.qc.cstj.consortium.presentation.ui.splash

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import ca.qc.cstj.consortium.data.AppDatabase
import ca.qc.cstj.consortium.data.repositories.TraderRepository
import ca.qc.cstj.consortium.domain.models.Trader
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val _trader = MutableLiveData<Trader>()
    val trader : LiveData<Trader> get() = _trader

    private val traderRepository = TraderRepository(application)
    private val deliveryRepository = AppDatabase.getDatabase(application).deliveryRepository()

    init {
        viewModelScope.launch {
            traderRepository.trader.collect {
                _trader.value = it
            }
        }
    }

    fun load()
    {
        _trader.value!!.load()
    }

    fun save(name:String,
             zuscum:Float,
             wusnyx:Float,
             jasmalt:Float,
             iaspyx:Float,
             blierium:Float) {
        viewModelScope.launch {
            traderRepository.save(name, zuscum, wusnyx, jasmalt, iaspyx, blierium)
        }
    }

    fun save(trader: Trader)
    {
        viewModelScope.launch {
            traderRepository.save(trader.name, trader.zuscum, trader.wusnyx, trader.jasmalt, trader.iaspyx, trader.blierium)
        }
    }

    fun deleteAll()
    {
        viewModelScope.launch {
            deliveryRepository.deleteAll()
        }
    }
}