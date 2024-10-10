package com.example.androiddatatime

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.Period

class SecondActivity : AppCompatActivity() {
    val DAY = 0
    val MONTH = 1
    val YEAR = 2
    private lateinit var toolbar:Toolbar
    private lateinit var photoIV:ImageView
    private lateinit var firstNameTV: TextView
    private lateinit var secondNameTV: TextView
    private lateinit var dateTV: TextView
    private lateinit var ageTV: TextView
    private lateinit var countTimeToBirthdayTV: TextView

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        val person = intent.getSerializableExtra(Person::class.java.simpleName, Person::class.java)

        setAllView(person)

        setSupportActionBar(toolbar)

    }
    private fun init(){
        toolbar = findViewById(R.id.toolbar)
        photoIV = findViewById(R.id.imageIV)
        firstNameTV = findViewById(R.id.firstName)
        secondNameTV = findViewById(R.id.secondName)
        dateTV = findViewById(R.id.date)
        ageTV = findViewById(R.id.age)
        countTimeToBirthdayTV = findViewById(R.id.countTimeToBirthday)
    }
    @SuppressLint("NewApi", "SetTextI18n")
    private fun setAllView(person: Person?){
        photoIV.setImageURI(person!!.image.toUri())
        firstNameTV.text = person.firstName
        secondNameTV.text = person.secondName
        dateTV.text = person.dataBirthday.toString()
        ageTV.text = (LocalDate.now().year-person.dataBirthday.year).toString()
        val difMonth = (Period.between(person.dataBirthday, LocalDate.now()).months)
        val difDay = (Period.between(person.dataBirthday, LocalDate.now()).days)
        countTimeToBirthdayTV.text = "До ДР осталось $difMonth месяцев $difDay дней"
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exit->{
                finishAffinity()
            }
        }
        return true
    }
}