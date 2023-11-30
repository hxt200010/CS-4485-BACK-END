package com.example.back_end_discard.backend

enum class Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum class Value(val points: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(1)
}


data class Card(val suit: Suit, val value: Value)