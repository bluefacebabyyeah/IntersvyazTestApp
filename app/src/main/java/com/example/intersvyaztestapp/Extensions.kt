package com.example.intersvyaztestapp

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.drawableFromId(id: Int) =
    ContextCompat.getDrawable(this, id)