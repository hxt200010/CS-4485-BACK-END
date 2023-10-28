package com.example.back_end_discard.backend

// Game.kt
class Game(val players: List<Player>) {
    var deck = Deck()
    var currentCard: Card = deck.draw()
    var currentPlayerIndex = 0

    fun nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
    }

    fun playCard(player: Player, card: Card): Boolean {
        if (isValidMove(card)) {
            player.playCard(card)
            currentCard = card
            handleSpecialCard(card)
            nextTurn()
            return true
        }
        return false
    }

    private fun isValidMove(card: Card): Boolean {
        return card.suit == currentCard.suit || card.value == currentCard.value
    }

    private fun handleSpecialCard(card: Card) {
        // Implement special actions like reverse, skip, draw two, draw four
    }
}