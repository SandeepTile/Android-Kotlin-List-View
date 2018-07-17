package com.example.sandy.gallerydemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.indiview.view.*
import java.io.File

class MyAdapter : BaseAdapter{

    var files:Array<File>?=null
    var activity:MainActivity?=null

    constructor(files:Array<File>,activity: MainActivity)

    {

        //Instance Variables
        this.files=files
        this.activity=activity

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //*************Convert xml to view Object*************************
        var inflater=LayoutInflater.from(activity)
        var view=inflater.inflate(R.layout.indiview,null)

        var file=files!![position]

        //view.iv1.setImageURI(Uri.fromFile(file))
        var b=BitmapFactory.decodeFile(file.path)
        var bmp=ThumbnailUtils.extractThumbnail(b,50,50)
        view.iv1.setImageBitmap(bmp)

        view.tv1.text=file.name
        view.tv2.text=file.length().toString()

        view.bt1.setOnClickListener {

            file.delete()
            activity!!.readFiles()

            Toast.makeText(activity,file.name.toString() +" Deleted",Toast.LENGTH_LONG).show()
        }

        return view

    }

    override fun getItem(position: Int): Any {

        return 0
    }

    override fun getItemId(position: Int): Long {

        return 0
    }

    override fun getCount(): Int {

        //return file size
        return files!!.size
    }


}