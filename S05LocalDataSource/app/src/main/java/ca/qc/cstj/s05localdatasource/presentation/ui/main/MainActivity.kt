package ca.qc.cstj.s05localdatasource.presentation.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintAttribute
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.notifyAllItemChange
import ca.qc.cstj.s05localdatasource.databinding.ActivityMainBinding
import ca.qc.cstj.s05localdatasource.domain.models.Contact
import ca.qc.cstj.s05localdatasource.presentation.adapters.AsyncContactRecyclerViewAdapter
import ca.qc.cstj.s05localdatasource.presentation.adapters.ContactRecyclerViewAdapter
import ca.qc.cstj.s05localdatasource.presentation.ui.dialogs.ContactDialogFragment
import ca.qc.cstj.s05localdatasource.presentation.ui.dialogs.ContactDialogFragment.Companion.CONTACT_DIALOG_TAG
import ca.qc.cstj.s05localdatasource.presentation.ui.dialogs.ContactDialogFragment.Companion.CONTACT_NAME

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    //private lateinit var contactRecyclerViewAdapter: ContactRecyclerViewAdapter
    private lateinit var contactRecyclerViewAdapter: AsyncContactRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //contactRecyclerViewAdapter = ContactRecyclerViewAdapter(listOf())
        contactRecyclerViewAdapter = AsyncContactRecyclerViewAdapter(this::onContactItemClick)
        binding.rcvContacts.layoutManager = LinearLayoutManager(this)
        binding.rcvContacts.adapter = contactRecyclerViewAdapter

        val itemTouchHelperCallback = object
            : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val contact = contactRecyclerViewAdapter.differ.currentList[viewHolder.adapterPosition]
                when(direction) {
                    ItemTouchHelper.RIGHT -> {
                        viewModel.delete(contact)
                        contactRecyclerViewAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                    }
                    ItemTouchHelper.LEFT -> {
                        contact.firstName = "Elden"
                        contact.lastName = "Lord"
                        contact.isOnline = !contact.isOnline

                        viewModel.update(contact)

                        contactRecyclerViewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                }
            }

        }

        //val itemTouchHelp = ItemTouchHelper(itemTouchHelperCallback)
        //itemTouchHelp.attachToRecyclerView(binding.rcvContacts)

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rcvContacts)
        }

        viewModel.contacts.observe(this) {
            //contactRecyclerViewAdapter.contacts = it
            //contactRecyclerViewAdapter.notifyAllItemChange()
            contactRecyclerViewAdapter.differ.submitList(it)
        }

        binding.btnAdd.setOnClickListener {
            viewModel.createContact("Elden", "Ring", true)
        }
    }

    private fun onContactItemClick(contact: Contact) {
        //Toast.makeText(this, contact.fullName, Toast.LENGTH_SHORT).show()
        val args = Bundle()
        args.putString(CONTACT_NAME, contact.fullName)
        ContactDialogFragment().apply {
            arguments = args
        }.show(supportFragmentManager, CONTACT_DIALOG_TAG)
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

}