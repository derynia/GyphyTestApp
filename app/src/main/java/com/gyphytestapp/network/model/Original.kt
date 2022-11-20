package com.gyphytestapp.network.model

import android.os.Parcel
import android.os.Parcelable

data class Original(
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

    companion object CREATOR : Parcelable.Creator<Original> {
        override fun createFromParcel(parcel: Parcel): Original {
            return Original(parcel)
        }

        override fun newArray(size: Int): Array<Original?> {
            return arrayOfNulls(size)
        }
    }
}