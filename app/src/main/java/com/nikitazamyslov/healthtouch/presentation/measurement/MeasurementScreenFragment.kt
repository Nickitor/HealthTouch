package com.nikitazamyslov.healthtouch.presentation.measurement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nikitazamyslov.healthtouch.databinding.FragmentMeasurementScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MeasurementScreenFragment : Fragment() {

    private var _binding: FragmentMeasurementScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MeasurementScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeasurementScreenBinding.inflate(inflater, container, false)
        setObservers()
        return binding.root
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

    private fun updateUi(state: Unit) {
        //TODO
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
