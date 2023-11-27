package com.example.back_end_discard.backend

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
            return true
        }
        return false
    }

    private fun isValidMove(card: Card): Boolean {
        return card.suit == currentCard.suit || card.value == currentCard.value
    }

    private fun handleSpecialCard(card: Card) {
        when (card.value) {
            Value.JACK -> {
                nextTurn() // Skip next player
            }
            Value.QUEEN -> {
                playDirection *= -1 // Reverse the play direction
                nextTurn() // This will move to the previous player in the new direction
            }
            Value.KING -> { // Draw Two
                nextTurn()
                players[currentPlayerIndex].drawCard(deck.draw())
                players[currentPlayerIndex].drawCard(deck.draw())
            }
            else -> { /* No special action */ }
        }
    }

    // Add this method to handle special card announcements
// Add this method to handle special card announcements
    fun handleSpecialCardAnnouncement(card: Card): Boolean {
        when (card.value) {
            Value.JACK -> {
                // When a Jack is drawn or played, skip the next player's turn
                val nextPlayer = players[(currentPlayerIndex + playDirection + players.size) % players.size]
                println("Jack drawn or played! ${nextPlayer.name} will be skipped.")
                return true
            }
            Value.QUEEN -> {
                // When a Queen is drawn or played, reverse the play direction
                playDirection *= -1
                println("Queen drawn or played! The play direction is reversed.")
                return true
            }
            Value.KING -> {
                // When a King is drawn or played, the next player must draw two cards
                val nextPlayer = players[(currentPlayerIndex + playDirection + players.size) % players.size]
                println("King drawn or played! ${nextPlayer.name} will draw two cards.")
                nextPlayer.drawCard(deck.draw())
                nextPlayer.drawCard(deck.draw())
                return true
            }
            else -> return false // No special action for other cards
        }
    }
}
