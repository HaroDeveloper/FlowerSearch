package element.list.flowersmvp.utils

import android.text.Editable
import android.text.TextWatcher

abstract class CustomTextWatcher : TextWatcher {
    abstract override fun afterTextChanged(text: Editable?)

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}