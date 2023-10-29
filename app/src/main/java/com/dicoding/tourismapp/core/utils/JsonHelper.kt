package com.dicoding.tourismapp.core.utils

import android.content.Context
import com.dicoding.tourismapp.R
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.tourism).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<TourismResponse> {
        val list = ArrayList<TourismResponse>()
        val responseObject = JSONObject(parsingFileToString().toString())
        val listArray = responseObject.getJSONArray("places")
        for (i in 0 until listArray.length()) {
            val course = listArray.getJSONObject(i)

            val id = course.getString("id")
            val name = course.getString("title")
            val description = course.getString("description")
            val image = course.getString("image")

            val courseResponse = TourismResponse(
                id = id,
                title = name,
                description = description,
                image = image
            )
            list.add(courseResponse)
        }
        return list
    }
}

