import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.beeline.demo.cocktailapp.R

class InformationDialogFragment(
    val title: Int? = R.string.information,
    val message: Int,
    @StringRes val positiveButtonText: Int? = R.string.ok,
    @StringRes val negativeButtonText: Int? = null
) : DialogFragment() {

    interface ConfirmationListener {
        fun confirmButtonClicked()
        fun cancelButtonClicked()
    }

    private lateinit var listener: ConfirmationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as ConfirmationListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement ConfirmationListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(true)
        positiveButtonText?.let {
            dialog.setPositiveButton(it) { _, _ ->
                listener.confirmButtonClicked()
            }
        }
        negativeButtonText?.let {
            dialog.setNegativeButton(it) { _, _ ->
                listener.cancelButtonClicked()
            }
        }
        return dialog.create()
    }
}