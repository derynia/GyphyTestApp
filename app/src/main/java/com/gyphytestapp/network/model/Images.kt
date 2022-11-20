package com.gyphytestapp.network.model

import android.os.Parcel
import android.os.Parcelable

data class Images(
    val original: Original?,
    val preview_webp: PreviewWebp?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Original::class.java.classLoader),
        parcel.readParcelable(PreviewWebp::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(original, flags)
        parcel.writeParcelable(preview_webp, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Images> {
        override fun createFromParcel(parcel: Parcel): Images {
            return Images(parcel)
        }

        override fun newArray(size: Int): Array<Images?> {
            return arrayOfNulls(size)
        }
    }
}