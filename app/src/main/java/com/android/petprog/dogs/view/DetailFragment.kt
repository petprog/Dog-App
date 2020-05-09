package com.android.petprog.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.android.petprog.dogs.R
import com.android.petprog.dogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by activityViewModels()

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
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch(dogUuid)
        viewModel.dog.observe(viewLifecycleOwner, Observer { dog ->
            dog.let {
                detail_dogName.text = dog.dogBreed
                detail_dogPurpose.text = dog.bredFor
                detail_dogTemperament.text = dog.temperament
                detail_dogLifespan.text = dog.lifeSpan
            }
        })
    }

}
