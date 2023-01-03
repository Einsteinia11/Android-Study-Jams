package com.example.sunlight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var userPass : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.name)
        userPass = findViewById(R.id.user_password)
        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)
        button.setOnClickListener {
            val u = name.text.toString()
            val p = userPass.text.toString()
            try{
                val fileName = "$filesDir/${u}.txt"
                val file = File(fileName)
                val ins: InputStream = file.inputStream()
                // read contents of InputStream to String
                val content = ins.bufferedReader().readLines()
                if(content[1] == p || content[0] == u){
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("userName", u)
                    intent.putExtra("password", p)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Name or Password found wrong!", Toast.LENGTH_LONG).show()
                }
            }
            catch (e: FileNotFoundException) {
                Toast.makeText(this, "Couldn't find account..create one now!", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
            catch (e: IOException) {
                Toast.makeText(this, "Oops Error occurred type correctly!", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
        button2.setOnClickListener {
            val intent1 = Intent(this, CreateAccount::class.java)
            startActivity(intent1)
        }
    }
}