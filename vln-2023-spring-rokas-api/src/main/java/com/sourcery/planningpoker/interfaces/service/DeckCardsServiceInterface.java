package com.sourcery.planningpoker.interfaces.service;

import com.sourcery.planningpoker.model.DeckCards;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DeckCardsServiceInterface {
  List<DeckCards> getAllDeckCards();

  DeckCards getDeckCardsById(Integer deckId);
}
