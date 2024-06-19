package com.modul.buahhati.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.di.Injection
import com.modul.buahhati.view.article.DetailArticleViewModel
import com.modul.buahhati.view.detail_history.DetailHistoryViewModel
import com.modul.buahhati.view.fragment.fragment_home.HomeViewModel
import com.modul.buahhati.view.fragment.fragment_home.InputViewModel
import com.modul.buahhati.view.fragment.fragment_home.ProfileViewModel
import com.modul.buahhati.view.history.HistoryViewModel
import com.modul.buahhati.view.input_pertumbuhan.InputPertumbuhanViewModel
import com.modul.buahhati.view.login.LoginViewModel
import com.modul.buahhati.view.regis_anak.RegisAnakViewModel
import com.modul.buahhati.view.regis_anak.SharedViewModel
import com.modul.buahhati.view.sign_up.SignUpViewModel
import com.modul.buahhati.view.view_result.ResultViewModel

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val prereference : LoginPreference,
):ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECK_CAST")
    override fun <T:ViewModel>create(modelClass:Class<T>):T{
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(userRepository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(userRepository,prereference) as T
        }
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(prereference,userRepository) as T
        }
        if (modelClass.isAssignableFrom(RegisAnakViewModel::class.java)){
            return RegisAnakViewModel(userRepository, prereference) as T
        }
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel() as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(userRepository) as T
        }
        if (modelClass.isAssignableFrom(DetailArticleViewModel::class.java)){
            return DetailArticleViewModel(userRepository) as T
        }
        if (modelClass.isAssignableFrom(InputPertumbuhanViewModel::class.java)){
            return InputPertumbuhanViewModel(userRepository, prereference) as T
        }
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)){
            return ResultViewModel(userRepository, prereference) as T
        }
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)){
            return HistoryViewModel(userRepository) as T
        }
        if (modelClass.isAssignableFrom(DetailHistoryViewModel::class.java)) {
            return DetailHistoryViewModel(userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class" + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context, preferences: LoginPreference): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context), preferences)
            }.also { INSTANCE = it }
    }
}
