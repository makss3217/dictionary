package com.dictionary.db;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
class DictionaryEntryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, unique = true)
  private String headWord;

  void setHeadWord(String headWord) {
    this.headWord = headWord;
  }

  String getHeadWord() {
    return headWord;
  }
  
  long getId() {
    return id;
  }

}
