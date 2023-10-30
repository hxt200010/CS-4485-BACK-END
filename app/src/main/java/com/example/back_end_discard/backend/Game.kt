package com.example.back_end_discard.backend

// Game.kt
class Game(val players: List<Player>) {
    var deck = Deck()
    var discardPile: MutableList<Card> = mutableListOf()
    var currentCard: Card = deck.draw()
    var currentPlayerIndex = 0
    var playDirection = 1 // 1 for forward, -1 for reverse

    fun nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + playDirection + players.size) % players.size
    }

    fun playCard(player: Player, card: Card): Boolean {
        if (isValidMove(card)) {
            player.playCard(card)
            discardPile.add(card)
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
        when (card.value) {
            Value.JACK -> nextTurn() // Skip
            Value.QUEEN -> playDirection *= -1 // Reverse
            Value.KING -> { // Draw Two
                nextTurn()
                val nextPlayer = players[currentPlayerIndex]
                nextPlayer.drawCard(deck.draw())
                nextPlayer.drawCard(deck.draw())
            }
            else -> {}
        }
    }
}
