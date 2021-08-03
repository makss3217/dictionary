package com.dictionary.db;

import lombok.Value;

@Value
public class DictionaryEntry {

  private String headWord;
  
  private String translation;
}
