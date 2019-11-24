package com.ucsmonywataungthu.org.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucsmonywataungthu.org.Interface.AnswerButtonClick
import com.ucsmonywataungthu.org.Interface.ImageClick
import com.ucsmonywataungthu.org.Network.APIInitiate
import com.ucsmonywataungthu.org.Network.APIService
import com.ucsmonywataungthu.org.R
import com.ucsmonywataungthu.org.adapter.AnswerAdapter
import com.ucsmonywataungthu.org.adapter.QuestionAdapter
import com.ucsmonywataungthu.org.model.Answer
import com.ucsmonywataungthu.org.model.Question
import com.ucsmonywataungthu.org.model.SuccessUpload
import kotlinx.android.synthetic.main.activity_question.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionActivity : AppCompatActivity() ,AnswerButtonClick,ImageClick{


    override fun onImageClickListener(img_url: String) {
        /*Glide.with(this)
            .load(APIInitiate.PIC_URL+img_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(full_image)*/
    }


    override fun onResume() {
        super.onResume()
        readQuestion()
    }

    lateinit var alert:AlertDialog
    lateinit var apiService: APIService
    lateinit var question_adapter:QuestionAdapter
    lateinit var ans_adapter:AnswerAdapter
    lateinit var questionRecycler :RecyclerView
    lateinit var sharedPreferences: SharedPreferences
    var user_id:Int = 0

    lateinit var ansRecyclerView: RecyclerView
    override fun onAnswerClickListener(position: Int) {
        showAnswer(position)
    }
    private fun showAnswer(position: Int) {

        val dialog= AlertDialog.Builder(this,R.style.customizedAlert)
        val view= LayoutInflater.from(this).inflate(R.layout.answer_dialog,null)
        ansRecyclerView=view.findViewById(R.id.ans_recycler)
        val commentEdit:EditText=view.findViewById(R.id.ans_comment_EditText)
        val ansSend:ImageButton=view.findViewById(R.id.ans_send)


        ansSend.setOnClickListener {
            commentEdit.onEditorAction(EditorInfo.IME_ACTION_DONE)
            if (commentEdit.text.isEmpty()){
                Toast.makeText(this@QuestionActivity,"Enter Comment ...", Toast.LENGTH_LONG).show()
            }
            else{
                 val comment:String=commentEdit.text.toString()
                apiService.setAnswer(comment,user_id,position).enqueue(object :Callback<SuccessUpload>{

                    override fun onFailure(call: Call<SuccessUpload>, t: Throwable) {
                        Toast.makeText(this@QuestionActivity,"Connection Fail,Try again", Toast.LENGTH_LONG).show()

                    }

                    override fun onResponse(call: Call<SuccessUpload>, response: Response<SuccessUpload>) {
                        getAnswer(position)
                        commentEdit.setText("")
                        Toast.makeText(this@QuestionActivity,"Added Comment", Toast.LENGTH_LONG).show()

                    }

                })

            }

        }
        ansRecyclerView.layoutManager=LinearLayoutManager(this)
        getAnswer(position)


        dialog.setView(view)
        alert=dialog.create()
        alert.window!!.attributes.windowAnimations = R.style.DialogThemeAnim
        alert.show()

    }

    private fun getAnswer(position: Int) {
        val call=apiService.getAnswer(position).enqueue(object :Callback<Answer>{
            override fun onFailure(call: Call<Answer>, t: Throwable) {
                Toast.makeText(this@QuestionActivity,"Connection Fail,", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<Answer>, response: Response<Answer>) {
                val q=response.body()!!.answer_get_one_question
                if (q.isEmpty()){
                    Toast.makeText(this@QuestionActivity,"No Answer"
                        , Toast.LENGTH_LONG).show()
                }else{
                    ans_adapter=AnswerAdapter(this@QuestionActivity,q)
                    ansRecyclerView.adapter=ans_adapter
                    ans_adapter.notifyDataSetChanged()

                }



            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setSupportActionBar(q_toolbar)
        q_toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }


        sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        user_id=sharedPreferences.getInt("user_id",0)
        apiService= APIInitiate.client.create((APIService::class.java))

        ask_Edit.setOnClickListener {

            startActivity(Intent(this,AskQuestion::class.java))
        }
        questionRecycler =findViewById(R.id.questionRecycler)
        questionRecycler.layoutManager= LinearLayoutManager(this)



        readQuestion()


    }

    private fun readQuestion() {
        val call=apiService.getQuestion().enqueue(object :Callback<Question>{
            override fun onFailure(call: Call<Question>, t: Throwable) {


            }

            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                val q=response.body()!!.question_get_all
                question_adapter= QuestionAdapter(this@QuestionActivity,q)
                questionRecycler.adapter=question_adapter
                question_adapter.setOnItemClickListener(this@QuestionActivity)
                question_adapter.setOnImageClickListener(this@QuestionActivity)

            }
        })
    }

}
