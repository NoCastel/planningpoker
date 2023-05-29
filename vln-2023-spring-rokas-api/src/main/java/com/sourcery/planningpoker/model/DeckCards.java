package com.sourcery.planningpoker.model;

import java.util.List;

public class DeckCards {
  private Integer deckId;

  private String deckName;

  private List<Integer> cardValues;

  public DeckCards(){

  }

  public DeckCards(Integer deckId, String deckName, List<Integer> cardValues) {
    this.deckId = deckId;
    this.deckName = deckName;
    this.cardValues = cardValues;
  }

  public Integer getDeckId() {
    return deckId;
  }

  public void setDeckId(Integer deckId) {
    this.deckId = deckId;
  }

  public String getDeckName() {
    return deckName;
  }

  public void setDeckName(String deckName) {
    this.deckName = deckName;
  }

  public List<Integer> getCardValues() {
    return cardValues;
  }

  public void setCardValues(List<Integer> cardValues) {
    this.cardValues = cardValues;
  }

  @Override
  public String toString() {
    return "DeckCards{" +
        "deckId=" + deckId +
        ", deckName='" + deckName + '\'' +
        ", cardValue=" + cardValues +
        '}';
  }
}
