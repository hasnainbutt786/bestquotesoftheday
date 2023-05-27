package com.hzdevelopers.bestquotesoftheday.ui

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hzdevelopers.bestquotesoftheday.R
import com.hzdevelopers.bestquotesoftheday.adapters.HomeAdapter
import com.hzdevelopers.bestquotesoftheday.adapters.ViewAllAdapter
import com.hzdevelopers.bestquotesoftheday.apiInterfaces.QuotesCategoryApi
import com.hzdevelopers.bestquotesoftheday.databinding.FragmentViewallBinding
import com.hzdevelopers.bestquotesoftheday.interfaces.ViewAllInterface
import com.hzdevelopers.bestquotesoftheday.models.ViewAllCategoriesModel
import com.hzdevelopers.bestquotesoftheday.objects.QuoteCategoriesInstance
import com.hzdevelopers.bestquotesoftheday.viewmodel.MyViewModel
import com.hzdevelopers.bestquotesoftheday.viewmodel.Repository
import com.hzdevelopers.bestquotesoftheday.viewmodel.Result
import com.hzdevelopers.bestquotesoftheday.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewAllFragment : Fragment(), ViewAllInterface {
    private var binding: FragmentViewallBinding? = null
    private var name: String? = null
    private lateinit var adapter : ViewAllAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewallBinding.inflate(LayoutInflater.from(context), container, false)
        name = arguments?.getString("name")
        val repository = Repository(requireContext().applicationContext)
        val viewModelFactory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)

        viewModel.quotesData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    binding?.progressBar2?.visibility = View.GONE
                    adapter = ViewAllAdapter(requireContext(),name?:return@Observer,result.data,this@ViewAllFragment)
                    binding?.viewAllRecyclerView?.adapter = adapter
                }
                is Result.Error -> {
                    binding?.progressBar2?.visibility = View.GONE
                    val cachedResult = viewModel.getQuotesFromCache()
                    if (cachedResult is Result.Success) {
                        adapter = ViewAllAdapter(requireContext(),name?:return@Observer, cachedResult.data,this@ViewAllFragment)
                        binding?.viewAllRecyclerView?.adapter = adapter
                    } else {
                        Toast.makeText(requireContext(), "No cached data available", Toast.LENGTH_SHORT).show()
                    }
                }
                is Result.Loading -> {
                    binding?.progressBar2?.visibility = View.VISIBLE
                }
            }
        })

        viewModel.fetchQuotesData()
        return binding?.root
    }

    override fun onClick(model: ViewAllCategoriesModel, name: String, position: Int) {
        //Toast.makeText(requireContext(), "${name.toString()} ${position.toString()}",Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putInt("position", position)

        when(name){
            "Love" ->{
                bundle.putString("image",model.Love[position].imageUrl)
                bundle.putSerializable("model",model.Love.toString())
                findNavController().navigate(R.id.oneImageViewFragment, bundle)
            }
        }
    }
}
