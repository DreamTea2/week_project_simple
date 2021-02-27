package com.example.myapplication.kotlin

data class Cellphone(val brand: String, val price: Double) {

    constructor() {
    }

    override fun equals(other: Any?): Boolean {
        when (other) {
            is Cellphone -> return other.brand.equals(brand) && other.price == price
        }
        return false;
    }

    override fun toString(): String {
        return "Cellphone(brand=$brand,price=$price)"
    }
}