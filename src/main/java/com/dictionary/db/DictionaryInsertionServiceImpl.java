package com.dictionary.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static java.util.stream.Collectors.toList;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class DictionaryInsertionServiceImpl implements DictionaryInsertionService {

  @Autowired
  private final DictionaryCrudRepository crudRepository;

  @Override
  public void insert(List<DictionaryEntry> entries) {
    crudRepository.saveAll(mapToEntities(entries));
  }

  private Iterable<DictionaryEntryEntity> mapToEntities(List<DictionaryEntry> entries) {
    return entries.stream()
        .map(this::mapToEntityOrUpdate)
        .collect(toList());
  }

  private DictionaryEntryEntity mapToEntityOrUpdate(DictionaryEntry entry) {
    if (crudRepository.existsByHeadWord(entry.getHeadWord())) {
      DictionaryEntryEntity entityToUpdate = crudRepository.findByHeadWord(entry.getHeadWord()).get();
      entityToUpdate.setTranslation(entry.getTranslation());
      return entityToUpdate;
    } else {
      return DictionaryEntryEntity.builder()
          .headWord(entry.getHeadWord())
          .translation(entry.getTranslation())
          .build();
    }
  }

}
