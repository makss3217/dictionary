package com.dictionary.db;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface EnglishDictionaryRepository extends Repository<EnglishEntryEntity, Long> {
}
