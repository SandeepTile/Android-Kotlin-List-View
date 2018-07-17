package com.example.sandy.gallerydemo

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check permission for WRITE_EXTERNAL_STORAGE
        var status=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (status==PackageManager.PERMISSION_GRANTED){

            readFiles()
        }else{

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),11)

        }
    }

    //RunTime permission for WRITE_EXTERNAL_STORAGE
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){

            readFiles()
        }else{

            Toast.makeText(this,"You can't continue without permission",Toast.LENGTH_LONG).show()

        }
    }

    fun readFiles()
    {
        var path="storage/sdcard0/WhatsApp/Media/WhatsApp Images/"
        var file= File(path)

        if(!file.exists()){

            path="storage/emulated/0/WhatsApp/Media/WhatsApp Images/"
            file= File(path)

        }

        var files=file.listFiles()

        lv1.adapter=MyAdapter(files,this) //send parameters to constructor

    }


}
