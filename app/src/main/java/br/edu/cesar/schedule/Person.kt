package br.edu.cesar.schedule

import java.io.Serializable

data class Person (var name: String, var age: Int) : Serializable {
    override fun toString(): String {
        return "$name - $age"
    }
}