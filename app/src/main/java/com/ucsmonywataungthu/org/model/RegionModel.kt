package com.ucsmonywataungthu.org.model

data class RegionModel (val id:Int,
                        val region_name:String)
{
    override fun toString(): String {
        return region_name
    }

}