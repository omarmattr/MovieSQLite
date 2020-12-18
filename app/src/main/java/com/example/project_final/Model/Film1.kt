package com.example.project_final.Model

import android.os.Parcel
import android.os.Parcelable

data class Film1(var id:Int, var img:String, var name:String, var time:String, var price:String ,var category:String): Parcelable {



    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!)


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(name)
        parcel.writeString(time)
        parcel.writeString(price)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film1> {
        var COL_ID_film = "id"
        var COL_NAME_film="name"
        var COL_img="img"
        var COL_time="time"
        var COL_price="price"
        var COL_category="category"



        val TABLE_FILM_NAME = "Film"
        val TABLE_FILM_CREATE = "create table $TABLE_FILM_NAME ($COL_ID_film integer primary key autoincrement," +
                "$COL_img text ,$COL_NAME_film text not null, $COL_time text,$COL_price text,$COL_category text);"

        override fun createFromParcel(parcel: Parcel): Film1 {
            return Film1(parcel)
        }

        override fun newArray(size: Int): Array<Film1?> {
            return arrayOfNulls(size)
        }
    }
}