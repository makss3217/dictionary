package com.dictionary.translation;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dictionary.db.DictionaryRepository;

@Repository
public interface TranslationRepository extends DictionaryRepository {

  Optional<ProjectionOnTranslations> findByHeadWord(String headWord);
}
