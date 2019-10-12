package p185296_m203380.ft.unicamp.aula03_fragmentos.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import p185296_m203380.ft.unicamp.aula03_fragmentos.MainActivity
import p185296_m203380.ft.unicamp.aula03_fragmentos.R

class EmptyActivity : AppCompatActivity() {

    val KEY: String? = null
    var text: EditText? = null
    var textView: TextView? = null
    var memoria: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        text = findViewById(R.id.random_text)
        textView = findViewById(R.id.random_text_view)
        pushButton()
        pullButton()

        if (savedInstanceState != null) {
            memoria = savedInstanceState.getString(KEY, "")
        }
    }

    fun pushButton() {
        val randomButton: Button = findViewById(R.id.push_button)
        randomButton.setOnClickListener {
            memoria = text?.text.toString()
        }
    }

    fun pullButton() {
        val randomButton: Button = findViewById(R.id.pull_button)
        randomButton.setOnClickListener {
            if (memoria == null) {
                Toast.makeText(this, "Você não possui texto para setar", Toast.LENGTH_SHORT).show()
            } else {
                textView?.append(memoria)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, textView?.text.toString())
    }
}
