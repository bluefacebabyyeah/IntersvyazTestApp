package com.example.intersvyaztestapp.ui.details.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.domain.service.IReminderService
import com.example.intersvyaztestapp.databinding.DialogPickerBinding

class SchedulePickerDialog(
    context: Context,
    private val onResult: (IReminderService.Period) -> Unit,
): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DialogPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCanceledOnTouchOutside(true)
        binding.run {
            bQh.setOnClickListener {
                onResult(IReminderService.Period.QUARTER_HOUR)
                dismiss()
            }
            bH.setOnClickListener {
                onResult(IReminderService.Period.HOUR)
                dismiss()
            }
            bD.setOnClickListener {
                onResult(IReminderService.Period.DAY)
                dismiss()
            }
            bW.setOnClickListener {
                onResult(IReminderService.Period.WEEK)
                dismiss()
            }
            bFs.setOnClickListener {
                onResult(IReminderService.Period.FIVE_SECS)
                dismiss()
            }
            bM.setOnClickListener {
                onResult(IReminderService.Period.MINUTE)
                dismiss()
            }
        }
    }
}