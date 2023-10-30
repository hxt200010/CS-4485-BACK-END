package com.example.back_end_discard

import com.example.back_end_discard.backend.Game
import com.example.back_end_discard.backend.Player

fun main() {
    val player1 = Player("Player 1")
    val player2 = Player("Player 2")
    val game = Game(listOf(player1, player2))

    while (true) {
        val currentPlayer = game.players[game.currentPlayerIndex]
        println("${currentPlayer.name}'s Turn")
        println("Current Card: ${game.currentCard.suit} ${game.currentCard.value}")
        println("Your Hand: ${currentPlayer.hand.joinToString(", ") { "${it.suit} ${it.value}" }}")
        println("Choose a card to play (by index) or type 'draw' to draw a card:")

        val input = readLine()

        if (input == "draw") {
            val drawnCard = game.deck.draw()
            currentPlayer.drawCard(drawnCard)
            println("You drew a ${drawnCard.suit} ${drawnCard.value}")
        } else {
            val chosenIndex = input?.toIntOrNull()
            if (chosenIndex != null && chosenIndex in currentPlayer.hand.indices) {
                val chosenCard = currentPlayer.hand[chosenIndex]
                if (game.playCard(currentPlayer, chosenCard)) {
                    println("Played ${chosenCard.suit} ${chosenCard.value}")
                } else {
                    println("Invalid move!")
                }
            } else {
                println("Invalid choice!")
            }
        }

        // Check for a winner (player with no cards left)
        if (currentPlayer.hand.isEmpty()) {
            println("${currentPlayer.name} wins!")
            break
        }

        println("\n--- Next Turn ---\n")
    }
}

