package com.android.petprog.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.android.petprog.dogs.R
import com.android.petprog.dogs.databinding.FragmentDetailBinding
import com.android.petprog.dogs.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by activityViewModels()

    private lateinit var dataBinding: FragmentDetailBinding

    private var dogUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch(dogUuid)
        viewModel.dog.observe(viewLifecycleOwner, Observer { dog ->
            dog.let {
                if(dog != null) {
                    dataBinding.dog = dog
                }

            }
        })
    }

}
