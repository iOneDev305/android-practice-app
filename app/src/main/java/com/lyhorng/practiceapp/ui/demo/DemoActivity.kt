package com.lyhorng.practiceapp.ui.demo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.lyhorng.practiceapp.databinding.ActivityDemoBinding
import com.lyhorng.practiceapp.R
import com.lyhorng.practiceapp.ui.home.HomeActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DemoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.btnDialog.setOnClickListener{
            showDialog()
        }
        binding.btnDatePicker.setOnClickListener{ showDatePicker() }
        binding.btnTimePicker.setOnClickListener{ showTimePicker() }
        binding.fab.setOnClickListener{}


    }

    private fun showDialog(){
        val builder = MaterialAlertDialogBuilder(this, R.style.CustomAlertDialog)
        builder.setTitle("Show Custom Dialog")
            .setMessage("This me Lyhorng")
            .setPositiveButton("Ok") {dialog, _ ,->
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") {diallog, _, ->
                diallog.dismiss()
            }
            .show()
    }
    private fun showDatePicker(){
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()

        datePicker.show(supportFragmentManager, "DATE_PICKER")
        datePicker.addOnPositiveButtonClickListener{ selection ->
            val sdf = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
            val date = sdf.format(Date(selection))
            Toast.makeText(this, "Selected Data: $date",Toast.LENGTH_SHORT).show()
        }
    }
    private fun showTimePicker(){
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Sect time")
            .build()

        timePicker.show(supportFragmentManager, "TIME_PICKER")
        timePicker.addOnPositiveButtonClickListener{
            val selectedTime = String.format("%02d:%02d %s",
                if(timePicker.hour > 12) timePicker.hour - 12 else timePicker.hour,
                timePicker.minute,
                if(timePicker.hour >= 12 ) "PM" else "AM"
            )
            Toast.makeText(this, "Selected Time : $selectedTime", Toast.LENGTH_SHORT).show()
        }

    }


}