package com.dictionary.db;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface PolishDictionaryRepository extends Repository<PolishEntryEntity, Long> {
}
