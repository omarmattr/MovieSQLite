package com.example.project_final.fragment


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.project_final.R
import com.example.project_final.db.DatabaseHelper
import com.vansuita.pickimage.bean.PickResult
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_addfilm.*
import kotlinx.android.synthetic.main.fragment_addfilm.view.*
import com.vansuita.pickimage.bundle.PickSetup

import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.fragment_addfilm.view.btn_add_film



/**
 * A simple [Fragment] subclass.
 */
class addfilm : Fragment(),IPickResult
{
    var imageURI: String = ""
   // var imageURI2:Bitmap? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.fab.visibility = View.GONE

        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_addfilm, container, false)
        val db = DatabaseHelper(activity)

        root.add_img.setOnClickListener {
         PickImageDialog.build(PickSetup()).setOnPickResult {
             onPickResult(it)

         }.setOnPickCancel {
             activity!!.supportFragmentManager.beginTransaction()
                 .replace(R.id.mainContainer2, Home()).commit()
         }.show(activity)


           // val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //startActivityForResult(i, 100)
        }


        activity!!.toolbar.title = "Add Film"


       // var bun=arguments
      //  if(bun!=null){
        //    activity!!.toolbar.title = "Update Film"
        //    var p=bun.getInt("update")


      //  }
        root.btn_add_film.setOnClickListener {
            if (root.add_name.text.isNotEmpty() && root.add_time.text.isNotEmpty() && root.add_price.text.isNotEmpty() ) {

                if (db.insertFilm(
                        imageURI,
                        root.add_name.text.toString(),
                        root.add_time.text.toString(),
                        root.add_price.text.toString(),
                        root.add_category.selectedItem.toString()
                    )
                ) {

                    Toast.makeText(activity, "Added Successfully ^_^ ", Toast.LENGTH_SHORT).show()
                    root.add_name.text.clear()
                    root.add_time.text.clear()
                    root.add_price.text.clear()
                }
                //  activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer2,Home()).commit()
                else {
                    Toast.makeText(activity, "Add Failed", Toast.LENGTH_SHORT).show()

                }

            } else {
                Toast.makeText(activity, "Fill Fields", Toast.LENGTH_SHORT).show()
            }
        }



        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            Log.e("hhhh","hhhf" )
            imageURI = data!!.data.toString()
            Log.e("hzm", imageURI)
            add_img.setImageURI(data!!.data)
        }
    }

    override fun onPickResult(r: PickResult?) {
        Log.e("hhhh","hhhf1" )
        if (r!!.error == null) {
            imageURI=r.uri.toString()
          add_img.setImageURI(r.uri)
           //imageURI2=r.bitmap

        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(activity, r!!.error.message, Toast.LENGTH_LONG).show()
        }

    }


}
