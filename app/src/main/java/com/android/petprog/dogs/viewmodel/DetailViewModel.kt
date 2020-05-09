package com.android.petprog.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.petprog.dogs.model.DogBreed

class DetailViewModel: ViewModel() {

    val dog = MutableLiveData<DogBreed>()
//    val dogName = MutableLiveData<String>()
//    val dogPurpose = MutableLiveData<String>()
//    val dogTemperament = MutableLiveData<String>()
//    val dogLifespan = MutableLiveData<String>()

    fun fetch(position: Int) {
        val dog1 = DogBreed("1", "Corgi", "12 years", "breedGroup1", "bredFor1", "temperament1", "")
        val dog2 = DogBreed("2", "Fui", "7 years", "breedGroup2", "bredFor2", "temperament2", "")
        val dog3 =
            DogBreed("3", "White Fox", "10 years", "breedGroup3", "bredFor3", "temperament3", "")

        val dogList = arrayListOf(dog1, dog2, dog3)
        dog.value = dogList[position]

//        dogName.value = dog.breed
//        dogPurpose.value = dog.bredFor
//        dogTemperament.value = dog.temperament
//        dogLifespan.value = dog.lifeSpan

    }



}