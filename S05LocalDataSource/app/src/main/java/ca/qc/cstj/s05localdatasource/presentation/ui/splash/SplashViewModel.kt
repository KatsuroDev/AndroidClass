package ca.qc.cstj.s05localdatasource.presentation.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s05localdatasource.data.repositories.UserRepository
import ca.qc.cstj.s05localdatasource.domain.models.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val _user = MutableLiveData<User>()
    val user : LiveData<User> get() = _user

    private val userRepository = UserRepository(application)

    init {
        viewModelScope.launch {
            userRepository.user.collect {
                _user.value = it
            }
        }

    }

    fun save(firstName: String, lastName: String, isOnline: Boolean) {
        viewModelScope.launch {
            userRepository.save(firstName, lastName, isOnline)
        }
    }
}