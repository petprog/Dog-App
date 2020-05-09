package com.android.petprog.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.petprog.dogs.model.DogBreed

class ListViewModel : ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val dog1 = DogBreed("1", "Corgi", "12 years", "breedGroup1", "bredFor1", "temperament1", "")
        val dog2 = DogBreed("2", "Fui", "7 years", "breedGroup2", "bredFor2", "temperament2", "")
        val dog3 =
            DogBreed("3", "White Fox", "10 years", "breedGroup3", "bredFor3", "temperament3", "")

        val dogList = arrayListOf<DogBreed>(dog1, dog2, dog3)
        dogs.value = dogList
        dogLoadError.value = false
        loading.value = false
    }

}