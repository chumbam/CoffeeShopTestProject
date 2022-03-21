package ru.isaev.swstest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.isaev.swstest.R
import ru.isaev.swstest.adapters.NavigateToMenuDelegate
import ru.isaev.swstest.adapters.CoffeeShopListAdapter
import ru.isaev.swstest.databinding.CoffeeShopListFragmentBinding
import ru.isaev.swstest.fragment.viewmodel.CoffeeShopListViewModel
import ru.isaev.swstest.helper.getStringArg


@AndroidEntryPoint
class CoffeeShopListFragment : Fragment() {

    private val mAdapter = CoffeeShopListAdapter()
    private val viewModel by viewModels<CoffeeShopListViewModel>()
    lateinit var button: Button
    private lateinit var mBinding: CoffeeShopListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val token = getStringArg("Token")
        this.activity?.getSharedPreferences(getString(R.string.app_name), 0)?.edit()
            ?.putString("Token", token)?.apply()
        viewModel.fetchCoffeeShopListRequest()

        mAdapter.attachDelegate(object : NavigateToMenuDelegate {
            override fun navigateToMenuFragment(id: String) {
                viewModel.navigateToMenuFragment(id)
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = CoffeeShopListFragmentBinding.inflate(layoutInflater)
        setupAdapter(mBinding.rvComponent)
        return mBinding.root

    }

    override fun onStart() {
        super.onStart()
        viewModel.coffeeShopListItem.observe(this, { data ->
            mAdapter.setData(data)
        })
        mBinding.onMapsBtn.setOnClickListener {
            viewModel.navigateToMapsFragment()
        }

    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }


}