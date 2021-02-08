package com.maxwell.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var tvSelectedDate: TextView? = null
    var tvAgeInMinutes: TextView? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btSelectDate = findViewById<Button>(R.id.btSelectDate)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btSelectDate.setOnClickListener { view ->
            showDatePicker(view)
        }
    }

    private fun showDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { view, year, month, dayOfMonth ->
            getSelectedDate(year, month, dayOfMonth)
        }, year, month, dayOfMonth).show()
    }

    private fun getSelectedDate(year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$dayOfMonth/$month/$year"
        tvSelectedDate?.text = selectedDate
        val sdf  = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val date = sdf.parse(selectedDate)
        calculateAgeInMinutes(date)
    }

    private fun calculateAgeInMinutes(dateSelected: Date?) {
        val currentDate = Date()
        val diffDates: Long = currentDate.time - dateSelected!!.time
        val seconds = diffDates / 1000
        val minutes = seconds / 60

        tvAgeInMinutes?.text = minutes.toString()
    }
}