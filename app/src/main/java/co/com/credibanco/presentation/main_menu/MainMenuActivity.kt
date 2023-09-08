package co.com.credibanco.presentation.main_menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.credibanco.R

class MainMenuActivity : AppCompatActivity() {

    companion object {

        fun getIntent(context: Context) = Intent(context, MainMenuActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }
}