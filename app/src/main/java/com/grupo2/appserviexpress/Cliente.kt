package com.grupo2.appserviexpress

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Cliente(
    @get:Exclude var id:String="",
    var clienteName:String="",
    var servicio:String="",
    var telefono:String="",
    var email:String="",
    var valor : String=""



)
