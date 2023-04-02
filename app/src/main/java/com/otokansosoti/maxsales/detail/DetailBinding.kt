package com.otokansosoti.maxsales.detail
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailBinding(
    val title: String,
    val image: String,
    val text: String,
    val whatsappLabel: String,
    val whastappContent: String,
    val phoneLabel: String,
    val phoneContent: String
): Parcelable