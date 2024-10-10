package com.example.androiddatatime

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    private val GALLERY_REQUEST = 1
    val DAY = 0
    val MONTH = 1
    val YEAR = 2
    private var photoURI: Uri? = null
    private lateinit var person: Person
    private lateinit var toolbar: Toolbar
    private lateinit var photoIV: ImageView
    private lateinit var firstNameET: EditText
    private lateinit var secondNameET: EditText
    private lateinit var dateET: EditText
    private lateinit var saveBTN: Button

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        setSupportActionBar(toolbar)

        saveBTN.setOnClickListener {
            val dateList = dateET.text.toString().split(".")
            val date = LocalDate.of(dateList[YEAR].toInt(), dateList[MONTH].toInt(), dateList[DAY].toInt())
            person =
                Person(
                    firstNameET.text.toString(),
                    secondNameET.text.toString(),
                    date,
                    photoURI.toString()
                )
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(Person::class.java.simpleName, person)
            startActivity(intent)
        }

        photoIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

    }

    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        photoIV = findViewById(R.id.photoIV)
        firstNameET = findViewById(R.id.firstName)
        secondNameET = findViewById(R.id.secondName)
        dateET = findViewById(R.id.dateET)
        saveBTN = findViewById(R.id.save)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GALLERY_REQUEST -> {
                if (resultCode == RESULT_OK) {
                    photoURI = data?.data
                }
            }
        }
        photoIV.setImageURI(photoURI)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit -> {
                finishAffinity()
            }
        }
        return true
    }

}