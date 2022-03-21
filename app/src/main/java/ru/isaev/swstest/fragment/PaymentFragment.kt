package ru.isaev.swstest.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.isaev.swstest.adapters.PaymentListAdapter
import ru.isaev.swstest.databinding.PaymentFragmentBinding
import ru.isaev.swstest.fragment.viewmodel.MenuListViewModel
import ru.isaev.swstest.helper.Item
import ru.isaev.swstest.models.PaymentDataModel

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private lateinit var mBinding: PaymentFragmentBinding
    private val mAdapter = PaymentListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = PaymentFragmentBinding.inflate(layoutInflater)
        val recyclerView: RecyclerView = mBinding.paymentRvComponent
        setupAdapter(recyclerView)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = parseToList(Item.menuPositionCount)
        mAdapter.setData(data)
    }



    private fun setupAdapter(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }
    private fun parseToList(map:MutableMap<String,Int>): List<PaymentDataModel>{
        val mList = mutableListOf<PaymentDataModel>()
        map.forEach{
            val payModel = PaymentDataModel(name= it.key, count = it.value)
            mList.add(payModel)
        }
        return mList.toList()

    }
}