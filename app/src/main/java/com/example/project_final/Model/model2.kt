package com.example.project_final.Model

import android.os.Parcel
import android.os.Parcelable

data class model2 (var hour:String , var audi:String ,var filling:String ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hour)
        parcel.writeString(audi)
        parcel.writeString(filling)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<model2> {
        override fun createFromParcel(parcel: Parcel): model2 {
            return model2(parcel)
        }

        override fun newArray(size: Int): Array<model2?> {
            return arrayOfNulls(size)
        }
    }
}