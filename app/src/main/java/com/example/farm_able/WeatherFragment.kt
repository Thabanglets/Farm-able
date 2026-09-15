package com.example.farm_able

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.farm_able.databinding.FragmentWeatherBinding
import com.example.farm_able.weatherModel.Metao
import kotlinx.coroutines.launch

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val apiKey = "3143fcd3ab0fa87ec5910f7a408a9672"
    private val city = "Pretoria"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWeatherData(city, apiKey)
        
        binding.imageView4.setOnClickListener {
            // Depending on how it's added, but popBackStack is a safe bet for navigation
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getWeatherData(town: String, key: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getWeatherForecast(town, key, "metric")
                updateUI(response)
            } catch (e: Exception) {
                Log.e("WeatherFragment", "Error fetching weather data", e)
            }
        }
    }

    private fun updateUI(data: Metao) {
        val currentWeather = data.list.firstOrNull() ?: return
        
        binding.apply {
            temperature.text = "${currentWeather.main.temp.toInt()}°C"
            condition.text = currentWeather.weather.firstOrNull()?.main ?: "Unknown"
            date.text = currentWeather.dt_txt
            windSpeed.text = "${currentWeather.wind.speed} km/h"
        }
    }
}