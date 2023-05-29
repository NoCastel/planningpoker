package com.sourcery.planningpoker.model;

import jakarta.validation.constraints.NotEmpty;

public class Deck {
  private Integer deckId;
  @NotEmpty
  private String deckName;

  public Deck(int deckId, String deckName) {
    this.deckId = deckId;
    this.deckName = deckName;
  }

  public Deck() {
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

  @Override
  public String toString() {
    return "Deck{" +
        "deckId=" + deckId +
        ", deckName='" + deckName + '\'' +
        '}';
  }
}