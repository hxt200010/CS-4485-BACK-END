package com.example.back_end_discard.backend

enum class Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum class Value {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

data class Card(val suit: Suit, val value: Value)