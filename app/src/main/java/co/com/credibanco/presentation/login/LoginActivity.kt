package co.com.credibanco.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import co.com.credibanco.R
import co.com.credibanco.databinding.ActivityLoginBinding
import co.com.credibanco.presentation.BaseActivity
import co.com.credibanco.presentation.main_menu.MainMenuActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<LoginViewModel, LoginViewState, LoginViewEvent>() {

    companion object {

        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }

    override fun buildState(state: LoginViewState) {
        when (state) {
            LoginViewState.Initial -> buildInitialState()
            LoginViewState.Loading -> buildLoadingState()
            is LoginViewState.Content -> buildContentState(state)
            is LoginViewState.Error -> buildErrorState(state)
        }
    }

    private fun buildInitialState() {
        binding.buttonLogin.setOnClickListener {
            val commerceCode = binding.commerceCode.text.toString()
            val terminalCode = binding.terminalCode.text.toString()

            dispatchEvent(LoginViewEvent.LoginClicked(commerceCode, terminalCode))
        }
    }

    private fun buildLoadingState() {
        showProgress()
    }

    private fun buildContentState(state: LoginViewState.Content) {
        showProgress(false)

        startActivity(MainMenuActivity.getIntent(this))
    }

    private fun buildErrorState(state: LoginViewState.Error) {
        showProgress(false)

        val dialogBuilder = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(getString(R.string.text_error))
            .setMessage(state.message)
            .setPositiveButton(getString(R.string.text_ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialogBuilder.show()
    }

    private fun showProgress(isVisible: Boolean = true) {
        val (isFieldVisible, isProgressVisible) = if (isVisible) {
            Pair(View.GONE, View.VISIBLE)
        } else {
            Pair(View.VISIBLE, View.GONE)
        }

        with(binding) {
            labelCommerceCode.visibility = isFieldVisible
            labelTerminalCode.visibility = isFieldVisible
            commerceCode.visibility = isFieldVisible
            terminalCode.visibility = isFieldVisible
            buttonLogin.visibility = isFieldVisible

            progressBar.visibility = isProgressVisible
        }
    }
}