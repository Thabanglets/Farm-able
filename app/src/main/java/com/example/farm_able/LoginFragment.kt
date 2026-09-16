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
import com.example.farm_able.databinding.FragmentLoginBinding
import com.example.farm_able.databinding.FragmentSignupBinding
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ownerName = arguments?.getString("ownerName") ?: ""
        val farmName = arguments?.getString("farmName") ?: ""
        val selectedLanguage = arguments?.getString("selectedLanguage") ?: ""
        val fullLocation = arguments?.getString("fullLocation") ?: ""

        binding.loginBtn.setOnClickListener {
            val emailAddress = binding.email.text.toString().trim()
            val password = binding.password.text.toString()

            if (emailAddress.isEmpty() || password.isEmpty() ) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }




            lifecycleScope.launch {
                val db = FarmableDb.getDatabase(requireContext())
                val user = db.userDao().getUserByEmailAndPassword(emailAddress, password)

                if (user != null) {
                    Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, HomeFragment())
                        .commit()
                } else {
                    Toast.makeText(requireContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.loginRedirect.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, SignupFragment())
                .commit()
        }
    }


}