package com.hzdevelopers.bestquotesoftheday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hzdevelopers.bestquotesoftheday.databinding.FragmentOneimageviewBinding
import com.hzdevelopers.bestquotesoftheday.models.Love
import com.hzdevelopers.bestquotesoftheday.models.ViewAllCategoriesModel
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class OneImageViewFragment : Fragment() {
    private var binding: FragmentOneimageviewBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentOneimageviewBinding.inflate(LayoutInflater.from(context), container, false)
        val name = arguments?.getString("name")
        val position = arguments?.getInt("position")
        val image = arguments?.getString("image")
        Glide.with(requireContext()).load(image).dontAnimate().into(
            binding!!.oneImageView
        )
        return binding?.root
    }
}