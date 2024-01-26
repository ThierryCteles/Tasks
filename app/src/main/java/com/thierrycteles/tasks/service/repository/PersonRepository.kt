package com.thierrycteles.tasks.service.repository

import android.content.Context
import com.thierrycteles.tasks.R
import com.thierrycteles.tasks.service.listener.APIListener
import com.thierrycteles.tasks.service.model.PersonModel
import com.thierrycteles.tasks.service.repository.remote.PersonService
import com.thierrycteles.tasks.service.repository.remote.RetrofitClient

class PersonRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(PersonService::class.java)

    fun login(email: String, password: String, listener: APIListener<PersonModel>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        executeCall(remote.login(email, password), listener)
    }

    fun create(name: String, email: String, password: String, listener: APIListener<PersonModel>) {
        if (!isConnectionAvailable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        executeCall(remote.create(name, email, password), listener)
    }

}