package com.example.back_end_discard

import com.example.back_end_discard.backend.Game
import com.example.back_end_discard.backend.Player

fun main() {
    val player1 = Player("Player 1")
    val player2 = Player("Player 2")
    val player3 = Player("Player 3")
    val player4 = Player("Player 4")
    val game = Game(listOf(player1, player2, player3, player4))

    // Initialize players' hands with 7 cards each
    game.players.forEach { player ->
        repeat(7) {
            player.drawCard(game.deck.draw())
        }
    }

    var specialCardDrawn = false // Add this variable to track if a special card was drawn

    while (true) {
        val currentPlayer = game.players[game.currentPlayerIndex]
        val cardsLeft = game.deck.cards.size // Get the number of cards left in the deck
        println("\n${currentPlayer.name}'s Turn (Cards remaining in deck: $cardsLeft)")
        println("Current Card: ${game.currentCard.suit} ${game.currentCard.value}")
        println("Your Hand: ${currentPlayer.hand.joinToString(", ") { "${it.suit} ${it.value}" }}")
        println("Choose a card to play (by index) or type 'draw' to draw a card:")
        val input = readLine()

        val validMoveMade = if (input == "draw") {
            if (!currentPlayer.hasPlayableCard(game.currentCard)) {
                val drawnCard = game.deck.draw()
                currentPlayer.drawCard(drawnCard)
                println("You drew: ${drawnCard.suit} ${drawnCard.value}")
                // Check if the drawn card is a special card and announce the effect
                if (game.handleSpecialCardAnnouncement(drawnCard)) {
                    println("Special card drawn! Next player will be affected.")
                }
                true // End the current player's turn after drawing a card
            } else {
                println("You have a playable card. You must play it.")
                false
            }
        } else {
            val chosenIndex = input?.toIntOrNull()
            if (chosenIndex != null && chosenIndex in currentPlayer.hand.indices) {
                val chosenCard = currentPlayer.hand[chosenIndex]
                if (game.playCard(currentPlayer, chosenCard)) {
                    println("Played: ${chosenCard.suit} ${chosenCard.value}")
                    // Check if a special card is played and announce the effect
                    if (game.handleSpecialCardAnnouncement(chosenCard)) {
                        println("Special card played! Next player will be affected.")
                        specialCardDrawn = true // Set the flag to true
                    }
                    true
                } else {
                    println("Invalid move! Try again.")
                    false
                }
            } else {
                println("Invalid choice! Try again.")
                false
            }
        }

        if (validMoveMade) {
            println("--- Next Turn ---")
            game.nextTurn()
            specialCardDrawn = false // Reset the flag at the start of the next turn
        }

        if (currentPlayer.hand.isEmpty()) {
            val cardCounts = game.countCardsForPlayers()
            val winner = cardCounts.minByOrNull { it.value }?.key
            println("${currentPlayer.name} wins the game with $cardsLeft cards remaining in the deck!")
            println("Player with the least cards is ${winner?.name} with ${cardCounts[winner]} cards.")
            break
        }
    }
}
