package com.example.back_end_discard.backend

class Player(val name: String) {
    val hand: MutableList<Card> = mutableListOf()

    fun playCard(card: Card) {
        hand.remove(card)
    }

    fun drawCard(card: Card) {
        hand.add(card)
    }

    fun hasPlayableCard(currentCard: Card): Boolean {
        return hand.any { it.suit == currentCard.suit || it.value == currentCard.value }
    }
}
