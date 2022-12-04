package com.nikitazamyslov.healthtouch.presentation.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nikitazamyslov.healthtouch.R
import com.nikitazamyslov.healthtouch.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.ivLogo.animate().scaleX(1.2f).scaleY(1.2f).withEndAction {
            binding.ivLogo.animate().scaleX(1.0f).scaleY(1.0f).withEndAction {
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                    binding.tvTitle.isVisible = true
                    binding.tvTitle.animate().translationY(-50.0f)
                    delay(2000)
                    findNavController().navigate(R.id.action_splashScreenFragment_to_mainScreenFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
