
import android.view.View
import android.widget.EditText

fun View.invisible() {
    visibility = View.INVISIBLE
}
fun View.gone() {
    visibility = View.GONE
}
fun View.visible() {
    visibility = View.VISIBLE
}
fun EditText.getStringText() = text.toString()


