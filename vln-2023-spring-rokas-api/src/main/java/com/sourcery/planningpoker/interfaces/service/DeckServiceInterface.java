package com.sourcery.planningpoker.interfaces.service;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.model.Deck;
import java.util.List;

public interface DeckServiceInterface {
  List<Deck> getAllDecks();

  Deck getDeckById(Integer deckId);

  Deck addDeck(Deck deck) throws ExceptionBuilder;

  void updateDeck(Integer deckId, String deckName) throws ExceptionBuilder;

  Deck deleteDeck(Integer deckId);
}
