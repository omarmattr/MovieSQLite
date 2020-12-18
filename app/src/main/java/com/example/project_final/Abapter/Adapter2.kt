package com.example.project_final.Abapter
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_final.Model.model2
import com.example.project_final.R
import kotlinx.android.synthetic.main.recycletime.view.*

class Adapter2 (var activity: Activity, var data: MutableList<model2>) : RecyclerView.Adapter<Adapter2.ViewHoldr>() {

    class ViewHoldr(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        val name = ItemView.txthour
        val audi = ItemView.txtaudi
        val filling = ItemView.txtfilling
        val card=ItemView.cardid
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoldr {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.recycletime, parent, false)
        return ViewHoldr(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHoldr, position: Int) {
        holder.name.text = data[position].hour
        holder.audi.text = data[position].audi
        holder.filling.text = data[position].filling
        holder.card.setOnClickListener {

            holder.card.setBackgroundResource(R.color.bk)}







    }
}



