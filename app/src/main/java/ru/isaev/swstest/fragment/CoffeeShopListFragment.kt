package ru.isaev.swstest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.isaev.swstest.R
import ru.isaev.swstest.adapters.CoffeeShopListAdapter
import ru.isaev.swstest.fragment.viewmodel.CoffeeShopListViewModel
import ru.isaev.swstest.databinding.CoffeeShopListFragmentBinding

@AndroidEntryPoint
class CoffeeShopListFragment : Fragment() {

    private val mAdapter = CoffeeShopListAdapter()
    private val viewModel by viewModels<CoffeeShopListViewModel>()
    private lateinit var mBinding: CoffeeShopListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCoffeeShopListRequest()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = CoffeeShopListFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = mBinding.rvComponent
        setupAdapter(recyclerView)
        viewModel.coffeeShopListItem.value?.let { mAdapter.setData(it) }
    }


    private fun setupAdapter(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }

    fun navigateToMenuList(coffeeShopId: Int){

        val menuFragment = MenuListFragment()
        val bundle = Bundle()
        bundle.putInt("CoffeeShopId", coffeeShopId)
        menuFragment.arguments = bundle
        val fm = fragmentManager
        fm?.beginTransaction()?.replace(R.id.fragment_container, menuFragment)?.commit()

    }

}