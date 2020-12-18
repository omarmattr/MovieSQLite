package com.example.project_final.Abapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.project_final.Main2Activity
import com.example.project_final.Model.Film1
import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import kotlinx.android.synthetic.main.recycle2.view.*
import kotlinx.android.synthetic.main.recyclehome.view.*
import kotlinx.android.synthetic.main.recyclesearch.view.*

class Adapter (var activity: Activity, var data: ArrayList<Film1>, var layout:Int, val click: onClick): RecyclerView.Adapter<Adapter.myviewHolder>() {

    class myviewHolder(iteamView : View): RecyclerView.ViewHolder(iteamView)
    {
        var filmImage=iteamView.imageView2
        val filmName=iteamView.textView10
        val filmTime=itemView.filmTime
        val filmprince=iteamView.filmprice
        val like=iteamView.LikeFilm
        val more=iteamView.btn_more

        val filmImage2=iteamView.imgview
        val filmName2=iteamView.textName
        val filmprince2=iteamView.price2
        val card=iteamView
        val more2=iteamView.btn_more2

        val filmImage3=iteamView.img_se
        val filmName3=iteamView.name_se
        val filmTime3=iteamView.time_se



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {

        var v = LayoutInflater.from(activity).inflate(layout , parent , false)

        return myviewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {

        val fllist = data[position]
        if(layout==R.layout.recyclehome){
       holder.itemView.apply {
           imageView2 .setImageURI(Uri.parse(fllist.img))
           textView10.text = fllist.name
           holder.filmTime.text = "#"+fllist.time+"min"
           holder.filmprince.text ="$"+ fllist.price

           holder.like.setOnClickListener {
               holder.like.setImageResource(R.drawable.ic_favorite)
               holder.like.setOnClickListener {
                   holder.like.setImageResource(R.drawable.ic_favorite_border_black_24dp)
               }
           }

           holder.card.setOnClickListener {

               click.onClickItem(fllist.id)



           }
           holder.more.setOnClickListener {
               click.onLongClickItem(holder.more,fllist.id)
           }
       }


            if (layout==R.layout.recycle2){

                holder.filmImage2.setImageURI(Uri.parse(data[position].img))
                holder.filmName2.text = fllist.name
                holder.filmprince2.text = "$"+fllist.price

                holder.card.setOnClickListener {
                    click.onClickItem(fllist.id)
                }
                holder.more2.setOnClickListener {
                    click.onLongClickItem(holder.more2,fllist.id)
                }
            }
            if (layout==R.layout.recyclesearch){

                holder.filmImage3.setImageURI(Uri.parse(data[position].img))
                holder.filmName3.text = fllist.name
                holder.filmTime3.text = "#"+fllist.time+"min"
                holder.card.setOnClickListener {
                    click.onClickItem(fllist.id)
                }
                holder.card.setOnLongClickListener {
                    click.onLongClickItem(holder.card,fllist.id)
                    true
                }

            }
       }

}
    interface onClick {
        fun onClickItem(position: Int)

        fun onLongClickItem(view: View,position: Int)
    }


}


