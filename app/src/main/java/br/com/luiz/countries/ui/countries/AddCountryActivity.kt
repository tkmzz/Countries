package br.com.luiz.countries.ui.countries

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.luiz.countries.MainActivity
import br.com.luiz.countries.R
import br.com.luiz.countries.model.Country
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_country.*

class AddCountryActivity : AppCompatActivity() {


    lateinit var plainCountry: EditText
    lateinit var plainCapital: EditText
    lateinit var plainContinent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_country)

        plainCountry = findViewById(R.id.edtCountry)
        plainCapital = findViewById(R.id.edtCapital)
        plainContinent = findViewById(R.id.edtContinent)

        btAddToDb.setOnClickListener {
            addCountry()
        }

    }

    private fun addCountry() {

        val name = plainCountry.text.toString().trim()
        val capital = plainCapital.text.toString().trim()
        val continent = plainContinent.text.toString().trim()

        if(name.isEmpty() || capital.isEmpty() || continent.isEmpty()){
            Toast.makeText(this, "Please fill the form", Toast.LENGTH_SHORT).show()
        } else{
            val ref = FirebaseDatabase.getInstance().getReference("countries")
            val countryId = ref.push().key.toString()

            val country = Country(countryId, name, capital, continent)

            ref.child(countryId).setValue(country).addOnCompleteListener {
                Toast.makeText(this, "Country saved successfully!", Toast.LENGTH_SHORT).show()
                val listScreen = Intent(this, MainActivity::class.java)
                startActivity(listScreen)
            }
        }
    }
}