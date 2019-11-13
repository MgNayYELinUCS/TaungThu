package com.ucsmonywataungthu.org.Activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.model.SuccessUpload
import kotlinx.android.synthetic.main.activity_ask_question.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class AskQuestion : AppCompatActivity() {
    private val RC_SELECT_IMGAE = 103
    private val RC_PERMISSIONS = 101
    lateinit var apiService: APIService
    var is_pick:Boolean = false
    var user_id:Int = 0
    lateinit var sharedPreferences: SharedPreferences

    private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_question)
        setSupportActionBar(ask_question_toolbar)
        sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val name=sharedPreferences.getString("user_name","")
        user_id=sharedPreferences.getInt("user_id",0)
        apiService= APIInitiate.client.create((APIService::class.java))

        ask_question_toolbar.setNavigationOnClickListener { this.onBackPressed() }

        ask_question_edit.setOnClickListener {
            ask_question_detail.requestFocus()
            val inputMethodManager:InputMethodManager= applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.showSoftInput(ask_question_detail,InputMethodManager.SHOW_IMPLICIT)
        }
        ask_question_pickImage.setOnClickListener {
            askRequiredPermissions()
            if (arePermissionGranted()){
                pickImage()
            }

        }



    }

    fun pickImage() {
        val selectImageIntent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        selectImageIntent.type="image/*"
        val  mimeTypes = arrayOf("image/jpeg","image/png")
        selectImageIntent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes)
        startActivityForResult(selectImageIntent,RC_SELECT_IMGAE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            RC_PERMISSIONS -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED && grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission Denied", Toast.LENGTH_SHORT).show()
                    askRequiredPermissions()
                }
                else{
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
                    pickImage()
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.question_post_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.post_question -> {
                postQuestion()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun postQuestion() {
        var t="hello"
        var d=ask_question_detail.text.toString()
        var body: MultipartBody.Part? =null
        if (is_pick){
            ask_question_image.isDrawingCacheEnabled = true
            ask_question_image.buildDrawingCache()
            val bitmap = (ask_question_image.drawable as BitmapDrawable).bitmap

            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), data)
            body= MultipartBody.Part.createFormData("question_photo",t+".jpg",requestFile)

        }

        val title = RequestBody.create(MediaType.parse("multipart/form-data"), t)
        val desc = RequestBody.create(MediaType.parse("multipart/form-data"), d)

        val userid = RequestBody.create(MediaType.parse("multipart/form-data"),user_id.toString())


        //Log.i("Body",body.toString())

        val call=apiService.insertQuestion(title,desc,userid, body)
        call.enqueue(object : Callback<SuccessUpload> {
            override fun onFailure(call: Call<SuccessUpload>, t: Throwable) {
                Toast.makeText(this@AskQuestion,"Connection lose. !!Try again", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<SuccessUpload>, response: Response<SuccessUpload>) {
                val successUpload=response.body()
                Toast.makeText(this@AskQuestion,successUpload.toString(), Toast.LENGTH_LONG).show()
                finish()

            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        when (requestCode) {
            RC_SELECT_IMGAE -> {

                if (data != null) {

                    is_pick=true
                    val uri = data.data
                    Log.i("Image Uri!!",uri.toString())
                    displaySelectedImage(getBitmapFromUri(uri))

                }
                else{
                    is_pick=false
                }

            }


            else -> super.onActivityResult(requestCode, resultCode, data)

        }
    }
    private fun displaySelectedImage(bitmapFromUri: Bitmap) {
        val display=windowManager.defaultDisplay
        val size= Point()
        display.getSize(size)
        val resize=Bitmap.createScaledBitmap(bitmapFromUri,size.x,550,true)

        ask_question_image.setImageBitmap(resize)

    }

    private fun getBitmapFromUri(uri: Uri): Bitmap {

        val parcelFileDescriptor = this!!.contentResolver.openFileDescriptor(uri, "r")

        val fileDescriptor = parcelFileDescriptor?.fileDescriptor

        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)

        parcelFileDescriptor.close()

        return image

    }

    private fun askRequiredPermissions() {
        if (!arePermissionGranted()) {
            ActivityCompat.requestPermissions(this,PERMISSIONS_REQUIRED,RC_PERMISSIONS)
        }
    }

    private fun arePermissionGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,

                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&

            ContextCompat.checkSelfPermission(
                this,

                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){

            return false

        }

        return true
    }
}
