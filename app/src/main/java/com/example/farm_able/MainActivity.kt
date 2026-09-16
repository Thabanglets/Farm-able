package com.example.farm_able

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.farm_able.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportFragmentManager.registerFragmentLifecycleCallbacks(object : androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentResumed(fm: androidx.fragment.app.FragmentManager, f: Fragment) {
                super.onFragmentResumed(fm, f)
                if (f is OnboardingFragment || f is SignupFragment || f is LoginFragment) {
                    binding.bottomNav.visibility = android.view.View.GONE
                } else {
                    binding.bottomNav.visibility = android.view.View.VISIBLE
                }
            }
        }, false)

        // Set initial fragment
        if (savedInstanceState == null) {
            val sharedPref = getSharedPreferences("FarmablePrefs", android.content.Context.MODE_PRIVATE)
            val isOnboardingFinished = sharedPref.getBoolean("is_onboarding_finished", false)
            
            if (isOnboardingFinished) {
                replaceFragment(LoginFragment())
            } else {
                replaceFragment(OnboardingFragment())
            }
        }

        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.crop -> {
                    replaceFragment(CropFragment())
                    true
                }
                R.id.weather ->{
                    replaceFragment(WeatherFragment())
                    true
                }
                R.id.task ->{
                    replaceFragment(TaskFragment())
                    true
                }
                // Add cases for other menu items when their fragments are implemented
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}
