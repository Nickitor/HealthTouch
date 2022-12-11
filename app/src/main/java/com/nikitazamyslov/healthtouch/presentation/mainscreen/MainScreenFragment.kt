package com.nikitazamyslov.healthtouch.presentation.mainscreen

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nikitazamyslov.healthtouch.R
import com.nikitazamyslov.healthtouch.databinding.FragmentMainScreenBinding
import com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.banner.BannerListAdapter
import com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.measurement.MeasurementListAdapter
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MainScreenUiModel
import com.nikitazamyslov.healthtouch.presentation.util.DeleteItemTouchCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainScreenViewModel by viewModels()

    private var adapterBanner = BannerListAdapter()

    private var adapterMeasurement = MeasurementListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        setRecyclerView()
        setObservers()
        setListeners()
        return binding.root
    }

    private fun setRecyclerView() {
        binding.rvBanners.adapter = adapterBanner
        adapterBanner.onClickListener = {}

        binding.rvMeasurement.adapter = adapterMeasurement
        adapterBanner.onClickListener = {}
        setDeleteMeasureTouchHelper()
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    updateUi(state)
                }
            }
        }
    }

    private fun setListeners() {
        binding.ivScanButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_measurementScreenFragment)
        }
    }

    private fun updateUi(state: MainScreenUiModel) {
        adapterBanner.submitList(state.bannerUiModel)
        adapterMeasurement.submitList(state.measurementUiModel)
    }

    private fun setDeleteMeasureTouchHelper() {
        val itemTouchHelper = ItemTouchHelper(DeleteItemTouchCallback(::deleteMeasureCallback))
        itemTouchHelper.attachToRecyclerView(binding.rvMeasurement)
    }

    private fun deleteMeasureCallback(position: Int) {
        viewModel.removeItem(adapterMeasurement.currentList[position].id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
