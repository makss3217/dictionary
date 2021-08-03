package com.dictionary.rating;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class WordRating {

  private String headWord;

  private long rating;

  public WordRating() {
    this.headWord = "";
    this.rating = 0;
  }

}
