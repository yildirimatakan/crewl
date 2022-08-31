package com.example.crewl.ui.bottomSheet.countryCode.providers

import com.example.crewl.core.BaseApplication
import com.example.crewl.data.models.Country

class AssetCountryProvider(private val fileName: String) : CountryListProvider {
    private val applicationContext = BaseApplication.getContext()

    override fun getCountries() = applicationContext.assets
        .open(fileName)
        .bufferedReader()
        .useLines {
            it.map { raw ->
                val (phoneCode, isoCode, countryName) = raw.split(";")
                Country(
                    isoCode = isoCode,
                    phoneCode = phoneCode,
                    countryName = countryName
                )
            }.toList()
        }
}