package com.example.back_end_discard.backend

// Deck.kt
import java.util.Collections.shuffle

class Deck {
    var cards: MutableList<Card> = mutableListOf()

    init {
        for (suit in Suit.values()) {
            for (value in Value.values()) {
                cards.add(Card(suit, value))
            }
        }
        shuffle()
    }

    fun shuffle() {
        shuffle(cards)
    }

    fun draw(): Card = cards.removeAt(cards.size - 1)
}
