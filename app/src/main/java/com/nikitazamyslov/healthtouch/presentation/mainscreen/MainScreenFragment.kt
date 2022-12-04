package com.nikitazamyslov.healthtouch.presentation.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikitazamyslov.healthtouch.databinding.FragmentMainScreenBinding
import com.nikitazamyslov.healthtouch.presentation.mainscreen.adapter.banner.BannerListAdapter

class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private var adapter = BannerListAdapter()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
