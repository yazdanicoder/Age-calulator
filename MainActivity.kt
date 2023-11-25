package com.example.zodicsignapp

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    private lateinit var Year: DatePicker
    private lateinit var Age: TextView
    private lateinit var ZodiacSign: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       Year = findViewById(R.id.Year)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        Age = findViewById(R.id.Age)
        ZodiacSign = findViewById(R.id.ZodiacSign)

        btnCalculate.setOnClickListener {
            calculateAgeAndZodiacSign()
        }
    }

    private fun calculateAgeAndZodiacSign() {
        val birthYear = Year.year
        val birthMonth = Year.month + 1 // Month is zero-based
        val birthDay = Year.dayOfMonth

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        var years = currentYear - birthYear
        var months = currentMonth - birthMonth
        var days = currentDay - birthDay

        if (days < 0) {
            months--
            days += daysInMonth(birthMonth, birthYear)
        }
        if (months < 0) {
            years--
            months += 12
        }

        val ageString = "Your age is: $years years, $months months, and $days days"
        Age.text = ageString

        val zodiacSign = calculateZodiacSign(birthMonth, birthDay)
        ZodiacSign.text = "Your Zodiac Sign is: $zodiacSign"
    }

    private fun daysInMonth(month: Int, year: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, 1)
        calendar.add(Calendar.MONTH, 1)
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun calculateZodiacSign(month: Int, day: Int): String {
        return when {
            (month == 3 && day >= 21) || (month == 4 && day <= 19) -> "Aries"
            (month == 4 && day >= 20) || (month == 5 && day <= 20) -> "Taurus"
            (month == 5 && day >= 21) || (month == 6 && day <= 20) -> "Gemini"
            (month == 6 && day >= 21) || (month == 7 && day <= 22) -> "Cancer"
            (month == 7 && day >= 23) || (month == 8 && day <= 22) -> "Leo"
            (month == 8 && day >= 23) || (month == 9 && day <= 22) -> "Virgo"
            (month == 9 && day >= 23) || (month == 10 && day <= 22) -> "Libra"
            (month == 10 && day >= 23) || (month == 11 && day <= 21) -> "Scorpio"
            (month == 11 && day >= 22) || (month == 12 && day <= 21) -> "Sagittarius"
            (month == 12 && day >= 22) || (month == 1 && day <= 19) -> "Capricorn"
            (month == 1 && day >= 20) || (month == 2 && day <= 18) -> "Aquarius"
            (month == 2 && day >= 19) || (month == 3 && day <= 20) -> "Pisces"
            else -> "Zodiac sign not found"
        }
    }
}
