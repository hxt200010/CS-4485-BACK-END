package com.example.back_end_discard.backend

// Player.kt
class Player(val name: String) {
    var hand: MutableList<Card> = mutableListOf()

    fun playCard(card: Card): Card {
        hand.remove(card)
        return card
    }

    fun addCardToHand(card: Card) {
        hand.add(card)
    }

    fun drawCard(deck: Deck) {
        hand.add(deck.draw())
    }
}
