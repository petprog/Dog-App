package com.android.petprog.dogs.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.palette.graphics.Palette
import com.android.petprog.dogs.R
import com.android.petprog.dogs.databinding.FragmentDetailBinding
import com.android.petprog.dogs.model.DogPalette
import com.android.petprog.dogs.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

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
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetch(dogUuid)

        viewModel.dog.observe(viewLifecycleOwner, Observer { dog ->
            dog.let {
                if (dog != null) {
                    dataBinding.dog = dog

                    it.imageUrl?.let { image ->
                        setBackgroundColor(image)
                    }
                }
            }
        })

    }

    private fun setBackgroundColor(url: String) {

        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate { palette ->
                            val intBackgroundColor = palette?.lightMutedSwatch?.rgb ?: 0
                            val intTextColor = palette?.darkMutedSwatch?.rgb ?: 1
                            val intOtherTextColor = palette?.darkMutedSwatch?.rgb ?: 1
                            val myPalette =
                                DogPalette(intBackgroundColor, intTextColor, intOtherTextColor)
                            dataBinding.palette = myPalette
                        }
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_send_sms -> {

            }
            R.id.action_share -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


}
