package br.com.luiz.countries.ui.countries

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.luiz.countries.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //PRINT OF DETAILS
        val countryName = getIntent().getStringExtra("countryName")
        val countryCapital = getIntent().getStringExtra("countryCapital")
        val countryContinent = getIntent().getStringExtra("countryContinent")
        txtCountryName.text = countryName
        txtCapitalName.text = countryCapital
        txtContinentName.text = countryContinent

        //FAVORITE TOGGLE AND CODING
        val myFav = findViewById<ToggleButton>(R.id.tBtFavorite)
        myFav.setChecked(false)
        myFav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite))
        myFav.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView:CompoundButton, isChecked:Boolean) {
                if (isChecked){

                    myFav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_full))
                    Toast.makeText(this@DetailsActivity , "Successfully favorited!", Toast.LENGTH_SHORT).show()

                } else{
                    myFav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite))
                }
            }
        })
        myFav.setOnClickListener {
            intent.putExtra("countryNameFav", countryName)
        }
    }
}