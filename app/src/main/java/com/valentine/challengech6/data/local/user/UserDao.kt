package com.valentine.challengech6.data.local.user

import androidx.room.*


@Dao
interface UserDao {
    @Query("SELECT *  From User where username = (:username) AND password =(:password)")
    fun loginUser (username:String, password:String): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun Insertuser (user : User): Long

    @Update
    fun updateUser (user: User): Int

    @Delete
    fun deleteUser(user: User):Int

    @Query("SELECT * FROM user WHERE email=:email ")
    fun getUsername(email:String? ) : User

    @Update
    fun updateUserAccount(user : User) : Int




}