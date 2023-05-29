package com.sourcery.planningpoker.service;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.interfaces.service.StoryServiceInterface;
import com.sourcery.planningpoker.mapper.StoryMapper;
import com.sourcery.planningpoker.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class StoryService implements StoryServiceInterface {
  @Autowired
  private StoryMapper storyMapper;

  @Override
  public List<Story> getAllStories() {
    return storyMapper.getAllStories();
  }

  @Override
  public List<Story> getStoriesByRoom(Integer roomId) {
    return storyMapper.getStoriesByRoom(roomId);
  }

  @Override
  public Story getStoryById(Integer id) {
    return storyMapper.getStoryById(id);
  }

  @Override
  public Story addStory(Story story) throws ExceptionBuilder {
    Story newStory = storyMapper.getStoryById(story.getId());

    if (newStory != null){
      throw new ExceptionBuilder("Story already exists", HttpStatus.CONFLICT);
    }
    storyMapper.addStory(story);
    return story;
  }
/*
  @Override
  public void updateStory(Integer id, String name, LocalDate date, Integer estimate, Integer roomId) throws ExceptionBuilder {
    Story story = storyMapper.getStoryById(id);
    if (story == null){
      throw new ExceptionBuilder("Story not found.", HttpStatus.NOT_FOUND);
    }

    if (name != null
        && Objects.equals(story.getName(), name)) {
      throw new ExceptionBuilder("New name must differ from the old one.", HttpStatus.CONFLICT);
    } else if (name != null) {
      story.setName(name);
    }

    if (date != null
        && Objects.equals(story.getDate(), date)) {
      throw new ExceptionBuilder("New date must differ from the old one.", HttpStatus.CONFLICT);
    } else if (date != null) {
      story.setDate(date);
    }

    if (estimate != null
        && Objects.equals(story.getEstimate(), estimate)) {
      throw new ExceptionBuilder("New estimate must differ from the old one.", HttpStatus.CONFLICT);
    } else if (estimate != null) {
      story.setEstimate(estimate);
    }

    if (roomId != null
        && Objects.equals(story.getRoomId(), roomId)) {
      throw new ExceptionBuilder("New room id must differ from the old one.", HttpStatus.CONFLICT);
    } else if (roomId != null) {
      story.setRoomId(roomId);
    }

    storyMapper.updateStory(story);
  }
*/
  @Override
  public Story deleteStory(Integer id) throws ExceptionBuilder {
    Story story = storyMapper.getStoryById(id);
    if (story == null){
      return null;
    }
    storyMapper.deleteStory(id);
    return story;
  }
}
