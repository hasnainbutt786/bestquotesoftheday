package com.hzdevelopers.bestquotesoftheday.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hzdevelopers.bestquotesoftheday.R
import com.hzdevelopers.bestquotesoftheday.adapters.HomeAdapter
import com.hzdevelopers.bestquotesoftheday.databinding.FragmentHomeBinding
import com.hzdevelopers.bestquotesoftheday.interfaces.HomeInterface
import com.hzdevelopers.bestquotesoftheday.models.QuotesCategory
import com.hzdevelopers.bestquotesoftheday.viewmodel.MyViewModel
import com.hzdevelopers.bestquotesoftheday.viewmodel.Repository
import com.hzdevelopers.bestquotesoftheday.viewmodel.Result
import com.hzdevelopers.bestquotesoftheday.viewmodel.ViewModelFactory

class HomeFragment : Fragment(), HomeInterface {
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context), container, false)

        val repository = Repository(requireContext().applicationContext)
        val viewModelFactory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)

        viewModel.directoryData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    binding?.progressBar?.visibility = View.GONE
                    adapter = HomeAdapter(requireContext(), result.data.quotesCategories,this@HomeFragment)
                    binding?.recyclerViewCategory?.adapter = adapter
                }
                is Result.Error -> {
                    binding?.progressBar?.visibility = View.GONE
                    val cachedResult = viewModel.getDirectoryFromCache()
                    if (cachedResult is Result.Success) {
                        adapter = HomeAdapter(requireContext(), cachedResult.data.quotesCategories,this@HomeFragment)
                        binding?.recyclerViewCategory?.adapter = adapter
                    } else {
                        Toast.makeText(requireContext(), "No cached data available", Toast.LENGTH_SHORT).show()
                    }
                }
                is Result.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })

        viewModel.fetchDirectoryData()
        return binding?.root
    }

    private fun showMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(model: QuotesCategory) {
        val bundle = Bundle()
        bundle.putString("name", model.name)
        findNavController().navigate(R.id.action_homeFragment_to_viewAllFragment, bundle)
    }
}