package com.dictionary.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static java.util.stream.Collectors.toList;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class PolishDictionaryInsertionServiceImpl implements PolishDictionaryInsertionService {

  @Autowired
  private final PolishDictionaryCrudRepository crudRepository;
  
  @Override
  public void insert(List<PolishToEnglishTranslation> entries) {
    crudRepository.saveAll(mapToEntities(entries));
  }

  private Iterable<PolishEntryEntity> mapToEntities(List<PolishToEnglishTranslation> entries) {
    return entries.stream()
        .map(this::updateOrCreateEntity)  
        .collect(toList());
  }
  
  private PolishEntryEntity updateOrCreateEntity(PolishToEnglishTranslation entry) {
    return null;
  }

}
