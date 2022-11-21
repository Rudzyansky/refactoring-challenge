package com.example.refaktoring.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.refaktoring.R
import com.example.refaktoring.databinding.FragmentCoinPriceListBinding
import com.example.refaktoring.presentation.rcview.CoinInfoAdapter
import com.example.refaktoring.presentation.viewmodel.CoinPriceListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinPriceListFragment : Fragment(R.layout.fragment_coin_price_list) {

    private val binding by viewBinding(FragmentCoinPriceListBinding::bind)
    private val viewModel by viewModel<CoinPriceListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CoinInfoAdapter(requireContext())
        adapter.setOnClickListener {
            val direction = CoinPriceListFragmentDirections
                .actionCoinPriceListFragmentToCoinDetailFragment(it.fromSymbol)
            findNavController().navigate(direction)
        }
        binding.rvCoinPriceList.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.priceList.collect { adapter.coinInfoList = it }
        }
    }
}
