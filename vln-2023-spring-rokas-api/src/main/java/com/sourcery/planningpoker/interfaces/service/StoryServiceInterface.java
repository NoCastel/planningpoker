package com.sourcery.planningpoker.interfaces.service;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.model.Story;
import java.time.LocalDate;
import java.util.List;

public interface StoryServiceInterface {
  List<Story> getAllStories();

  List<Story> getStoriesByRoom(Integer roomId);

  Story getStoryById(Integer id);

  Story addStory(Story story) throws ExceptionBuilder;

/*  void updateStory( Integer id,
                  String name,
                  LocalDate date,
                  Integer estimate,
                  Integer roomId) throws ExceptionBuilder;*/

  Story deleteStory(Integer id) throws ExceptionBuilder;
}
