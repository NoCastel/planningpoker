package com.sourcery.planningpoker.controller;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.handlers.response.ResponseMessageHandler;
import com.sourcery.planningpoker.model.Deck;
import com.sourcery.planningpoker.service.DeckService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Validated
@RequestMapping("api/v1/decks")
public class DeckController {
  @Autowired
  private DeckService deckService;

  @Autowired
  private ResponseMessageHandler responseMessageHandler;
  Map<String, String> response;
  @GetMapping("/all-decks")
  public ResponseEntity<List<Deck>> getAllDecks(){
    List<Deck> decks = deckService.getAllDecks();
    return decks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(decks);
  }

  @GetMapping("/{deckId}")
  public ResponseEntity<Deck> getDeckById(@PathVariable @Min(1) Integer deckId) {
    Deck deck = deckService.getDeckById(deckId);
    return deck != null ? ResponseEntity.ok(deck) : ResponseEntity.noContent().build();
  }

  @PostMapping("/add")
  public ResponseEntity<Object> addDeck(@RequestBody Deck deck) {
    try{
      Deck newDeck = deckService.addDeck(deck);
      return ResponseEntity.status(HttpStatus.CREATED).body(newDeck);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  @PutMapping(path = "/update/{id}")
  public ResponseEntity<Map<String, String>> updateDeck(@PathVariable("id") @Min(1) Integer deckId,
                                                        @RequestParam(required = false) String deckName) {
    try {
      deckService.updateDeck(deckId, deckName);
      response = responseMessageHandler.generateResponseMessageJson("Deck updated successfully.");
      return ResponseEntity.ok(response);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  @DeleteMapping(path = "/delete/{deckId}")
  public ResponseEntity<Object> deleteDeck(@PathVariable("deckId") Integer deckId) {
    Deck deck = deckService.deleteDeck(deckId);
    if (deck != null) {
      response = responseMessageHandler.generateResponseMessageJson("Deck was deleted successfully.");
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
