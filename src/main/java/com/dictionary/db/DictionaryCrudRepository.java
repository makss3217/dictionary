package com.dictionary.db;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
interface DictionaryCrudRepository extends DictionaryRepository {
  
  Iterable<DictionaryEntryEntity> findAll();

  Iterable<DictionaryEntryEntity> saveAll(Iterable<DictionaryEntryEntity> entities);

  Optional<DictionaryEntryEntity> findByHeadWord(String headWord);
  
  boolean existsByHeadWord(String headWord);
}
