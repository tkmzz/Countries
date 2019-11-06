package br.com.luiz.countries.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.luiz.countries.R
import br.com.luiz.countries.model.Country
import br.com.luiz.countries.ui.countries.DetailsActivity


class CountryListAdapter(private val context: Context?,
    private val countriesList: MutableList<Country>)
    : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country: Country = countriesList[position]
        holder.countryName?.text = country.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("country", country)
            intent.putExtra("countryName", country.name)
            intent.putExtra("countryCapital", country.capital)
            intent.putExtra("countryContinent", country.continent)
            context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.country_row, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return countriesList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val countryName = itemView.findViewById<TextView>(R.id.tvCountry)
    }

}



