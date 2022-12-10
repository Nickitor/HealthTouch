package com.nikitazamyslov.healthtouch.presentation.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nikitazamyslov.healthtouch.R
import com.nikitazamyslov.healthtouch.databinding.FragmentSplashScreenBinding
import com.nikitazamyslov.healthtouch.presentation.util.getTimer
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.Duration.Companion.seconds


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var heartAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        heartAnimation = AnimationUtils.loadAnimation(context, R.anim.heart_pulse_animation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        startAnimation()
    }

    private fun startAnimation() {
        binding.ivLogo.startAnimation(heartAnimation)
        getTimer(
            duration = DURATION_SPLASH_SCREEN,
            completeAction = ::onCompleteAction,
            scope = lifecycleScope
        )
    }

    private fun stopAnimation() {
        binding.ivLogo.clearAnimation()
        heartAnimation.cancel()
        heartAnimation.reset()
    }

    private fun onCompleteAction() {
        stopAnimation()
        findNavController().navigate(R.id.action_splashScreenFragment_to_mainScreenFragment)
    }

    override fun onPause() {
        stopAnimation()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val DURATION_SPLASH_SCREEN = 3.seconds
    }
}
