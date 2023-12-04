package com.example.intersvyaztestapp

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Context.drawableFromId(id: Int) =
    ContextCompat.getDrawable(this, id)

fun Context.toast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()