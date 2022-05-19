package com.valentine.challengech6.data.local.repository

import android.accounts.Account
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.valentine.challengech6.data.local.user.User
import com.valentine.challengech6.data.local.user.UserDao
import com.valentine.challengech6.data.local.user.UserDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.internal.userAgent
import java.lang.reflect.Constructor

class UserRepository constructor(private val userDao: UserDao, private val context:Context){
    companion object{
        private var instance: UserRepository?= null
        fun getInstance (context: Context): UserRepository?{
            return instance?: synchronized(UserRepository::class.java){
                if (instance==null){
                    val db = UserDatabase.getInstance(context)
                    instance = UserRepository(db!!.UserDao(), context)
                }
                return instance
            }
        }
        private const val Datastore_name = "user"
        private val KEY_USERNAME = stringPreferencesKey("key_username")
        private val KEY_EMAIL = stringPreferencesKey("key_email")
        private val Context.prefDataStore by preferencesDataStore(name = Datastore_name)
    }
    suspend fun userRegister (user: User): Long{
        return userDao.Insertuser(user)

    }
    suspend fun authLogin(email:String, password:String):List<User>{
        return userDao.loginUser(email, password)
    }
    suspend fun updateProfile (user: User):Int{
        return userDao.updateUser(user = user)
    }


suspend fun getAllData(username: String?):User{
    return userDao.getUsername(username)
}


suspend fun setUsername(username :String){
    context.prefDataStore.edit {
        it[KEY_USERNAME]=username
    }
}

suspend fun setEmail(email: String){
    context.prefDataStore.edit {
        it[KEY_EMAIL] = email
    }
}
fun getUsernameValue():Flow<String>{
    return context.prefDataStore.data.map {
        it[KEY_EMAIL]?:"default"
    }
/*    suspend fun getPhoto(username:String?):User{
        return userDao.getImagePath(username)
    }*/
}

fun getEmailValue(): Flow<String> {
    return context.prefDataStore.data.map {
        it[KEY_EMAIL]?: "default"
    }
}


suspend fun clearDataStore(){
    context.prefDataStore.edit {
        it.clear()
    }
}


}