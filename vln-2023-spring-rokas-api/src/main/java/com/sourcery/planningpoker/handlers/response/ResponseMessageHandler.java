package com.sourcery.planningpoker.handlers.response;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseMessageHandler {

  public Map<String, String> generateResponseMessageJson(String text) {
    Map<String, String> response = new HashMap<>();
    response.put("message", text);
    return response;
  }

}
