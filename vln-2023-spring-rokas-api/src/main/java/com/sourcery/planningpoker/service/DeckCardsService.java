package com.sourcery.planningpoker.service;

import com.sourcery.planningpoker.interfaces.service.DeckCardsServiceInterface;
import com.sourcery.planningpoker.mapper.DeckCardsMapper;
import com.sourcery.planningpoker.model.DeckCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeckCardsService implements DeckCardsServiceInterface {
  @Autowired
  private DeckCardsMapper deckCardsMapper;
  @Override
  public List<DeckCards> getAllDeckCards() {
    return deckCardsMapper.getAllDeckCards();
  }

  @Override
  public DeckCards getDeckCardsById(Integer deckId) {
    return deckCardsMapper.getDeckCardsById(deckId);
  }
}