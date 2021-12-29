package com.example.task1.roomdb

import androidx.room.TypeConverter
import com.example.task1.model.Address
import com.example.task1.model.Company
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class Convertors {

    @TypeConverter
    fun fromAddress(countryLang: Address?): String? {
        val type = object : TypeToken<Address>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toAddress(countryLangString: String?): Address? {
        val type = object : TypeToken<Address>() {}.type
        return Gson().fromJson<Address>(countryLangString, type)
    }

    @TypeConverter
    fun fromCompany(countryLang: Company?): String? {
        val type = object : TypeToken<Company>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toCompany(countryLangString: String?): Company? {
        val type = object : TypeToken<Company>() {}.type
        return Gson().fromJson<Company>(countryLangString, type)
    }

}