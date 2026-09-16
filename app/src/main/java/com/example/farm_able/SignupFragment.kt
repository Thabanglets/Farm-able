package com.example.farm_able

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.farm_able.data.FarmableDb
import com.example.farm_able.data.User
import com.example.farm_able.databinding.FragmentSignupBinding
import com.example.farm_able.databinding.FragmentWeatherBinding
import kotlinx.coroutines.launch

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? =null
    private val binding get() = _binding!!


    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ownerName = arguments?.getString("ownerName") ?: ""
        val farmName = arguments?.getString("farmName") ?: ""
        val selectedLanguage = arguments?.getString("selectedLanguage") ?: ""
        val fullLocation = arguments?.getString("fullLocation") ?: ""

        binding.signUpBtn.setOnClickListener {
            val emailAddress = binding.email.text.toString().trim()
            val phoneNumber = binding.phoneNum.text.toString().trim()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confPassword.text.toString()

            if (emailAddress.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            if (password != confirmPassword) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val db = FarmableDb.getDatabase(requireContext())
                val newUser = User(
                    FirstName = ownerName,
                    LastName = "",
                    Email = emailAddress,
                    PasswordHash = password,
                    Language = selectedLanguage,
                    Location = fullLocation,
                    FarmName = farmName
                )
                db.userDao().addUser(newUser)

                Toast.makeText(requireContext(), "Profile Saved Successfully!", Toast.LENGTH_SHORT).show()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_container, LoginFragment())
                    .commit()
            }
           
        }

        binding.loginRedirect.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, LoginFragment())
                .commit()
        }
    }





}