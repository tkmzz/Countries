package br.com.luiz.countries.auth.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.luiz.countries.auth.signup.SignUpActivity
import br.com.luiz.countries.MainActivity
import br.com.luiz.countries.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val CADASTRO_REQUEST_CODE = 1

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        if(mAuth.currentUser != null){
            goToMain()
        }

        btLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                etEmail.text.toString(),
                etPassword.text.toString()
            ).addOnCompleteListener {
                if(it.isSuccessful){
                    goToMain()
                }else {
                    Toast.makeText(this@LoginActivity,
                        it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        btNewAccount.setOnClickListener {
            val signUpScreen = Intent(this, SignUpActivity::class.java)
            startActivityForResult(signUpScreen, CADASTRO_REQUEST_CODE)
        }
    }

    private fun goToMain() {
        val menuScreen = Intent(this, MainActivity::class.java)
        startActivity(menuScreen)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CADASTRO_REQUEST_CODE -> {
                when(resultCode){
                    Activity.RESULT_OK -> {
                        etEmail.setText(data?.getStringExtra("email"))
                        etPassword.setText(data?.getStringExtra("password"))
                    }
                }
            }
        }
    }

}
