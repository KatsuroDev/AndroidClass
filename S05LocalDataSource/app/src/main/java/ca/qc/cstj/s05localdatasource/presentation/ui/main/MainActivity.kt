package ca.qc.cstj.s05localdatasource.presentation.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.core.notifyAllItemChange
import ca.qc.cstj.s05localdatasource.databinding.ActivityMainBinding
import ca.qc.cstj.s05localdatasource.domain.models.Contact
import ca.qc.cstj.s05localdatasource.presentation.adapters.ContactRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var contactRecyclerViewAdapter: ContactRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        contactRecyclerViewAdapter = ContactRecyclerViewAdapter(listOf())
        binding.rcvContacts.layoutManager = LinearLayoutManager(this)
        binding.rcvContacts.adapter = contactRecyclerViewAdapter

        viewModel.contacts.observe(this) {
            contactRecyclerViewAdapter.contacts = it
            contactRecyclerViewAdapter.notifyAllItemChange()
        }

        binding.btnAdd.setOnClickListener {
            viewModel.createContact("Test", "Test", true)
        }
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

}