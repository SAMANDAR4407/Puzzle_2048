package uz.gita.puzzle2048.screen.enter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import uz.gita.puzzle2048.screen.info.InfoActivity
import uz.gita.puzzle2048.screen.main.MainActivity
import uz.gita.puzzle2048_0423.R

class EnterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)

        findViewById<AppCompatButton>(R.id.playBtn).setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<AppCompatButton>(R.id.infoBtn).setOnClickListener{
            startActivity(Intent(this, InfoActivity::class.java))
        }

        findViewById<AppCompatButton>(R.id.exitBtn).setOnClickListener{
            finish()
        }
    }
}