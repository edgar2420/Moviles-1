package com.example.parcialgooglekeep

class Note (
    var id: Int,
    val title: String,
    val description: String
    ) : java.io.Serializable {
    override fun toString(): String {
        return "$title"
        }
    }
