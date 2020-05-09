package com.android.petprog.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation

import com.android.petprog.dogs.R
import com.android.petprog.dogs.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private val viewModel: ListViewModel by activityViewModels()
    private val dogListAdapter = DogListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
    }
}
