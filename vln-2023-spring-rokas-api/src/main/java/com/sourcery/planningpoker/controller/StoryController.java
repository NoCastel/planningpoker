package com.sourcery.planningpoker.controller;

import com.sourcery.planningpoker.exceptions.ExceptionBuilder;
import com.sourcery.planningpoker.handlers.response.ResponseMessageHandler;
import com.sourcery.planningpoker.model.Card;
import com.sourcery.planningpoker.model.Story;
import com.sourcery.planningpoker.service.StoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@Validated
@CrossOrigin
@RequestMapping("api/v1/stories")
public class StoryController {
  @Autowired
  private StoryService storyService;

  Map<String, String> response;

  @Autowired
  private ResponseMessageHandler responseMessageHandler;

  @GetMapping("/all-stories")
  public ResponseEntity<List<Story>> getAllStories(){
    List<Story> stories = storyService.getAllStories();
    return stories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(stories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Story> getStoryById(@PathVariable @Min(1) Integer id ) {
    Story story = storyService.getStoryById(id);
    return story != null ? ResponseEntity.ok(story) : ResponseEntity.noContent().build();
  }

  @GetMapping("/room/{roomId}")
  public ResponseEntity<List<Story>> getStoriesByRoom(@PathVariable @Min(1) Integer roomId) {
    List<Story> stories = storyService.getStoriesByRoom(roomId);
    return stories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(stories);
  }

  @PostMapping("/add")
  public ResponseEntity<Object> addStory(@Valid @RequestBody Story story) {
    try{
      Story newStory = storyService.addStory(story);
      return ResponseEntity.status(HttpStatus.CREATED).body(newStory);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }

  /*@PutMapping(path = "/update/{id}")
  public ResponseEntity<Map<String, String>> updateStory(@PathVariable("id") int id,
                                                         @RequestParam(required = false) String name,
                                                         @RequestParam(required = false) LocalDate date,
                                                         @RequestParam(required = false) Integer estimate,
                                                         @RequestParam(required = false) Integer roomId) {
    try {
      storyService.updateStory(id, name, date,estimate, roomId);
      response = responseMessageHandler.generateResponseMessageJson("Story was updated successfully.");
      return ResponseEntity.ok(response);
    } catch (ExceptionBuilder e) {
      response = responseMessageHandler.generateResponseMessageJson(e.getMessage());
      return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
  }*/

  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity<Object> deleteCard(@PathVariable("id") @Min(1) Integer id) throws ExceptionBuilder {
    Story story = storyService.deleteStory(id);
    if (story != null) {
      response = responseMessageHandler.generateResponseMessageJson("story was deleted successfully.");
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
