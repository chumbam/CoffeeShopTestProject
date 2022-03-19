package ru.isaev.swstest.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.isaev.swstest.R
import ru.isaev.swstest.adapters.CoffeeShopMenuListAdapter
import ru.isaev.swstest.databinding.MenuListFragmentBinding
import ru.isaev.swstest.fragment.viewmodel.MenuListViewModel
import ru.isaev.swstest.helper.showToast
import kotlin.properties.Delegates

@AndroidEntryPoint
class MenuListFragment : Fragment() {

    private val mAdapter = CoffeeShopMenuListAdapter()
    private val viewModel by viewModels<MenuListViewModel>()
    private lateinit var mBinding: MenuListFragmentBinding
    var shopMenuId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        shopMenuId = bundle?.getInt("CoffeeShopId") ?: 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = MenuListFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = mBinding.rvComponentMenu
        setupAdapter(recyclerView)
        viewModel.fetchMenuListRequest(shopMenuId)
        if(viewModel.menuListItem.value?.isNotEmpty() == true){
            mAdapter.setData(viewModel.menuListItem.value!!)
        } else{
            showToast("Данные грузяться")
        }
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        val layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }

}