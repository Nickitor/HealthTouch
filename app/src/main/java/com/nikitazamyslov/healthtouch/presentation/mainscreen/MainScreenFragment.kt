package com.nikitazamyslov.healthtouch.presentation.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikitazamyslov.healthtouch.databinding.FragmentMainScreenBinding
import com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.banner.BannerListAdapter
import com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.measurement.MeasurementListAdapter
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.BannerUiModel
import com.nikitazamyslov.healthtouch.presentation.mainscreen.model.MeasurementUiModel
import com.nikitazamyslov.healthtouch.presentation.util.MeasurementStatus

class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private var adapter = BannerListAdapter()

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
        return binding.root
    }

    private fun setRecyclerView() {
        binding.rvBanners.adapter = adapter
        adapter.onClickListener = {}
        adapter.submitList((1..1).map {
            BannerUiModel(
                it.toString(), "Title", "Subtitle",
                "https://content.everydayresources.com/wp-content/uploads/2021/05/Best-Fitness-Apps-500x375.jpg"
            )
        })

        binding.rvMeasurement.adapter = adapterMeasurement
        adapter.onClickListener = {}
        adapterMeasurement.submitList((1..1).map {
            MeasurementUiModel(it, "Today, at 6:55 a.m.", MeasurementStatus.Bad, 100, 100)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
