package com.example.f1

import android.icu.util.TimeUnit
import android.os.Bundle
import android.os.CountDownTimer
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.f1.Response.JSONResponse
import com.example.f1.databinding.FragmentFirstBinding
import com.google.gson.Gson
import org.json.JSONException
import java.net.URL

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            var response = URL("http://ergast.com/api/f1/current/driverStandings.json").readText()
            var gson = Gson()

            var commentResponse = gson.fromJson(response, JSONResponse::class.java)

            var leader = (commentResponse.mRData.standingsTable.standingsLists[0].driverStandings)
            println(leader[0].driver.familyName)
        } catch (e2: JSONException){
            e2.printStackTrace();
        }

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}