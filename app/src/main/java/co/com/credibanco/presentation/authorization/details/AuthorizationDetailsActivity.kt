package co.com.credibanco.presentation.authorization.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import co.com.credibanco.R
import co.com.credibanco.databinding.ActivityAuthorizationDetailsBinding
import co.com.credibanco.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationDetailsActivity :
    BaseActivity<AuthorizationDetailsViewModel, AuthorizationDetailsViewState, AuthorizationDetailsViewEvent>() {

    companion object {

        private const val KEY_RECEIPT_ID = "receiptId"
        private const val KEY_RRN = "rrn"
        private const val KEY_STATUS_CODE = "statusCode"
        private const val KEY_STATUS_DESCRIPTION = "statusDescription"

        fun getIntent(
            receiptId: String,
            rrn: String,
            statusCode: String,
            statusDescription: String,
            context: Context
        ) = Intent(context, AuthorizationDetailsActivity::class.java).apply {
            putExtra(KEY_RECEIPT_ID, receiptId)
            putExtra(KEY_RRN, rrn)
            putExtra(KEY_STATUS_CODE, statusCode)
            putExtra(KEY_STATUS_DESCRIPTION, statusDescription)
        }
    }

    override val viewModel: AuthorizationDetailsViewModel by viewModels()
    private lateinit var binding: ActivityAuthorizationDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationDetailsBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }

    override fun buildState(state: AuthorizationDetailsViewState) {
        when (state) {
            AuthorizationDetailsViewState.Initial -> buildInitialState()
            AuthorizationDetailsViewState.Loading -> buildLoadingState()
            is AuthorizationDetailsViewState.Content -> buildContentState(state)
            is AuthorizationDetailsViewState.Error -> buildErrorState(state)
        }
    }

    private fun buildInitialState() {
        val valueReceiptId = intent.getStringExtra(KEY_RECEIPT_ID).orEmpty()
        val valueRrn = intent.getStringExtra(KEY_RRN).orEmpty()
        val valueStatusCode = intent.getStringExtra(KEY_STATUS_CODE).orEmpty()
        val valueStatusDescription = intent.getStringExtra(KEY_STATUS_DESCRIPTION).orEmpty()

        with(binding) {
            receiptId.text = valueReceiptId
            rrn.text = valueRrn
            statusCode.text = valueStatusCode
            statusDescription.text = valueStatusDescription
        }

        binding.buttonRequestAnnulment.setOnClickListener {
            dispatchEvent(
                AuthorizationDetailsViewEvent.RequestAnnulment(valueReceiptId, valueRrn)
            )
        }
    }

    private fun showProgress(isVisible: Boolean = true) {
        val (isFieldVisible, isProgressVisible) = if (isVisible) {
            Pair(View.GONE, View.VISIBLE)
        } else {
            Pair(View.VISIBLE, View.GONE)
        }

        with(binding) {
            labelReceiptId.visibility = isFieldVisible
            labelStatusCode.visibility = isFieldVisible
            labelRrn.visibility = isFieldVisible
            labelStatusDescription.visibility = isFieldVisible

            receiptId.visibility = isFieldVisible
            statusCode.visibility = isFieldVisible
            rrn.visibility = isFieldVisible
            statusDescription.visibility = isFieldVisible

            buttonRequestAnnulment.visibility = isFieldVisible

            progressBar.visibility = isProgressVisible
        }
    }

    private fun buildLoadingState() {
        showProgress()
    }

    private fun buildContentState(state: AuthorizationDetailsViewState.Content) {
        showProgress(false)

        val (statusCode, statusDescription) = state.annulment

        showModalDialog(
            "Response",
            "Status code: $statusCode\n" +
                    "Status Description: $statusDescription"
        ) {
            finish()
        }
    }

    private fun buildErrorState(state: AuthorizationDetailsViewState.Error) {
        showProgress(false)

        showModalDialog(
            getString(R.string.text_error),
            state.message
        ) {}
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
}
