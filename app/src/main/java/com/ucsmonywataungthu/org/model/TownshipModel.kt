package com.ucsmonywataungthu.org.model

data class TownshipModel(val id:Int,
                         val city_name:String)

{
    override fun toString(): String {
        return city_name
    }
}