package com.dictionary.rating;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class DictionaryUsageRating {

  private List<WordRating> wordUsageRatings;

  public DictionaryUsageRating() {
    wordUsageRatings = new ArrayList<>();
  }
}
