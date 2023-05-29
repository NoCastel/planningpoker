package com.sourcery.planningpoker.mapper;

import com.sourcery.planningpoker.model.Deck;
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
public interface DeckMapper {
  // GET
  @Results({
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "deckName", column = "deck_name")
  })
  @Select("SELECT * FROM deck")
  List<Deck> getAllDecks();

  @Results({
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "deckName", column = "deck_name")
  })
  @Select("SELECT * FROM deck WHERE deck_id=#{deckId}")
  Deck getDeckById(Integer deckId);

  // POST
  @Results({
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "deckName", column = "deck_name")
  })
  @Options(useGeneratedKeys = true, keyColumn = "deck_id", keyProperty = "deckId")
  @Insert("INSERT INTO deck(deck_name) VALUES(#{deckName})")
  void addDeck(Deck deck);

  // PUT
  @Results({
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "deckName", column = "deck_name")
  })
  @Update("UPDATE deck SET "
      + "deck_name = #{deck.deckName} "
      + "WHERE deck_id = #{deck.deckId}")
  void updateDeck(@Param("deck") Deck deck);

  // DELETE
  @Results({
      @Result(property = "deckId", column = "deck_id"),
      @Result(property = "deckName", column = "deck_name")
  })
  @Delete("DELETE FROM deck WHERE deck_id = #{deckId}")
  void deleteDeck(@Param("deckId") Integer deckId);
}
