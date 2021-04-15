  package com.example.sharedpref

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*

  class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val name = etName.text.toString()
        val city = etCity.text.toString()

        retrieveData()

        save.setOnClickListener {
            saveData()
            retrieveData()

            etName.text = null
            etCity.text = null
        }
    }

      private fun retrieveData() {
          val sharedPref =  getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

          val name = sharedPref.getString("name", "")
          val city = sharedPref.getString("city", "")

          tvName.text = "name: $name"
          tvCity.text = "city: $city"
      }

      private fun saveData() {
          val name = etName.text.toString().trim()
          val city = etCity.text.toString().trim()

          when {
              TextUtils.isEmpty(name) -> {
                  etName.error = "please enter your name"
              }

              TextUtils.isEmpty(city) -> {
                  etCity.error = "please enter your city"
              }
              else -> {
                  val sharedPref =  getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

                  var editor = sharedPref.edit()


                  editor.putString("name", name)
                  editor.putString("city", city)

                  editor.apply()
              }
          }
      }
  }