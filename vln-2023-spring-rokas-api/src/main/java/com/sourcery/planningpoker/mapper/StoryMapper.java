package com.sourcery.planningpoker.mapper;

import com.sourcery.planningpoker.model.Story;
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
import java.util.Optional;

@Mapper
public interface StoryMapper {
  // GET
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "date", column = "date"),
      @Result(property = "estimate", column = "estimate"),
      @Result(property = "roomId", column = "room_id")
  })
  @Select("SELECT * FROM story")
  List<Story> getAllStories();

  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "date", column = "date"),
      @Result(property = "estimate", column = "estimate"),
      @Result(property = "roomId", column = "room_id")
  })
  @Select("SELECT * FROM story WHERE room_id=#{roomId}")
  List<Story> getStoriesByRoom(Integer roomId);

  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "date", column = "date"),
      @Result(property = "estimate", column = "estimate"),
      @Result(property = "roomId", column = "room_id")
  })
  @Select("SELECT * FROM story WHERE id=#{storyId}")
  Story getStoryById(Integer storyId);

  // POST
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "date", column = "date"),
      @Result(property = "estimate", column = "estimate"),
      @Result(property = "roomId", column = "room_id")
  })
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  @Insert("INSERT INTO story(name, date, estimate, room_id) VALUES(#{name},#{date},#{estimate},#{roomId})")
  void addStory(Story story);

  // PUT
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "date", column = "date"),
      @Result(property = "estimate", column = "estimate"),
      @Result(property = "roomId", column = "room_id")
  })
  @Update("UPDATE story SET "
      + "name = #{story.name}, "
      + "date = #{story.date}, "
      + "estimate = #{story.estimate}, "
      + "room_id = #{story.roomId} "
      + "WHERE id = #{story.id}")
  void updateStory(@Param("story") Story story);

  // DELETE
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "date", column = "date"),
      @Result(property = "estimate", column = "estimate"),
      @Result(property = "roomId", column = "room_id")
  })
  @Delete("DELETE FROM story WHERE id = #{id}")
  void deleteStory(@Param("id") Integer id);
}
