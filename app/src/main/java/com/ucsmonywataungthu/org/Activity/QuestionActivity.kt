package com.ucsmonywataungthu.org.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Interface.AnswerButtonClick
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.AnswerAdapter
import com.ucsmonywataungthu.org.adapter.QuestionAdapter
import com.ucsmonywataungthu.org.model.Answer
import com.ucsmonywataungthu.org.model.Question
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionActivity : AppCompatActivity() ,AnswerButtonClick{

    lateinit  var alert:AlertDialog
    private val RC_SELECT_IMGAE = 103
    private val RC_PERMISSIONS = 101
    private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    lateinit var photo:ImageView
    lateinit var apiService: APIService
    lateinit var question_adapter:QuestionAdapter
    lateinit var ans_adapter:AnswerAdapter
    override fun onAnswerClickListener(position: Int) {
        Toast.makeText(this@QuestionActivity,position.toString(), Toast.LENGTH_LONG).show()
        showAnswer(position)
    }
    private fun showAnswer(position: Int) {
        /* val view= LayoutInflater.from(this).inflate(R.layout.answer_dialog,null)
         val ansRecyclerView: ListView =view.findViewById(R.id.ans_recycler)
         val display=windowManager.defaultDisplay
         val size:Point= Point()
         display.getSize(size)
         val popupWindow= PopupWindow(view,size.x-50,size.y-500)

         popupWindow.isFocusable=true
         popupWindow.isOutsideTouchable=true
         popupWindow.showAtLocation(view, Gravity.BOTTOM,0,150)
         popupWindow.setBackgroundDrawable(resources.getDrawable(R.drawable.dialog_round))
         val video_list=ArrayList<String>()
         video_list!!.add("hi")
         video_list!!.add("hi")
         video_list!!.add("hi")
         ansRecyclerView.adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,video_list)
         popupWindow.contentView=view
         popupWindow.showAsDropDown(view, 0, 0);*/


        val dialog= AlertDialog.Builder(this,R.style.customizedAlert)
        val view= LayoutInflater.from(this).inflate(R.layout.answer_dialog,null)
        val ansRecyclerView: RecyclerView =view.findViewById(R.id.ans_recycler)
        ansRecyclerView.layoutManager=LinearLayoutManager(this)
        val call=apiService.getAnswer(position+1).enqueue(object :Callback<Answer>{
            override fun onFailure(call: Call<Answer>, t: Throwable) {
                Toast.makeText(this@QuestionActivity,"Fail", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<Answer>, response: Response<Answer>) {
                Toast.makeText(this@QuestionActivity,response.body()!!.answer_get_one_question[0].answer_description, Toast.LENGTH_LONG).show()

                val q=response.body()!!.answer_get_one_question
                ans_adapter=AnswerAdapter(this@QuestionActivity,q)
                ansRecyclerView.adapter=ans_adapter
                ans_adapter.notifyDataSetChanged()

            }
        })

        dialog.setView(view)
        alert=dialog.create()
        alert.window!!.attributes.windowAnimations = R.style.DialogThemeAnim
        alert.show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        var questionRecycler :RecyclerView=findViewById(R.id.questionRecycler)
        questionRecycler.layoutManager= LinearLayoutManager(this)
         apiService= APIInitiate.client.create((APIService::class.java))
        val call=apiService.getQuestion().enqueue(object :Callback<Question>{
            override fun onFailure(call: Call<Question>, t: Throwable) {


            }

            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                val q=response.body()!!.question_get_all
                question_adapter= QuestionAdapter(this@QuestionActivity,q)
                questionRecycler.adapter=question_adapter
                question_adapter.serOnItemClickListener(this@QuestionActivity)
            }
        })


        //photo=findViewById<ImageView>(R.id.photo)
        //photo.setOnClickListener {
          //  askRequiredPermissions()
            //val selectImageIntent= Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            //selectImageIntent.type="image/*"
            //val  mimeTypes = arrayOf("image/jpeg","image/png")
            //selectImageIntent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes)
            //startActivityForResult(selectImageIntent,RC_SELECT_IMGAE)

        //}
      /*  btnUpload.setOnClickListener {

            var t=txt_title.text.toString()
            var d=txt_desc.text.toString()

            photo.isDrawingCacheEnabled = true
            photo.buildDrawingCache()
            val bitmap = (photo.drawable as BitmapDrawable).bitmap

            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val title = RequestBody.create(MediaType.parse("multipart/form-data"), t)
            val desc = RequestBody.create(MediaType.parse("multipart/form-data"), d)

            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), data)
            val body=MultipartBody.Part.createFormData("photo",t+".jpg",requestFile)

            Log.i("Body",body.toString())

            val call=apiService.photoUpload(title,desc,body)
            call.enqueue(object :Callback<SuccessUpload>{
                override fun onFailure(call: Call<SuccessUpload>, t: Throwable) {
                    Toast.makeText(this@QuestionActivity,"Connection lose. !!Try again", Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<SuccessUpload>, response: Response<SuccessUpload>) {
                    val successUpload=response.body()
                    Toast.makeText(this@QuestionActivity,successUpload.toString(), Toast.LENGTH_LONG).show()

                }
            })

        }*/
    }
    private fun askRequiredPermissions() {
        if (!arePermissionGranted()) {

            //ActivityCompat.requestPermissions(this.requireActivity(), PERMISSIONS_REQUIRED, RC_PERMISSIONS)
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        when (requestCode) {
            RC_SELECT_IMGAE -> {

                if (data != null) {

                    val uri = data.data
                    displaySelectedImage(getBitmapFromUri(uri))

                }

            }


            else -> super.onActivityResult(requestCode, resultCode, data)

        }
    }

    private fun displaySelectedImage(bitmapFromUri: Bitmap) {

        photo.setImageBitmap(bitmapFromUri)

    }

    private fun getBitmapFromUri(uri: Uri): Bitmap {

        val parcelFileDescriptor = this!!.contentResolver.openFileDescriptor(uri, "r")

        val fileDescriptor = parcelFileDescriptor?.fileDescriptor

        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)

        parcelFileDescriptor.close()

        return image

    }

}
