package com.android.petprog.dogs.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.petprog.dogs.model.DogBreed
import com.android.petprog.dogs.model.DogDatabase
import kotlinx.coroutines.launch

private const val TAG = "DetailViewModel"
class DetailViewModel(application: Application) : BaseViewModel(application) {

    val dog = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int) {
        launch {
            val dogData = DogDatabase(getApplication()).dogDao().getDog(uuid)
            Log.d(TAG, dogData.toString())
            dog.value = dogData
        }


    }


}