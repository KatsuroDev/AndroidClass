package ca.qc.cstj.s05localdatasource.presentation.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ca.qc.cstj.s05localdatasource.R

class ContactDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val contactFullName = arguments?.getString(CONTACT_NAME)

        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.contactDialogTitle))
            .setMessage(contactFullName)
            .setPositiveButton("OK") {_, _ ->}.create()
    }

    companion object {
        const val CONTACT_NAME = "CONTACT_NAME"
        const val CONTACT_DIALOG_TAG = "CONTACT_DIALOG_TAG"
    }
}