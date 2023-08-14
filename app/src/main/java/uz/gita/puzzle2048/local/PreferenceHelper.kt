package uz.gita.puzzle2048.local

import android.content.Context

class PreferenceHelper private constructor(context: Context){

    private val pref = context.getSharedPreferences("2048",Context.MODE_PRIVATE)
    private val editor = pref.edit()

    companion object{
        private lateinit var instance: PreferenceHelper
        fun init(context: Context){
            if (!(::instance.isInitialized))
                instance = PreferenceHelper(context)
        }
        fun getInstance() = instance
    }

    fun saveInt(s: String, num: Int) {
        editor.putInt(s, num).apply()
    }

    fun getInt(s: String): Int {
        return pref.getInt(s, 0)
    }

    fun saveBoolean(b: Boolean) {
        editor.putBoolean("BOOLEAN", b).apply()
    }

    fun getBoolean(): Boolean {
        return pref.getBoolean("BOOLEAN", true)
    }

    fun saveBooleanLAST(b: Boolean) {
        editor.putBoolean("BOOLEANLAST", b).apply()
    }

    fun getBooleanLAST(): Boolean {
        return pref.getBoolean("BOOLEANLAST", true)
    }

    fun saveScore(score: Int){
        editor.putInt("SCORE",score).apply()
    }

    fun getScore() : Int{
        return pref.getInt("SCORE",0)
    }

    fun saveHigh(score: Int){
        editor.putInt("SCOREHIGH",score).apply()
    }

    fun getHigh() : Int{
        return pref.getInt("SCOREHIGH",0)
    }

    fun saveLast(score: Int){
        editor.putInt("SCORELAST",score).apply()
    }

    fun getLast() : Int{
        return pref.getInt("SCORELAST",0)
    }
}