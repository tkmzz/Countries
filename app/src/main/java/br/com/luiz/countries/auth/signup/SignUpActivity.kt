package br.com.luiz.countries.auth.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.luiz.countries.R
import br.com.luiz.countries.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        btCreate.setOnClickListener{
            mAuth.createUserWithEmailAndPassword(
                etEmail.text.toString(),
                etPassword.text.toString()
            ).addOnCompleteListener {
                if(it.isSuccessful){
                    save()
                } else{
                    Toast.makeText(this@SignUpActivity,
                        it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun save() {

        val user = User(etName.text.toString(),
            etEmail.text.toString(),
            etPhone.text.toString())

        FirebaseDatabase.getInstance().getReference("User")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this@SignUpActivity,
                        "Successful created!", Toast.LENGTH_LONG).show()
                    val intent = Intent()
                    intent.putExtra("email", etEmail.text.toString())
                    intent.putExtra("password", etPassword.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else{
                    Toast.makeText(this@SignUpActivity,
                        it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }

    }
}
