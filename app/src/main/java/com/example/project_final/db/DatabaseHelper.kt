package com.example.project_final.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity
import com.example.project_final.Model.Film1
import com.example.project_final.Model.User

class DatabaseHelper(activity: FragmentActivity?):SQLiteOpenHelper(activity ,
    DATABASE_NAME, null ,
    DATABASE_VERSION
) {
    var db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
      db?.execSQL(User.TABLE_user_CREATE)
        db?.execSQL(Film1.TABLE_FILM_CREATE)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db!!.execSQL("Drop table if exists ${User.TABLE_user_NAME} ")
        db.execSQL("Drop table if exists ${Film1.TABLE_FILM_CREATE} ")
    }

    fun insertUser(name:String , password:String,email:String):Boolean{
        val cv = ContentValues()
        cv.put(User.COL_user_NAME , name)
        cv.put(User.COL_user_PASSWORD , password)
        cv.put(User.COL_user_EMAIL , email)
        return db.insert(User.TABLE_user_NAME , null , cv)>0
    }
    fun insertFilm(img:String , name:String,time:String,price:String,category:String):Boolean{
        var cv = ContentValues()
        cv.put(Film1.COL_img, img)
        cv.put(Film1.COL_NAME_film , name)
        cv.put(Film1.COL_time , time)
        cv.put(Film1.COL_price , price)
        cv.put(Film1.COL_category , category)
        return db!!.insert(Film1.TABLE_FILM_NAME , null , cv)>0
    }
    fun getActionfilm():ArrayList<Film1>{
        val data = ArrayList<Film1>()
        val c =
            db.rawQuery("select * from ${Film1.TABLE_FILM_NAME} where ${Film1.COL_category}='Action' order by ${Film1.COL_ID_film} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Film1(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getString(5))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data

    }
    fun getromanticfilm():ArrayList<Film1>{
        val data = ArrayList<Film1>()
        val c =
            db.rawQuery("select * from ${Film1.TABLE_FILM_NAME} where ${Film1.COL_category}='romantic' order by ${Film1.COL_ID_film} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Film1(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getString(5))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data

    }
    fun gethorrorfilm():ArrayList<Film1>{
        val data = ArrayList<Film1>()
        val c =
            db.rawQuery("select * from ${Film1.TABLE_FILM_NAME} where ${Film1.COL_category}='horror' order by ${Film1.COL_ID_film} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Film1(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getString(5))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data

    }
    fun getdramafilm():ArrayList<Film1>{
        val data = ArrayList<Film1>()
        val c =
            db.rawQuery("select * from ${Film1.TABLE_FILM_NAME} where ${Film1.COL_category}='drama'order by ${Film1.COL_ID_film} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Film1(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getString(5))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data

    }
    fun getsearchfilm(film:String): ArrayList<Film1> {
        val data = ArrayList<Film1>()
        val c =
            db.rawQuery("select * from ${Film1.TABLE_FILM_NAME} where ${Film1.COL_NAME_film} like'%$film%' order by ${Film1.COL_ID_film} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Film1(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getString(5))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }
    fun getAllFilm(): ArrayList<Film1> {
        val data = ArrayList<Film1>()
        val c =
            db.rawQuery("select * from ${Film1.TABLE_FILM_NAME} order by ${Film1.COL_ID_film} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Film1(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4),c.getString(5))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }
    fun getAllUser(): ArrayList<User> {
        val data = ArrayList<User>()
        val c =
            db.rawQuery("select * from ${User.TABLE_user_NAME} order by ${User.COL_user_ID} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = User(c.getInt(0), c.getString(1), c.getString(2),c.getString(3))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }
    fun deleteFilm(id:Int) : Boolean {
        return db.delete(Film1.TABLE_FILM_NAME, "${Film1.COL_ID_film} = $id", null) > 0
    }
    fun deleteUser(id:Int) : Boolean {
        return db.delete(User.TABLE_user_NAME, "${User.COL_user_ID} = $id", null) > 0
    }
    fun updataUser(id:Int , name:String , password:String,email:String):Boolean{
        var cv = ContentValues()
        cv.put(User.COL_user_NAME , name)
        cv.put(User.COL_user_PASSWORD , password)
        cv.put(User.COL_user_EMAIL , email)
      return  db.update(User.TABLE_user_NAME , cv ,"${User.COL_user_ID} = $id " , null )>0

    }
    fun updataFilm(id:Int ,img:String , name:String,time:String,price:String,category:String):Boolean{
        var cv = ContentValues()
        cv.put(Film1.COL_img, img)
        cv.put(Film1.COL_NAME_film , name)
        cv.put(Film1.COL_time , time)
        cv.put(Film1.COL_price , price)
        cv.put(Film1.COL_category , category)
        return  db.update(Film1.TABLE_FILM_NAME , cv ,"${Film1.COL_ID_film} = $id " , null )>0

    }
    companion object{
        val DATABASE_NAME = "userregester"
        val DATABASE_VERSION = 1
    }
}