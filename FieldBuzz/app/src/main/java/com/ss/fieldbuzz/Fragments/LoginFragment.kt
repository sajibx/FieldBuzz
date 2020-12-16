package com.ss.fieldbuzz.Fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.ss.fieldbuzz.Data.AppSession
import com.ss.fieldbuzz.Data.login
import com.ss.fieldbuzz.R
import org.json.JSONObject


class LoginFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init()
    {
        view!!.findViewById<Button>(R.id.login).setOnClickListener()
        {
            if (view!!.findViewById<EditText>(R.id.username).text.length<4)
            {
                Toast.makeText(context, "invalid", Toast.LENGTH_LONG).show()
            }
            else
            {
                if (view!!.findViewById<EditText>(R.id.password).text.length<4)
                {
                    Toast.makeText(context, "invalid", Toast.LENGTH_LONG).show()
                }
                else
                {

                    val json = JSONObject()
                    json.put("username", view!!.findViewById<EditText>(R.id.username).text)
                    json.put("password",view!!.findViewById<EditText>(R.id.password).text)

                    "https://recruitment.fisdev.com/api/login/"
                        .httpPost()
                        .header("Content-Type" to "application/json")
                        .body(json.toString())
                        //.responseString()
                        .responseObject(login.Deserializer())
                        { request, response, result ->

                            when (result) {
                                is com.github.kittinunf.result.Result.Failure  -> {

                                    //val ex = result.getException()
                                    Toast.makeText(context, "failed",Toast.LENGTH_LONG).show()

                                }
                                is com.github.kittinunf.result.Result.Success -> {

                                    val pref = PreferenceManager.getDefaultSharedPreferences(context)
                                    val editor = pref.edit()
                                    editor
                                        .putString("Token", result.value.token)
                                        .apply()

                                    AppSession.token = result.value.token
                                    val transaction = fragmentManager!!.beginTransaction()
                                    var fr = HomeFragment()
                                    transaction.replace(R.id.univarsal, fr)
                                    //transaction.addToBackStack(null)
                                    transaction.commit()

                                }


                            }
                        }


                }
            }
        }
    }

}