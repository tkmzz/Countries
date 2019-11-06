package br.com.luiz.countries.ui.configurations

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.luiz.countries.auth.login.LoginActivity
import br.com.luiz.countries.R
import com.google.firebase.auth.FirebaseAuth

class ConfigurationFragment : Fragment() {

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_config, container, false)
        val logout = view.findViewById<Button>(R.id.btLogout)
        val imgGit = view.findViewById<ImageView>(R.id.imgGit)

        imgGit.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://github.com/tkmzz"))
            startActivity(intent)
        }

        //Logout code
        logout.setOnClickListener {

            Toast.makeText(this.getContext(), "Successful logout!", Toast.LENGTH_SHORT).show()
            FirebaseAuth.getInstance().signOut()
            activity?.let { callingActivity -> startActivity(Intent(callingActivity, LoginActivity::class.java)) }

        }


        return view
    }


}


