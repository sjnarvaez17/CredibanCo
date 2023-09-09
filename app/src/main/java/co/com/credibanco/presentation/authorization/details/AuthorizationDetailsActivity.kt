package co.com.credibanco.presentation.authorization.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.credibanco.databinding.ActivityAuthorizationDetailsBinding

class AuthorizationDetailsActivity: AppCompatActivity() {

    companion object {

        fun getIntent(context: Context) = Intent(context, AuthorizationDetailsActivity::class.java)
    }

    private lateinit var binding: ActivityAuthorizationDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationDetailsBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }
}