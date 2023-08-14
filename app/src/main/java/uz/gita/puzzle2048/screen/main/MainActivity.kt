package uz.gita.puzzle2048.screen.main

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import uz.gita.puzzle2048.utils.MyTouchListener
import uz.gita.puzzle2048.model.SideEnum
import uz.gita.puzzle2048.repository.AppRepository
import uz.gita.puzzle2048.utils.BackgroundUtil
import uz.gita.puzzle2048_0423.R

class MainActivity : AppCompatActivity() {

    private val items: MutableList<TextView> = ArrayList(16)
    private lateinit var mainView: LinearLayoutCompat
    private val repository = AppRepository.getInstance()
    private val util = BackgroundUtil()
    private lateinit var home: ImageView
    private lateinit var replay: ImageView
    private lateinit var restartBtn: ImageView
    private lateinit var score: TextView
    private lateinit var high: TextView
    private var logic = true

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.mainView)
        loadViews()

//        replay.isEnabled = false

        if (repository.getBoolean()) describeMatrix()
        else if (!repository.getBooleanLast()) {
            repository.resumeLast()
            describeMatrix()
        } else describeMatrix2()
        click()

        val myTouchListener = MyTouchListener(this)
        myTouchListener.setMyMovementSideListener {
            when (it) {
                SideEnum.LEFT -> {
                    repository.moveToLeft()
                    describeMatrix()
                    gaveOver()
//                    replay.isEnabled = true
                }
                SideEnum.RIGHT -> {
                    repository.moveToRight()
                    describeMatrix()
                    gaveOver()
//                    replay.isEnabled = true
                }
                SideEnum.UP -> {
                    repository.moveToUp()
                    describeMatrix()
                    gaveOver()
//                    replay.isEnabled = true
                }
                SideEnum.DOWN -> {
                    repository.moveToDown()
                    describeMatrix()
                    gaveOver()
//                    replay.isEnabled = true
                }
            }
        }
        mainView.setOnTouchListener(myTouchListener)
    }

    private fun loadViews() {
        for (i in 0 until mainView.childCount) {
            val linear = mainView.getChildAt(i) as LinearLayoutCompat
            for (j in 0 until linear.childCount) {
                items.add(linear.getChildAt(j) as TextView)
            }
        }

        home = findViewById(R.id.home)
        replay = findViewById(R.id.replay)
        restartBtn = findViewById(R.id.restart)

        score = findViewById(R.id.scoreText)
        high = findViewById(R.id.highScoreText)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun click() {
        home.setOnClickListener {
            repository.saveBoolean(false)
            if (repository.getBooleanLast()) {
                repository.saveInt()
            }
            finish()
        }
        restartBtn.setOnClickListener {
            repository.restartViews()
            describeMatrix()
//            replay.isEnabled = false
        }
    }

    private fun describeMatrix() {
        val _matrix = repository.matrix
        score.text = repository.getScore().toString()
        high.text = repository.getHigh().toString()

        for (i in _matrix.indices) {
            for (j in _matrix[i].indices) {
                items[i * 4 + j].apply {
                    text = if (_matrix[i][j] == 0) ""
                    else _matrix[i][j].toString()
                    setBackgroundResource(util.colorByAmount(_matrix[i][j]))
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun describeMatrix2() {

        var _matrix = arrayOf(
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0)
        )

        score.text = repository.getScore().toString()
        high.text = repository.getHigh().toString()

        for (i in _matrix.indices) {
            for (j in _matrix.indices) {
                _matrix[i][j] = repository.getInt("POSITIONS${i * 4 + j}")
            }
        }

        for (i in _matrix.indices) {
            for (j in _matrix[i].indices) {
                items[i * 4 + j].apply {
                    text = if (_matrix[i][j] == 0) ""
                    else _matrix[i][j].toString()

                    setBackgroundResource(util.colorByAmount(_matrix[i][j]))
                }
            }
        }

    }

    private fun gaveOver() {
        if (repository.isGameOver() && logic) {
            logic = false
            val view: View = LayoutInflater.from(this).inflate(R.layout.gameover_dialog, null)
            val alertDialog: AlertDialog = AlertDialog.Builder(this).setView(view).setCancelable(false).create()

            alertDialog.show()
            alertDialog.window?.setBackgroundDrawable(null)

            view.findViewById<AppCompatButton>(R.id.menuDg).setOnClickListener {
                alertDialog.dismiss()
                repository.restartViews()
                finish()
            }

            view.findViewById<AppCompatButton>(R.id.restartDG).setOnClickListener {
                repository.restartViews()
                describeMatrix()
                logic = true
                alertDialog.dismiss()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        repository.saveBoolean(false)
        if (repository.getBooleanLast()) {
            repository.saveInt()
        }
    }

}