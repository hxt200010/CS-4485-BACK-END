package com.example.back_end_discard

import com.example.back_end_discard.backend.Card
import com.example.back_end_discard.backend.Deck
import com.example.back_end_discard.backend.Player
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameLogicTests {

    private lateinit var deck: Deck
    private lateinit var player: Player

    @Before
    fun setUp() {
        deck = Deck()
        player = Player("TestPlayer")
    }

    @Test
    fun shuffleDeck_ShouldRandomizeOrder() {
        // Store a copy of the original deck to compare after shuffling
        val originalDeck = deck.cards.toList()

        deck.shuffle()

        // Assert that the shuffled deck is not in the same order as the original
        assertNotEquals(originalDeck, deck.cards)
    }

    @Test
    fun playCard_ShouldRemoveCardFromPlayerHand() {
        // Assuming you have a method to draw a card from the deck to the player's hand
        val drawnCard = deck.draw()
        player.addCardToHand(drawnCard)

        assertTrue(player.hand.contains(drawnCard))

        player.playCard(drawnCard)

        assertFalse(player.hand.contains(drawnCard))
    }
}