package com.example.sunlight

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class SecondActivity : AppCompatActivity() {

    // declaring a null variable for VideoView
    private var simpleVideoView: VideoView? = null
    // declaring a null variable for MediaController
    private var mediaControls: MediaController? = null
    private lateinit var textT : EditText
    private lateinit var recyclerView: RecyclerView
    private val movieList = ArrayList<MovieModel>()
    private lateinit var moviesAdapter: MoviesAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondactivity)
        title = "SunLight"
        val send: Button = findViewById(R.id.button3)
        textT = findViewById(R.id.editText)
        recyclerView = findViewById(R.id.recyclerView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        moviesAdapter = MoviesAdapter(movieList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = moviesAdapter
        val temp = intent.getStringExtra("userName")
        send.setOnClickListener {
            val textEntered = textT.text.toString()
            val text1 = "$$>$temp|> $textEntered"
            prepareMovieData(textEntered)
            giveResponse(textEntered)
        }
        showVideo()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun prepareMovieData(text: String) {
        val temp = intent.getStringExtra("userName")
        val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())
        val movie = MovieModel(text, currentDateTimeString)
        movieList.add(movie)
        moviesAdapter.notifyDataSetChanged()
    }
    //livedata viewmodel room database
    @SuppressLint("SetTextI18n")
    private fun giveResponse(text: String) {
        val temp = intent.getStringExtra("userName")
        val actualText = textT.text.toString()
        var say = 0
        val r1 = arrayListOf("Hello", "Hi", "hey", "Hola", "hoi", "hui", "hello", "hi", "Hey", "hii", "hiii", "hola")
        val g1 = arrayListOf(
            "Hey $temp, how's going Everything?",
            "Hola hui $temp nice to see you back again",
            "Hey there... Glad to see you back :)",
            "ZOOOOOm Back Yo There $temp!",
            "Hey Champion! I have a quote for you would you like to see??",
            "Yo there give a High Five!",
            "hola $temp :)",
            "Welcome Welcome it's a lovely day oh $temp you came long I'm little sad")
        val g2 = arrayListOf("Yep, :)", "Yeah Sure!", "Yes! Opening please wait $temp", "Opening", "Yes Commander! Salute HaHa :)")
        val r2 = arrayListOf("open", "Open", "0", "0", "0", "0", "0", "0", "0", "0", "0",)
        val g3 = arrayListOf("Showing you my Today's magic", "Yes One magic I have for you..watch it", "Do you itself know magic")
        val g4 = arrayListOf("You know what I just Love Dancing It's one of my hobby!", "Okay :)", "Okay then look..!!")
        val r3 = arrayListOf("Magic", "magic", "unique", "0", "0", "0", "0", "0", "0", "0", "0", "0")
        val r4 = arrayListOf("Dance", "dance", "0", "0", "0", "0", "0", "0", "0", "0", "0")
        val word = actualText.split(" ")
        val words = arrayListOf("1")
        for(i in word){
            words.add(i)
        }
        val wl = words.size
        val rl = r1.size
        for(i in 0 until wl) {
            if (wl < rl) {
                words.add(" ")
            }
            if (wl == rl) {
                say = 1
            }
        }
        for(i in 0 until wl){
            if(words[i] in r1) prepareMovieData(g1.random())
            else say = 0
        }
        if(say == 0){
            if (get(wl, words, r2) == 1) prepareMovieData(g2.random())
            else if (get(wl, words, r3) == 1) prepareMovieData(g3.random())
            else if (get(wl, words, r4) == 1) prepareMovieData(g4.random())
        }
    }
    private fun get(wl:Int, words: ArrayList<String>, r:ArrayList<String>): Int {
        var say = 0
        for (i in 0 until wl) {
            if (words[i] in r)
                say = 1
            else continue
        }
        return if(say == 1)
            1
        else 0
    }
    private fun showVideo(){
        // assigning id of VideoView from
        // activity_main.xml layout file
        simpleVideoView = findViewById<View>(R.id.videoView) as VideoView
        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(this)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.simpleVideoView)
        }
        // set the media controller for video view
        simpleVideoView!!.setMediaController(mediaControls)

        // set the absolute path of the video file which is going to be played
        simpleVideoView!!.setVideoURI(
            Uri.parse("android.resource://"
                    + packageName + "/" + R.raw.videoplayback))
        simpleVideoView!!.requestFocus()
        // starting the video
        simpleVideoView!!.start()
        // display a toast message if any
        // error occurs while playing the video
        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occurred " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }
    }
}