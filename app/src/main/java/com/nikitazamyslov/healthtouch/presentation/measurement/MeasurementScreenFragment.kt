package com.nikitazamyslov.healthtouch.presentation.measurement

import android.Manifest
import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nikitazamyslov.healthtouch.databinding.FragmentMeasurementScreenBinding
import com.nikitazamyslov.healthtouch.presentation.measurement.model.MeasurementScreenUiModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import net.kibotu.heartrateometer.HeartRateOmeter

@AndroidEntryPoint
class MeasurementScreenFragment : Fragment() {

    private var _binding: FragmentMeasurementScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MeasurementScreenViewModel by viewModels()

    var subscription: CompositeDisposable? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        _binding = FragmentMeasurementScreenBinding.inflate(inflater, container, false)
        setObservers()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dispose()
        subscription = CompositeDisposable()
        checkPermissionAndStartMeasure()
    }

    private fun checkPermissionAndStartMeasure() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) -> {
                startMeasure()
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }

    private fun startMeasure() {
        val bpmUpdates = HeartRateOmeter()
            .withAverageAfterSeconds(WITH_AVERAGE_AFTER_SECONDS)
            .setFingerDetectionListener(::onFingerChange)
            .bpmUpdates(binding.preview)
            .subscribe { bpm ->
                viewModel.updateBPM(bpm.value)
            }
        subscription?.add(bpmUpdates)
    }

    private fun onFingerChange(isPressed: Boolean) {
        if (isPressed) {
            binding.tvMessage.isVisible = false
            if (!viewModel.state.value.isStart)
                viewModel.startMeasure()
        } else {
            binding.tvMessage.isVisible = true
            viewModel.stopMeasure()
        }
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

    private fun updateUi(state: MeasurementScreenUiModel) {
        with(binding) {
            tvBPM.text = state.bpm.toString()
            progressBar.max = state.maxProgress
            progressBar.setProgress(state.progress, true)
        }
    }

    override fun onPause() {
        dispose()
        viewModel.stopMeasure()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dispose() {
        if (subscription?.isDisposed == false)
            subscription?.dispose()
    }

    companion object {
        private const val WITH_AVERAGE_AFTER_SECONDS = 3
    }
}
