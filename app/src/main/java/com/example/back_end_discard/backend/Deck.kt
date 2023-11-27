package com.example.back_end_discard.backend

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

    fun draw(): Card {
        if (cards.isEmpty()) {
            // Throw an exception, return a special value, or reshuffle the discard pile into the deck
            throw IllegalStateException("Cannot draw from an empty deck")
        }
        return cards.removeAt(cards.size - 1)
    }
}

