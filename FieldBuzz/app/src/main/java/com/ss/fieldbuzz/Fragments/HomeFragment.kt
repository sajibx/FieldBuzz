package com.ss.fieldbuzz.Fragments

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.DataPart
import com.github.kittinunf.fuel.core.FileDataPart
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.requests.upload
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.greentoad.turtlebody.docpicker.DocPicker
import com.greentoad.turtlebody.docpicker.core.DocPickerConfig
import com.ss.fieldbuzz.Data.AppSession
import com.ss.fieldbuzz.Data.AppSession.uri
import com.ss.fieldbuzz.Data.Data_Realm
import com.ss.fieldbuzz.Data.Posts
import com.ss.fieldbuzz.Data.Resume
import com.ss.fieldbuzz.MainActivity
import com.ss.fieldbuzz.R
import io.realm.Realm
import kotlinx.coroutines.launch
import org.jetbrains.anko.find
import org.json.JSONObject
import java.io.File
import java.util.*
import java.util.regex.Pattern


class HomeFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        view!!.findViewById<Button>(R.id.file).setOnClickListener()
        {

//            val intent = Intent()
//                    .setType("*/*")
//                    .setAction(Intent.ACTION_GET_CONTENT)
//
//            startActivityForResult(Intent.createChooser(intent, "Select a file"), 1212)


            val docs = arrayListOf<String>(DocPicker.DocTypes.PDF)

            val pickerConfig = DocPickerConfig()
                    .setShowConfirmationDialog(false)
                    .setAllowMultiSelection(false)
                    .setExtArgs(docs)

            DocPicker.with(activity!!)
                    .setConfig(pickerConfig)
                    .onResult()
                    .subscribe({
                        it.forEach {
                            AppSession.uri = it
                            AppSession.url = it.path!!
                            AppSession.file = 1
                        }
                    }, {
                        println("error: ${it.printStackTrace()}")
                    })

            //val file: File = File(uri!!.getPath())


        }

    }

    fun init()
    {
        val name = view!!.findViewById<EditText>(R.id.name)
        val email = view!!.findViewById<EditText>(R.id.email)
        val phone = view!!.findViewById<EditText>(R.id.phone)
        val address = view!!.findViewById<EditText>(R.id.address)
        val name_university = view!!.findViewById<EditText>(R.id.name_university)
        val graduation_year = view!!.findViewById<EditText>(R.id.graduation_year)
        val cgpa = view!!.findViewById<EditText>(R.id.cgpa)
        val ex_work = view!!.findViewById<EditText>(R.id.ex_work)
        val place_work = view!!.findViewById<EditText>(R.id.place_work)
        val applying_in = view!!.findViewById<EditText>(R.id.applying_in)
        val exp_salery = view!!.findViewById<EditText>(R.id.exp_salery)
        val field_reference = view!!.findViewById<EditText>(R.id.field_reference)
        val github = view!!.findViewById<EditText>(R.id.github)
        val files = view!!.findViewById<Button>(R.id.file)

        view!!.findViewById<TextView>(R.id.logout).setOnClickListener()
        {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref.edit()
            editor
                .putString("Token", "Null")
                .apply()

            editor.putString("entry", "Null").apply()

            activity!!.finish()
            context!!.startActivity(Intent(context, MainActivity::class.java))
        }



        view!!.findViewById<TextView>(R.id.submit).setOnClickListener()
        {
            //submit()

            val emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

            try {
                if (name.text.length in 5..255)
                {
                    if (email.text.length in 10..255 && emailPattern.matcher(email.text).find())
                    {
                        if (phone.text.toString().length in 11..14)
                        {
                            if (address.text.toString().length in 4..512)
                            {
                                if (name_university.text.toString().length in 4..256)
                                {
                                    if (graduation_year.text.toString().toInt() in 2015..2020)
                                    {
                                        if (cgpa.text.toString().toDouble() in 2.0..4.0)
                                        {
                                            if (ex_work.text.toString().toInt() in 0..100)
                                            {
                                                if (place_work.text.toString().length in 4..256)
                                                {
                                                    if (applying_in.text.toString() == "Mobile" || applying_in.text.toString() == "Backend")
                                                    {
                                                        if (exp_salery.text.toString().toInt() in 15000..60000)
                                                        {
                                                            if (field_reference.text.toString().length in 4..256)
                                                            {
                                                                if (github.text.toString().length in 10..512 && android.util.Patterns.WEB_URL.matcher(github.text.toString()).matches())
                                                                {
                                                                    if (AppSession.file==1)
                                                                    {
                                                                        submit()
                                                                    }
                                                                    else
                                                                    {
                                                                        Toast.makeText(context, "Enter select a pdf", Toast.LENGTH_LONG).show()
                                                                    }
                                                                }
                                                                else {
                                                                    Toast.makeText(context, "Enter valid project source", Toast.LENGTH_LONG).show()}
                                                            } else {
                                                                Toast.makeText(context, "Enter valid reference", Toast.LENGTH_LONG).show()
                                                            }
                                                        } else {
                                                            Toast.makeText(context, "Enter valid salary", Toast.LENGTH_LONG).show()
                                                        }
                                                    } else {
                                                        Toast.makeText(context, "Enter valid applying for Mobile or Backend", Toast.LENGTH_LONG).show()
                                                    }
                                                } else {
                                                    Toast.makeText(context, "Enter valid work place", Toast.LENGTH_LONG).show()
                                                }
                                            } else {
                                                Toast.makeText(context, "Enter valid work experience", Toast.LENGTH_LONG).show()
                                            }
                                        } else {
                                            Toast.makeText(context, "Enter valid cgpa", Toast.LENGTH_LONG).show()
                                        }
                                    } else {
                                        Toast.makeText(context, "Enter valid graduation year", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                    Toast.makeText(context, "Enter valid university", Toast.LENGTH_LONG).show()
                                }
                            } else {
                                Toast.makeText(context, "Enter valid address", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(context, "Enter valid phone", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, "Enter valid email", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Enter valid name", Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception)
            {
                //Log.e("login", e.toString())
            }
        }


    }







    fun submit()
    {



        val name = view!!.findViewById<EditText>(R.id.name).text.toString()
        val email = view!!.findViewById<EditText>(R.id.email).text.toString()
        val phone = view!!.findViewById<EditText>(R.id.phone).text.toString()
        val address = view!!.findViewById<EditText>(R.id.address).text.toString()
        val name_university = view!!.findViewById<EditText>(R.id.name_university).text.toString()
        val graduation_year = view!!.findViewById<EditText>(R.id.graduation_year).text.toString()
        val cgpa = view!!.findViewById<EditText>(R.id.cgpa).text.toString()
        val ex_work = view!!.findViewById<EditText>(R.id.ex_work).text.toString()
        val place_work = view!!.findViewById<EditText>(R.id.place_work).text.toString()
        val applying_in = view!!.findViewById<EditText>(R.id.applying_in).text.toString()
        val exp_salery = view!!.findViewById<EditText>(R.id.exp_salery).text.toString()
        val field_reference = view!!.findViewById<EditText>(R.id.field_reference).text.toString()
        var github = view!!.findViewById<EditText>(R.id.github).text.toString()
        val files = view!!.findViewById<Button>(R.id.file).text.toString()

        if (!github.contains("https://"))
        {
            github = "https://"+github
        }

        val tsLong = System.currentTimeMillis() / 1000
        val tss = tsLong.toString()

        var time1 = ""
        var time2 = ""


        val realm = Realm.getDefaultInstance()

        try {
            realm.executeTransaction {
                var mail = realm.where(Data_Realm::class.java).equalTo("email", email).findFirstAsync()

                time1 = tss
                time2 = mail.createTime.toString()
                AppSession.state = "Updated"

            }
        }catch (e:Exception)
        {
            time1 = tss
            time2 = tss

            realm.beginTransaction()
            val data:Data_Realm = Data_Realm(email, tss.toInt(), tss.toInt())
            realm.copyToRealm(data)
            realm.commitTransaction()
            AppSession.state = "Submitted"

        }






        try{
            var mail = view!!.findViewById<EditText>(R.id.email).text.toString()
            var uuid = UUID.nameUUIDFromBytes(mail.toByteArray())
            AppSession.tsync_id = uuid.toString()
            }catch (e: Exception){
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }



            val ss = "{\n" +
                "    \"tsync_id\": \"${AppSession.tsync_id}\",\n" +
                "    \"name\": \"${name}\",\n" +
                "    \"email\": \"${email}\",\n" +
                "    \"phone\": \"+${phone}\",\n" +
                "    \"full_address\": \"${address}\",\n" +
                "    \"name_of_university\": \"${name_university}\",\n" +
                "    \"graduation_year\": ${graduation_year},\n" +
                "    \"cgpa\": ${cgpa},\n" +
                "    \"experience_in_months\": ${ex_work},\n" +
                "    \"current_work_place_name\": \"${place_work}\",\n" +
                "    \"applying_in\": \"${applying_in}\",\n" +
                "    \"expected_salary\": ${exp_salery},\n" +
                "    \"field_buzz_reference\": \"${field_reference}\",\n" +
                "    \"github_project_url\": \"${github.toString()}\",\n" +
                "    \"cv_file\": {\n" +
                "        \"tsync_id\": \"${AppSession.tsync_id}\"\n" +
                "    },\n" +
                "    \"on_spot_update_time\": ${time1},\n" +
                "    \"on_spot_creation_time\": ${time2}\n" +
                "}"








        val ts = "Token"+"  "+AppSession.token



        launch {

            "https://recruitment.fisdev.com/api/v0/recruiting-entities/"
                .httpPost()
                .header("Content-Type" to "application/json")
                .header(Headers.AUTHORIZATION, ts)
                //.header("Authorization" to ts.toString())
                .body(ss)
                //.responseString()
                .responseObject(Posts.Deserializer())
                { request, response, result ->

                    when (result) {
                        is com.github.kittinunf.result.Result.Failure -> {

                            val ex = result.getException()
                            updateUI {
                                Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show()
                            }

                        }
                        is com.github.kittinunf.result.Result.Success -> {


                            AppSession.token_id = result.value.cv_file.id.toString()
                            resume()

                        }


                    }
                }

        }

    }

    fun resume()
    {


            val ts = "Token"+"  "+AppSession.token



            launch {



               try {



                   val file = File(AppSession.url)



                   Fuel.put("https://recruitment.fisdev.com/api/file-object/${AppSession.token_id}/")
                       .header(Headers.CONTENT_TYPE, "application/json")
                       .header(Headers.AUTHORIZATION, ts)
                       .body(file)
                       .responseObject(Resume.Deserializer()) { request, response, result ->
                           when(result){
                               is com.github.kittinunf.result.Result.Failure -> {
                                   val ex = result.getException()
                                       updateUI {
                                           Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show()
                                       }
                               }
                               is com.github.kittinunf.result.Result.Success -> {
                                   updateUI {
                                           Toast.makeText(context, AppSession.state, Toast.LENGTH_LONG).show()
                                       AppSession.file = 0
                                       }
                               }
                           }
                       }


               }catch (e:Exception)
               {
                   Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
               }


            }

        }
    }