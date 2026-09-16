package com.example.farm_able

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.farm_able.data.FarmableDb
import com.example.farm_able.data.User
import kotlinx.coroutines.launch
import java.util.Calendar

class OnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        // Editable Input Fields
        val etOwnerName = view.findViewById<EditText>(R.id.tv_owner_name)
        val etFarmName = view.findViewById<EditText>(R.id.tv_farm_name)
        val etLocationCity = view.findViewById<EditText>(R.id.tv_location_city)
        val etLocationSize = view.findViewById<EditText>(R.id.tv_location_size)

        // Language Buttons
        val btnLangEn = view.findViewById<LinearLayout>(R.id.btn_lang_en)
        val btnLangZu = view.findViewById<LinearLayout>(R.id.btn_lang_zu)
        val btnLangSo = view.findViewById<LinearLayout>(R.id.btn_lang_so)
        val btnLangTs = view.findViewById<LinearLayout>(R.id.btn_lang_ts)

        val langButtons = listOf(btnLangEn, btnLangZu, btnLangSo, btnLangTs)
        var selectedLanguage = "English"

        fun selectLanguage(selectedButton: LinearLayout, langName: String) {
            selectedLanguage = langName
            langButtons.forEach { button ->
                if (button == selectedButton) {
                    button.setBackgroundResource(R.drawable.bg_lang_selected)
                } else {
                    button.setBackgroundResource(R.drawable.bg_lang_unselected)
                }
            }
        }

        btnLangEn.setOnClickListener { selectLanguage(btnLangEn, "English") }
        btnLangZu.setOnClickListener { selectLanguage(btnLangZu, "Zulu") }
        btnLangSo.setOnClickListener { selectLanguage(btnLangSo, "Sotho") }
        btnLangTs.setOnClickListener { selectLanguage(btnLangTs, "Chitonga") }

        // Crop Buttons
        val btnCropTomato = view.findViewById<LinearLayout>(R.id.btn_crop_tomato)
        val btnCropSpinach = view.findViewById<LinearLayout>(R.id.btn_crop_spinach)
        val btnCropPepper = view.findViewById<LinearLayout>(R.id.btn_crop_pepper)
        val btnCropCabbage = view.findViewById<LinearLayout>(R.id.btn_crop_cabbage)

        var isTomatoSelected = false
        var isSpinachSelected = true // spinach is filled green by default in the image
        var isPepperSelected = false
        var isCabbageSelected = false

        btnCropTomato.setOnClickListener {
            isTomatoSelected = !isTomatoSelected
            btnCropTomato.setBackgroundResource(
                if (isTomatoSelected) R.drawable.bg_crop_spinach else R.drawable.bg_crop_tomato
            )
        }

        btnCropSpinach.setOnClickListener {
            isSpinachSelected = !isSpinachSelected
            btnCropSpinach.setBackgroundResource(
                if (isSpinachSelected) R.drawable.bg_crop_spinach else R.drawable.bg_lang_unselected
            )
        }

        btnCropPepper.setOnClickListener {
            isPepperSelected = !isPepperSelected
            btnCropPepper.setBackgroundResource(
                if (isPepperSelected) R.drawable.bg_crop_spinach else R.drawable.bg_crop_pepper
            )
        }

        btnCropCabbage.setOnClickListener {
            isCabbageSelected = !isCabbageSelected
            btnCropCabbage.setBackgroundResource(
                if (isCabbageSelected) R.drawable.bg_crop_spinach else R.drawable.bg_crop_cabbage
            )
        }

        // Date Picker
        val tvDatePicker = view.findViewById<TextView>(R.id.tv_date_picker)
        tvDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = String.format("%04d / %02d / %02d", selectedYear, selectedMonth + 1, selectedDay)
                    tvDatePicker.text = formattedDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        // Continue Button
        val btnContinue = view.findViewById<Button>(R.id.btn_continue)
        btnContinue.setOnClickListener {
            val ownerName = etOwnerName.text.toString().trim()
            val farmName = etFarmName.text.toString().trim()
            val city = etLocationCity.text.toString().trim()
            val size = etLocationSize.text.toString().trim()

            if (ownerName.isEmpty() || farmName.isEmpty() || city.isEmpty() || size.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all profile fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val fullLocation = "$city, $size"

            // Save onboarding status
            val sharedPref = requireContext().getSharedPreferences("FarmablePrefs", android.content.Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("is_onboarding_finished", true)
                apply()
            }

            val signupFragment = SignupFragment().apply {
                arguments = Bundle().apply {
                    putString("ownerName", ownerName)
                    putString("farmName", farmName)
                    putString("selectedLanguage", selectedLanguage)
                    putString("fullLocation", fullLocation)
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, signupFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
