package com.example.sunlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

class CreateAccount : AppCompatActivity() {
    private lateinit var username : EditText
    private lateinit var userpassword : EditText
    private lateinit var usergender : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        username = findViewById(R.id.userName)
        userpassword = findViewById(R.id.password)
        usergender = findViewById(R.id.gender)
        val done: Button = findViewById(R.id.done)
        done.setOnClickListener {
            createFile()
        }
    }
    private fun createFile(){
        val u = username.text.toString()
        val p = userpassword.text.toString()
        val g = usergender.text.toString()
        val fileName = "${u}.txt"
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE)
            fos.write(u.toByteArray())
            fos.write("\n${p}".toByteArray())
            fos.write("\n${g}".toByteArray())
            Toast.makeText(this, "Saved to $filesDir/$fileName", Toast.LENGTH_LONG).show()
        }
        catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
