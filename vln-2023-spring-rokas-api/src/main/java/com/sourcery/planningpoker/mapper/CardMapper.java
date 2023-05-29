package com.sourcery.planningpoker.mapper;

import com.sourcery.planningpoker.model.Card;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface CardMapper {
  // GET
  @Results({
      @Result(property = "cardId", column = "card_id"),
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "cardValue", column = "card_value")
  })
  @Select("SELECT * FROM card")
  List<Card> getAllCards();

  @Results({
      @Result(property = "cardId", column = "card_id"),
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "cardValue", column = "card_value")
  })
  @Select("SELECT * FROM card WHERE card_id=#{cardId}")
  Card getCardById(int cardId);

  @Results({
      @Result(property = "cardId", column = "card_id"),
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "cardValue", column = "card_value")
  })
  @Select("SELECT * FROM card WHERE deck_id=#{deckId}")
  List<Card> getCardsByDeck(int deckId);

  @Results({
      @Result(property = "cardId", column = "card_id"),
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "cardValue", column = "card_value")
  })
  @Select("SELECT * FROM card WHERE deck_id=#{deckId} and card_value=#{cardValue}")
  Card getCardByDeckAndValue(int deckId, int cardValue);

  // POST
  @Results({
      @Result(property = "cardId", column = "card_id"),
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "cardValue", column = "card_value")
  })
  @Options(useGeneratedKeys = true, keyProperty = "cardId", keyColumn = "card_id")
  @Insert("INSERT INTO card(deck_id, card_value) VALUES(#{deckId}, #{cardValue})")
  void addCard(Card card);

  // PUT
  @Results({
      @Result(property = "cardId", column = "card_id"),
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "cardValue", column = "card_value")
  })
  @Update("UPDATE card SET "
      + "deck_id = #{card.deckId}, "
      + "card_value = #{card.cardValue} "
      + "WHERE card_id = #{card.cardId}")
  void updateCard(@Param("card") Card card);

  // DELETE
  @Results({
      @Result(property = "cardId", column = "card_id"),
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "cardValue", column = "card_value")
  })
  @Delete("DELETE FROM card WHERE card_id = #{cardId}")
  void deleteCard(@Param("cardId") int cardId);
}