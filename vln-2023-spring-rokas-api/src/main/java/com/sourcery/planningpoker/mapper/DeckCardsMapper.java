package com.sourcery.planningpoker.mapper;

import com.sourcery.planningpoker.handlers.IntegerListTypeHandler;
import com.sourcery.planningpoker.model.DeckCards;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface DeckCardsMapper {

  // GET
  @Results({
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "deckName", column = "deck_name"),
      @Result(property = "cardValues", column = "cards", typeHandler = IntegerListTypeHandler.class)
  })
  @Select("SELECT d.deck_id, d.deck_name, GROUP_CONCAT(c.card_value) AS cards\n"
      + "FROM deck d "
      + "JOIN card c ON d.deck_id = c.deck_id "
      + "GROUP BY d.deck_id;")
  List<DeckCards> getAllDeckCards();


  // GET
  @Results({
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "deckName", column = "deck_name"),
      @Result(property = "cardValues", column = "cards", typeHandler = IntegerListTypeHandler.class)
  })
  @Select("SELECT d.deck_id, d.deck_name, GROUP_CONCAT(c.card_value) AS cards "
      + "FROM deck d "
      + "JOIN card c ON d.deck_id = c.deck_id "
      + "WHERE d.deck_id = #{deckId} "
      + "GROUP BY d.deck_id;")
  DeckCards getDeckCardsById(Integer deckId);
}