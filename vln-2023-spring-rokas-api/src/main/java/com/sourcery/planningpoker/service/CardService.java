package com.sourcery.planningpoker.service;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.interfaces.service.CardServiceInterface;
import com.sourcery.planningpoker.mapper.CardMapper;
import com.sourcery.planningpoker.model.Card;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class CardService implements CardServiceInterface {
  @Autowired
  private CardMapper cardMapper;

  @Override
  public List<Card> getAllCards() {
    return cardMapper.getAllCards();
  }

  @Override
  public Card getCardById(int cardId) {
    return cardMapper.getCardById(cardId);
  }

  @Override
  public List<Card> getCardsByDeck(Integer deckId) {
    return cardMapper.getCardsByDeck(deckId);
  }

  @Override
  public Card getCardByDeckAndValue(Integer deckId, Integer cardValue) {
    return cardMapper.getCardByDeckAndValue(deckId, cardValue);
  }

  @Override
  public Card addCard(Card card) throws ExceptionBuilder{
    Card cardByDeckAndValue = cardMapper.getCardByDeckAndValue(card.getDeckId(), card.getCardValue());

    if(cardByDeckAndValue != null){
      throw new ExceptionBuilder("Card already exists.", HttpStatus.CONFLICT);
    }
    cardMapper.addCard(card);
    return card;
  }
  @Transactional
  public void updateCard(int cardId, Integer deckId, Integer cardValue) throws ExceptionBuilder {
    Card card = cardMapper.getCardById(cardId);
    if (card == null) {
      throw new ExceptionBuilder("Card not found.", HttpStatus.NOT_FOUND);
    }

    if (deckId != null
        && Objects.equals(card.getDeckId(), deckId)) {
      throw new ExceptionBuilder("New deck must differ from the old one.", HttpStatus.CONFLICT);
    } else if (deckId != null) {
      card.setDeck(deckId);
    }

    if (cardValue != null
        && Objects.equals(card.getCardValue(), cardValue)) {
      throw new ExceptionBuilder("New card value must differ from the old one.", HttpStatus.CONFLICT);
    } else if (cardValue != null) {
      {
        card.setCardValue(cardValue);
      }

      cardMapper.updateCard(card);
    }
  }

  @Override
  public Card deleteCard(int cardId) {
    Card card = cardMapper.getCardById(cardId);

    if(card == null){
      return null;
    }
    cardMapper.deleteCard(cardId);
    return card;
  }
}