package com.example.a1erpracticocomics.models

class Comic(
    var name: String,
    var date: Int,
    var img: Array<Int>
) {
    override fun toString(): String {
        return "$name $date"
    }
}

