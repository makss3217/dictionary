package com.dictionary.db;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseDictionaryCrudRepository<T extends DictionaryEntryEntity> extends Repository<T, Long> {

  T save(T entity);
  
  Iterable<T> saveAll(Iterable<T> entities);
  
  List<T> findByHeadWord(String headWord);
  
}
