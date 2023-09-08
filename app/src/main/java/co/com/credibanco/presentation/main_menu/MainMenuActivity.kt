package co.com.credibanco.presentation.main_menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.credibanco.databinding.ActivityMainMenuBinding
import co.com.credibanco.presentation.authorization.list.AuthorizationListActivity
import co.com.credibanco.presentation.authorization.request.AuthorizationRequestActivity

class MainMenuActivity : AppCompatActivity() {

    companion object {

        fun getIntent(context: Context) = Intent(context, MainMenuActivity::class.java)
    }

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        binding.buttonAuthorizeTransaction.setOnClickListener {
            startActivity(
                AuthorizationRequestActivity.getIntent(this)
            )
        }

        binding.buttonSeeAuthorizedTransactions.setOnClickListener {
            startActivity(
                AuthorizationListActivity.getIntent(this)
            )
        }
    }
}
