package com.sourcery.planningpoker.controller;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.handlers.response.ResponseMessageHandler;
import com.sourcery.planningpoker.model.Card;
import com.sourcery.planningpoker.service.CardService;
import jakarta.validation.Valid;
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
@RequestMapping("api/v1/cards")
public class CardController {
  @Autowired
  private CardService cardService;

  @Autowired
  private ResponseMessageHandler responseMessageHandler;
  Map<String, String> response;
  @GetMapping("/all-cards")
  public ResponseEntity<List<Card>> getAllCards() {
    List<Card> cards = cardService.getAllCards();
    return cards.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cards);
  }
  @GetMapping("/{cardId}")
  public ResponseEntity<Card> getCardById(@PathVariable @Min(1) int cardId ) {
    Card card = cardService.getCardById(cardId);
    return card != null ? ResponseEntity.ok(card) : ResponseEntity.noContent().build();
  }
  @GetMapping("/deck/{deckId}")
  public ResponseEntity<List<Card>> getCardsByDeck(@PathVariable @Min(1) int deckId) {
    List<Card> cards = cardService.getCardsByDeck(deckId);
    return cards.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cards);
  }

  @GetMapping("/deck-value")
  public ResponseEntity<Card> getCardByDeckAndValue(@RequestParam(required = false) Integer deckId,
                                                    @RequestParam(required = false) Integer cardValue) {
    Card card = cardService.getCardByDeckAndValue(deckId, cardValue);
    return card != null ? ResponseEntity.ok(card) : ResponseEntity.noContent().build();
  }
  @PostMapping("/add")
  public ResponseEntity<Object> addCard(@Valid @RequestBody Card card) {
    try{
      Card newCard = cardService.addCard(card);
      return ResponseEntity.status(HttpStatus.CREATED).body(newCard);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  @PutMapping(path = "/update/{id}")
  public ResponseEntity<Map<String, String>> updateCard(@PathVariable("id") int id,
                                      @RequestParam(required = false) Integer deckId,
                                      @RequestParam(required = false) Integer cardValue) {
    try {
      cardService.updateCard(id, deckId, cardValue);
      response = responseMessageHandler.generateResponseMessageJson("Card updated successfully.");
      return ResponseEntity.ok(response);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  @DeleteMapping(path = "/delete/{cardId}")
  public ResponseEntity<Object> deleteCard(@PathVariable("cardId") @Min(1) int cardId) {
    Card card = cardService.deleteCard(cardId);
    if (card != null) {
      response = responseMessageHandler.generateResponseMessageJson("Card was deleted successfully.");
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
