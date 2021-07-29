package com.dictionary.db;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
interface PolishDictionaryCrudRepository extends PolishDictionaryRepository {
  
  PolishEntryEntity save(PolishEntryEntity entity);
  
  List<PolishEntryEntity> saveAll(List<PolishEntryEntity> entities);
}
