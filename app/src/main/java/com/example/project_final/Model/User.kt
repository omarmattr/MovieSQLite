package com.example.project_final.Model

data class User(var id:Int , var name:String , var password:String,var email:String) {

    companion object{
        var COL_user_ID = "id"
        var COL_user_NAME="name"
        var COL_user_PASSWORD ="password"
        var COL_user_EMAIL="email"


        val TABLE_user_NAME = "user"
        val TABLE_user_CREATE = "create table $TABLE_user_NAME ($COL_user_ID integer primary key autoincrement," +
                "$COL_user_NAME text not null, $COL_user_PASSWORD text,$COL_user_EMAIL text)"

    }
}