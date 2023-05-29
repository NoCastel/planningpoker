package com.sourcery.planningpoker.interfaces.service;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.model.Card;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CardServiceInterface {

  List<Card> getAllCards();

  Card getCardById(int cardId);

  List<Card> getCardsByDeck(Integer deckId);

  Card getCardByDeckAndValue(Integer deckId, Integer cardValue);

  Card addCard(Card card) throws ExceptionBuilder;

  void updateCard(int cardId, Integer deckId, Integer cardValue) throws ExceptionBuilder;

  Card deleteCard(int cardId);

}
