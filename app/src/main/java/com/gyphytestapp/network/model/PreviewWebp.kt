package com.gyphytestapp.network.model

import android.os.Parcel
import android.os.Parcelable

data class PreviewWebp(
    val url: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString() ?: "") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PreviewWebp> {
        override fun createFromParcel(parcel: Parcel): PreviewWebp {
            return PreviewWebp(parcel)
        }

        override fun newArray(size: Int): Array<PreviewWebp?> {
            return arrayOfNulls(size)
        }
    }
}