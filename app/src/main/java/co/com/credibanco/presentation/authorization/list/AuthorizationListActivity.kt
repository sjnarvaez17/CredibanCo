package co.com.credibanco.presentation.authorization.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.credibanco.databinding.ActivityAuthorizationListBinding

class AuthorizationListActivity : AppCompatActivity() {

    companion object {

        fun getIntent(context: Context) = Intent(context, AuthorizationListActivity::class.java)
    }

    private lateinit var binding: ActivityAuthorizationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationListBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }
}
