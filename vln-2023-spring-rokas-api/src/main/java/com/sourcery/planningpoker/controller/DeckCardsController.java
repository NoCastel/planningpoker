package com.sourcery.planningpoker.controller;

import com.sourcery.planningpoker.model.DeckCards;
import com.sourcery.planningpoker.service.DeckCardsService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("api/v1/deck-cards")
public class DeckCardsController {
  @Autowired
  private DeckCardsService deckCardsService;

  @GetMapping("/all-deck-cards")
  public ResponseEntity<List<DeckCards>> getAllDeckCards() {
    List<DeckCards> deckCards = deckCardsService.getAllDeckCards();
    return deckCards.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(deckCards);
  }

  @GetMapping("/{deckId}")
  public ResponseEntity<DeckCards> getDeckCardsById(@PathVariable @Min(1) Integer deckId) {
    DeckCards deckCards = deckCardsService.getDeckCardsById(deckId);
    return deckCards != null ? ResponseEntity.ok(deckCards) : ResponseEntity.noContent().build();
  }
}
