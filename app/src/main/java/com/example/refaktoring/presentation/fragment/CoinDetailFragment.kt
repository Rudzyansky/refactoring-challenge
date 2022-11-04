package com.example.refaktoring.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.refaktoring.R
import com.example.refaktoring.databinding.FragmentCoinDetailBinding
import com.example.refaktoring.presentation.viewmodel.CoinViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinDetailFragment : Fragment(R.layout.fragment_coin_detail) {

    private val binding by viewBinding(FragmentCoinDetailBinding::bind)
    private val viewModel by viewModel<CoinViewModel>()
    private val args by navArgs<CoinDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.getDetailInfo(args.fromSymbol).observe(viewLifecycleOwner) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowDay
                tvMaxPrice.text = it.highDay
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.getFormattedTime()
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
            }
        }
    }
}
