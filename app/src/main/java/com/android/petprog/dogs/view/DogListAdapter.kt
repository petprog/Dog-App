package com.android.petprog.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.petprog.dogs.R
import com.android.petprog.dogs.model.DogBreed
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_dog.view.*

class DogListAdapter(val dogList: ArrayList<DogBreed>): RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {

    class DogViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

    }

    fun updateDogList(newDogList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.containerView.item_dog_name.text = dogList[position].dogBreed
        holder.containerView.item_dog_lifeSpan.text = dogList[position].lifeSpan
    }


}