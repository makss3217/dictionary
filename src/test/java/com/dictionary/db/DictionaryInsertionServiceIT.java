package com.dictionary.db;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

@SpringBootTest
@Transactional
class DictionaryInsertionServiceIT {

  private static final List<DictionaryEntry> SAMPLE_ENTRIES = List.of(
      new DictionaryEntry("sampleHeadWord1", "sampleTranslaction1"),
      new DictionaryEntry("sampleHeadWord2", "sampleTranslaction2"));

  private static final List<DictionaryEntry> SAMPLE_ENTRY_WITH_UPDATED_TRANSLATION = List.of(
      new DictionaryEntry("sampleHeadWord1", "updatedTranslaction"));

  @Autowired
  private DictionaryCrudRepository crudRepository;
  @Autowired
  private DictionaryInsertionService insertionService;

  @Test
  void EntriesAreProperlyInsertedToRepo() {
    insertionService.insert(SAMPLE_ENTRIES);

    assertThat(crudRepository.findAll())
        .anyMatch(entry -> entry.getHeadWord().equals("sampleHeadWord1"))
        .anyMatch(entry -> entry.getHeadWord().equals("sampleHeadWord2"));
  }

  @Test
  void WhenHeadWordAlreadyExistInDictionaryThenTranslationIsUpdated() {
    insertionService.insert(SAMPLE_ENTRIES);

    insertionService.insert(SAMPLE_ENTRY_WITH_UPDATED_TRANSLATION);
    
    assertThat(crudRepository.findByHeadWord("sampleHeadWord1").get().getTranslation()).isEqualTo("updatedTranslaction");
 }

}
