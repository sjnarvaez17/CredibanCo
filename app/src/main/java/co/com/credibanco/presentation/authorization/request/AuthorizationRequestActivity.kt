package co.com.credibanco.presentation.authorization.request

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import co.com.credibanco.R
import co.com.credibanco.databinding.ActivityAuthorizationRequestBinding
import co.com.credibanco.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationRequestActivity :
    BaseActivity<AuthorizationRequestViewModel, AuthorizationRequestViewState, AuthorizationRequestViewEvent>() {

    companion object {

        fun getIntent(context: Context) = Intent(context, AuthorizationRequestActivity::class.java)
    }

    override val viewModel: AuthorizationRequestViewModel by viewModels()
    private lateinit var binding: ActivityAuthorizationRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationRequestBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }

    override fun buildState(state: AuthorizationRequestViewState) {
        when (state) {
            AuthorizationRequestViewState.Initial -> buildInitialState()
            is AuthorizationRequestViewState.InitializeForm -> buildInitializeFormState(state)
            AuthorizationRequestViewState.Loading -> buildLoadingState()
            is AuthorizationRequestViewState.Content -> buildContentState(state)
            is AuthorizationRequestViewState.Error -> TODO()
        }
    }

    private fun buildInitialState() {
        binding.buttonRequestAuthorization.setOnClickListener {
            dispatchEvent(
                AuthorizationRequestViewEvent.AuthorizationRequestClicked(
                    binding.id.text.toString(),
                    binding.commerceCode.text.toString(),
                    binding.terminalCode.text.toString(),
                    binding.amount.text.toString(),
                    binding.card.text.toString()
                )
            )
        }

        dispatchEvent(AuthorizationRequestViewEvent.Initialize)
    }

    private fun buildInitializeFormState(state: AuthorizationRequestViewState.InitializeForm) {
        with(binding) {
            commerceCode.setText(state.commerceCode)
            terminalCode.setText(state.terminalCode)
        }
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
            labelAmount.visibility = isFieldVisible
            labelCard.visibility = isFieldVisible
            labelId.visibility = isFieldVisible

            commerceCode.visibility = isFieldVisible
            terminalCode.visibility = isFieldVisible
            amount.visibility = isFieldVisible
            card.visibility = isFieldVisible
            id.visibility = isFieldVisible

            buttonRequestAuthorization.visibility = isFieldVisible

            progressBar.visibility = isProgressVisible
        }
    }

    private fun buildLoadingState() {
        showProgress()
    }

    private fun showModalDialog(title: String, message: String, acceptCallback: () -> Unit) {
        val dialogBuilder = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.text_ok)) { dialog, _ ->
                dialog.dismiss()
                acceptCallback()
            }
            .create()

        dialogBuilder.show()
    }

    private fun buildContentState(state: AuthorizationRequestViewState.Content) {
        showProgress(false)
        val (receiptId, rrn, statusCode, statusDescription) = state.authorization

        showModalDialog(
            "Response",
            "Receipt id: $receiptId\n" +
                    "RRN: $rrn\n" +
                    "Status code: $statusCode\n" +
                    "Status Description: $statusDescription"
        ) {
            finish()
        }
    }

    private fun buildErrorState(state: AuthorizationRequestViewState.Error) {
        showProgress(false)

        showModalDialog(
            getString(R.string.text_error),
            state.message
        ) {}
    }
}
