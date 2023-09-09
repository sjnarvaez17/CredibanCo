package co.com.credibanco.presentation.authorization.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.com.credibanco.databinding.AdapterAuthorizationItemBinding
import co.com.credibanco.domain.model.Authorization

class AuthorizationAdapter(private val listener: AuthorizationAdapterListener) :
    ListAdapter<Authorization, AuthorizationAdapter.AuthorizationViewHolder>(
        DiffCallback()
    ) {

    private class DiffCallback : DiffUtil.ItemCallback<Authorization>() {

        override fun areItemsTheSame(oldItem: Authorization, newItem: Authorization) =
            oldItem.receiptId == newItem.receiptId

        override fun areContentsTheSame(oldItem: Authorization, newItem: Authorization) =
            oldItem == newItem
    }

    interface AuthorizationAdapterListener {
        fun onItemClicked(authorization: Authorization)
    }

    class AuthorizationViewHolder(private val binding: AdapterAuthorizationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(authorization: Authorization, listener: AuthorizationAdapterListener) {

            binding.receiptId.text = authorization.receiptId

            binding.root.setOnClickListener { listener.onItemClicked(authorization) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AuthorizationViewHolder(
        AdapterAuthorizationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: AuthorizationViewHolder, position: Int) =
        holder.bind(getItem(position), listener)
}
