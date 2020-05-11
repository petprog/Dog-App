package com.android.petprog.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.petprog.dogs.R
import com.android.petprog.dogs.databinding.ItemDogBinding
import com.android.petprog.dogs.model.DogBreed
import kotlinx.android.synthetic.main.item_dog.view.*

class DogListAdapter(private val dogList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogListAdapter.DogViewHolder>(), DogClickListener {

    class DogViewHolder(var containerView: ItemDogBinding) :
        RecyclerView.ViewHolder(containerView.root)

    fun updateDogList(newDogList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        val view =
            DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {

        holder.containerView.dog = dogList[position]
        holder.containerView.listener = this
    }

    override fun onDogClicked(v: View) {
        val uuid = v.item_dog_id.text.toString().toInt()
        val action = ListFragmentDirections.actionListFragmentToDetailFragment()
        action.dogUuid = uuid
        Navigation.findNavController(v).navigate(action)
    }


}