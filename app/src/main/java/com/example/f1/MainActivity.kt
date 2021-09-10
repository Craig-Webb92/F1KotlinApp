package com.example.f1

import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.f1.Response.JSONResponse
import com.example.f1.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.net.URL

import java.util.*
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }



        // Update TextView every second
        handler.post(object : Runnable {
            override fun run() {
                val circuitText: TextView = findViewById(R.id.circuitName)
                var time = listOf<String>()
                var date = listOf<String>()
                var circuitName = "Hello"
                val policy = ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                try {
                    var response = URL("http://ergast.com/api/f1/current/next.json").readText()
                    var gson = Gson()


                    var commentResponse = gson.fromJson(response, JSONResponse::class.java)

                    var raceTime = (commentResponse.mRData.raceTable.races[0].time)
                    var raceDate = (commentResponse.mRData.raceTable.races[0].date)

                    time = raceTime.split(":")
                    date = raceDate.split("-")


                    var circuit = (commentResponse.mRData.raceTable.races[0].circuit.circuitId)

                    circuitName = circuit.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                }
                catch (e2: JSONException)
                {
               //     println("Exception Thrown?")
                    e2.printStackTrace();
                }

                circuitText.text = "$circuitName"

                // Keep the postDelayed before the updateTime(), so when the event ends, the handler will stop too.
                handler.postDelayed(this, 1000)
                updateTime(time, date)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun updateTime(time: List<String>, date: List<String>) {

        val countdownText: TextView = findViewById(R.id.countdown_text)
    // Set Current Date
        val currentDate = Calendar.getInstance()
       // println(currentDate)
        // Set Event Date
        val eventDate = Calendar.getInstance()
        eventDate[Calendar.YEAR] = date[0].toInt()
        eventDate[Calendar.MONTH] = date[1].toInt() - 1 // 0-11 so 1 less
        eventDate[Calendar.DAY_OF_MONTH] = date[2].toInt()
        eventDate[Calendar.HOUR_OF_DAY] = time[0].toInt() - 1 // 0-23
        eventDate[Calendar.MINUTE] = time[1].toInt()
        eventDate[Calendar.SECOND] = 0
        eventDate.timeZone = TimeZone.getTimeZone("GMT")
      //  println("HELP: " + eventDate)
        // Find how many milliseconds until the event
        val diff = eventDate.timeInMillis - currentDate.timeInMillis
     //   println("diff: " + diff)
        // Change the milliseconds to days, hours, minutes and seconds
        val days = (diff / (24 * 60 * 60 * 1000))
        val hours = (diff / (1000 * 60 * 60) % 24)
        val minutes = (diff / (1000 * 60) % 60)
        val seconds = ((diff / 1000) % 60)

        // Display Countdown
        countdownText.text = "${days}d ${hours}h ${minutes}m ${seconds}s"
        // Show different text when the event has passed
        endEvent(currentDate, eventDate)
    }

    private fun endEvent(currentDate: Calendar, eventDate: Calendar) {
        val countdownText: TextView = findViewById(R.id.countdown_text)
        if (currentDate.time >= eventDate.time) {
            countdownText.text = "RACE DAY!"
            //Stop Handler
            handler.removeMessages(0)
        }
    }



}