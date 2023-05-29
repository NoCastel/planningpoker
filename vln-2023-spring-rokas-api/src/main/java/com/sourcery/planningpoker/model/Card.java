package com.sourcery.planningpoker.model;

import jakarta.validation.constraints.NotNull;

public class Card {
  private int cardId;
  @NotNull
  private Integer deckId;
  @NotNull
  private Integer cardValue;

  public Card() {
  }

  public Card(int cardId, Integer deckId, Integer cardValue) {
    this.cardId = cardId;
    this.deckId = deckId;
    this.cardValue = cardValue;
  }

  public int getCardId() {
    return cardId;
  }

  public void setCardId(int cardId) {
    this.cardId = cardId;
  }

  public Integer getDeckId() {
    return deckId;
  }

  public void setDeck(Integer deckId) {
    this.deckId = deckId;
  }

  public Integer getCardValue() {
    return cardValue;
  }

  public void setCardValue(Integer cardValue) {
    this.cardValue = cardValue;
  }

  @Override
  public String toString() {
    return "Card{" +
        "cardId=" + cardId +
        ", deckId=" + deckId +
        ", cardValue=" + cardValue +
        '}';
  }
}