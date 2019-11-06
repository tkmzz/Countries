package br.com.luiz.countries.ui.countries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.luiz.countries.R
import br.com.luiz.countries.adapters.CountryListAdapter
import br.com.luiz.countries.model.Country
import com.google.firebase.database.*


class CountriesFragment : Fragment() {

    lateinit var ref: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        ref = FirebaseDatabase.getInstance().getReference("countries")
        val view = inflater.inflate(R.layout.fragment_countries, container, false)
        val mRecycleView = view.findViewById(R.id.rvCountries) as RecyclerView
        mRecycleView.setLayoutManager(LinearLayoutManager(getActivity()))
        val countryList: MutableList<Country> = ArrayList()
        val mAdapter = CountryListAdapter(context, countryList)


        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for(h in p0.children){
                        val country = h.getValue(Country::class.java)
                        countryList.add(country!!)
                    }
                    mRecycleView.setAdapter(mAdapter)
                }
            }
        })



        //BUTTON TO ADD A COUNTRY
        val add = view.findViewById(R.id.btAdd) as Button
        add.setOnClickListener {

            val nextScreen = Intent(context, AddCountryActivity::class.java)
            startActivity(nextScreen)
        }


        return view
    }



}