package com.example.adainfoodorderingapp.model

import android.os.Parcel
import android.os.Parcelable

class orderDetails():Parcelable {
    var userUid:String?=null
    var userName:String?=null
    var Address:String?=null
    var totalPrice:String?=null
    var phoneNumber:String?=null
    var itemPushKey:String?=null
    var foodNames:MutableList<String>?=null
    var foodImage:MutableList<String>?=null
    var foodPrice:MutableList<String>?=null
    var foodQuantity:MutableList<Int>?=null
    var orderAccepted:Boolean = false
    var orderRecived:Boolean = false
    var currentTime : Long = 0

    constructor(parcel: Parcel) : this() {
        userUid = parcel.readString()
        userName = parcel.readString()
        Address = parcel.readString()
        totalPrice = parcel.readString()
        phoneNumber = parcel.readString()
        itemPushKey = parcel.readString()
        orderAccepted = parcel.readByte() != 0.toByte()
        orderRecived = parcel.readByte() != 0.toByte()
        currentTime = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userUid)
        parcel.writeString(userName)
        parcel.writeString(Address)
        parcel.writeString(totalPrice)
        parcel.writeString(phoneNumber)
        parcel.writeString(itemPushKey)
        parcel.writeByte(if (orderAccepted) 1 else 0)
        parcel.writeByte(if (orderRecived) 1 else 0)
        parcel.writeLong(currentTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<orderDetails> {
        override fun createFromParcel(parcel: Parcel): orderDetails {
            return orderDetails(parcel)
        }

        override fun newArray(size: Int): Array<orderDetails?> {
            return arrayOfNulls(size)
        }
    }
}