package co.com.credibanco.presentation.authorization.request

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.credibanco.databinding.ActivityAuthorizationRequestBinding

class AuthorizationRequestActivity : AppCompatActivity() {

    companion object {

        fun getIntent(context: Context) = Intent(context, AuthorizationRequestActivity::class.java)
    }

    private lateinit var binding: ActivityAuthorizationRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationRequestBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }
}
