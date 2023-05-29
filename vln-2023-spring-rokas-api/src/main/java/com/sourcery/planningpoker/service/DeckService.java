package com.sourcery.planningpoker.service;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.interfaces.service.DeckServiceInterface;
import com.sourcery.planningpoker.mapper.DeckMapper;
import com.sourcery.planningpoker.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class DeckService implements DeckServiceInterface {
  @Autowired
  private DeckMapper deckMapper;

  @Override
  public List<Deck> getAllDecks() {
    return deckMapper.getAllDecks();
  }

  @Override
  public Deck getDeckById(Integer deckId) {
    return deckMapper.getDeckById(deckId);
  }

  @Override
  public Deck addDeck(Deck deck) throws ExceptionBuilder{
    Deck deckById = deckMapper.getDeckById(deck.getDeckId());

    if (deckById != null
        && Objects.equals(deck.getDeckName(), deckById.getDeckName())){
        throw new ExceptionBuilder("Deck already exists.", HttpStatus.CONFLICT);
    } else if(deck.getDeckName() == null){
      throw new ExceptionBuilder("Deck name cannot be null.", HttpStatus.CONFLICT);
    }

    deckMapper.addDeck(deck);
    return deck;
  }

  @Override
  public void updateDeck(Integer deckId, String deckName) throws ExceptionBuilder{
    Deck deckById = deckMapper.getDeckById(deckId);

    if (deckById == null){
      throw new ExceptionBuilder("Deck not found.", HttpStatus.NOT_FOUND);
    }

    if (deckName != null
        && Objects.equals(deckName, deckById.getDeckName())) {
      throw new ExceptionBuilder("New deck name must differ from the old one.", HttpStatus.CONFLICT);
    } else if (deckName != null) {
      deckById.setDeckName(deckName);
    }

    deckMapper.updateDeck(deckById);
  }

  @Override
  public Deck deleteDeck(Integer deckId) {
    Deck deck = deckMapper.getDeckById(deckId);

    if(deck == null){
      return null;
    }
    deckMapper.deleteDeck(deckId);
    return deck;
  }
}