package co.com.credibanco.presentation.authorization.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import co.com.credibanco.databinding.ActivityAuthorizationListBinding
import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationListActivity :
    BaseActivity<AuthorizationListViewModel, AuthorizationListViewState, AuthorizationListViewEvent>(),
    AuthorizationAdapter.AuthorizationAdapterListener {

    companion object {

        fun getIntent(context: Context) = Intent(context, AuthorizationListActivity::class.java)
    }

    override val viewModel: AuthorizationListViewModel by viewModels()
    private lateinit var binding: ActivityAuthorizationListBinding

    private var adapter: AuthorizationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationListBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }

    override fun buildState(state: AuthorizationListViewState) {
        when (state) {
            AuthorizationListViewState.Initial -> buildInitialState()
            AuthorizationListViewState.Loading -> buildLoadingState()
            is AuthorizationListViewState.Content -> buildContentState(state)
            is AuthorizationListViewState.NavigateToAuthDetail -> navigateToAuthDetail(state)
        }
    }

    private fun buildInitialState() {
        initializeAdapter()
        initializeEvents()
        dispatchEvent(AuthorizationListViewEvent.Initialize)
    }

    private fun showProgress(isVisible: Boolean = true) {
        val (isFieldVisible, isProgressVisible) = if (isVisible) {
            Pair(View.GONE, View.VISIBLE)
        } else {
            Pair(View.VISIBLE, View.GONE)
        }

        with(binding) {
            recycler.visibility = isFieldVisible
            noResultsFound.visibility = isFieldVisible

            searchReceiptId.isEnabled = !isVisible

            progressBar.visibility = isProgressVisible
        }
    }

    private fun buildLoadingState() {
        showProgress()
    }

    private fun buildContentState(state: AuthorizationListViewState.Content) {
        showProgress(false)

        val authorizations = state.authorizationList

        val isEmptyList = authorizations.isEmpty()

        val (recyclerVisibility, noResultsVisibility) = if (isEmptyList) {
            Pair(View.GONE, View.VISIBLE)
        } else {
            Pair(View.VISIBLE, View.GONE).also {
                initializeAdapter()
                adapter?.submitList(authorizations)
            }
        }

        with(binding) {
            recycler.visibility = recyclerVisibility
            noResultsFound.visibility = noResultsVisibility
        }
    }

    override fun onItemClicked(authorization: Authorization) {

    }

    private fun initializeEvents() {
        binding.searchReceiptId.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) = Unit

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) = Unit

            override fun onTextChanged(
                text: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (text.isEmpty()) {
                    dispatchEvent(AuthorizationListViewEvent.Initialize)
                } else {
                    dispatchEvent(AuthorizationListViewEvent.SearchReceiptId(text.toString()))
                }
            }
        })
    }

    private fun initializeAdapter() {
        if (adapter == null) {
            adapter = AuthorizationAdapter(this)
            binding.recycler.adapter = adapter
        }
    }

    private fun navigateToAuthDetail(state: AuthorizationListViewState.NavigateToAuthDetail) {}
}
