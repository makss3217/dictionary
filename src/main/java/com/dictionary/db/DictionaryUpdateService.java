package com.dictionary.db;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DictionaryUpdateService extends DictionaryRepository {

  @Modifying(clearAutomatically = true)
  @Query("update DictionaryEntryEntity d set d.rating = d.rating + 1 where d.headWord =:headWord")
  void increaseWordRating(@Param(value = "headWord") String headWord);

}
