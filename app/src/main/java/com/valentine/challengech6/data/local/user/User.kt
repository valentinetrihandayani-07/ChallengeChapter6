package com.valentine.challengech6.data.local.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int?=null,
    @ColumnInfo(name = "username") var username: String?=null,
    @ColumnInfo(name = "fullname") var fullname: String?=null,
    @ColumnInfo(name = "email") var email: String?=null,
    @ColumnInfo(name = "password") var password: String?=null,
    @ColumnInfo(name = "imagepath") var imagepath: String?=null
) : Parcelable