package uz.gita.puzzle2048.screen.info
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uz.gita.puzzle2048_0423.R

class InfoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        findViewById<ImageView>(R.id.btn_back).setOnClickListener{
            finish()
        }

        findViewById<TextView>(R.id.pp).movementMethod = LinkMovementMethod.getInstance()
    }
}