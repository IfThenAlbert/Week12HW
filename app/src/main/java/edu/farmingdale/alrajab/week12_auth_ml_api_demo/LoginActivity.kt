package edu.farmingdale.alrajab.week12_auth_ml_api_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import edu.farmingdale.alrajab.week12_auth_ml_api_demo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        imageView = findViewById(R.id.googlelogo)

        imageView.setOnClickListener { googleAut() }

        binding.signUpTv.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener{
            login();
        }
    }

    private fun login() {
        val email = binding.emailET.text.toString()
        val pass = binding.passET.text.toString()

        if (email.isNotEmpty() && pass.isNotEmpty()) {

            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, LandingActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        } else {
            Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

        }
    }

    fun googleAut(){

        var inint = Intent(this@LoginActivity,GoogleSignInActivity::class.java)
        startActivity(inint)
    }


}