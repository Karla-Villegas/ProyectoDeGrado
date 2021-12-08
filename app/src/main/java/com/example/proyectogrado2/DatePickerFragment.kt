package com.example.proyectogrado2

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener: (year:Int, month:Int, day:Int)->Unit): DialogFragment(), DatePickerDialog.OnDateSetListener{
    lateinit var c: Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dialogDate = DatePickerDialog(activity as Context, this, year,month,day)
        return dialogDate
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        listener(year, month+1, day)
    }

}