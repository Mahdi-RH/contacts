package com.mohaymen.codeassignment.contacts.presentation.main.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mohaymen.codeassignment.contacts.databinding.ItemContactsViewBinding
import com.mohaymen.codeassignment.contacts.domain.model.Contact
import com.mohaymen.codeassignment.contacts.presentation.base.BaseViewHolder

class ContactsListAdapter :
    ListAdapter<Contact, ContactsListAdapter.ContactsViewHolder>(DiffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            ItemContactsViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ContactsViewHolder(private val item: ItemContactsViewBinding) :
        BaseViewHolder<Contact>(item.root) {
        override fun onBind(obj: Contact) {
            item.txtContactItem.text = obj.name.plus("\n").plus(obj.phoneNumber)
        }

    }


    object DiffCallBack : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }


}