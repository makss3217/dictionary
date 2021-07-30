package com.dictionary.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class EnglishDictionaryInsertionServiceImpl implements EnglishDictionaryInsertionService {

  @Autowired
  private final EnglishDictionaryCrudRepository crudRepository;

  @Override
  public long insert(String word) {
    EnglishEntryEntity entryToInsert = new EnglishEntryEntity();
    entryToInsert.setHeadWord(word);
    EnglishEntryEntity insertedEntity = crudRepository.save(entryToInsert);
    return insertedEntity.getId();
  }

}
