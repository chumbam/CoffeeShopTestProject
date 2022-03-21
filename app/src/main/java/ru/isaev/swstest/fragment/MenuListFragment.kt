package ru.isaev.swstest.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.isaev.swstest.adapters.CoffeeShopMenuListAdapter
import ru.isaev.swstest.adapters.QuantityCountDelegate
import ru.isaev.swstest.databinding.MenuListFragmentBinding
import ru.isaev.swstest.fragment.viewmodel.MenuListViewModel
import ru.isaev.swstest.helper.Item
import ru.isaev.swstest.helper.getStringArg
import kotlin.properties.Delegates

@AndroidEntryPoint
class MenuListFragment : Fragment() {

    private val mAdapter = CoffeeShopMenuListAdapter()
    private val viewModel by viewModels<MenuListViewModel>()
    private lateinit var mBinding: MenuListFragmentBinding
    var shopMenuId by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shopMenuId = getStringArg("ID")
        viewModel.fetchMenuListRequest(shopMenuId)

        mAdapter.attachDelegate(object : QuantityCountDelegate {
            override fun quantityPlus(name: String, count: Int) {
                Item.menuPositionCount[name] = count

            }

            override fun quantityMinus(name: String, count: Int) {
                Item.menuPositionCount[name] = count

            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = MenuListFragmentBinding.inflate(layoutInflater)
        val recyclerView: RecyclerView = mBinding.rvComponentMenu
        setupAdapter(recyclerView)
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        viewModel.menuListItem.observe(this, { data ->
            mAdapter.setData(data)
        })
        mBinding.payBtn.setOnClickListener {
            viewModel.navigateToPaymentFragment()
        }
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        val layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }

}
