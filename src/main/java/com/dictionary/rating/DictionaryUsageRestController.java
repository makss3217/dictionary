package com.dictionary.rating;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
class DictionaryUsageRestController {

  private final DictionaryUsageRatingService dictionaryUsageRatingService;
  
  @GetMapping(value = "/rating", produces = APPLICATION_JSON_VALUE)
  public DictionaryUsageRating getDictionaryUsageRating() {
    return dictionaryUsageRatingService.getUsageRating();
  }
  
}
