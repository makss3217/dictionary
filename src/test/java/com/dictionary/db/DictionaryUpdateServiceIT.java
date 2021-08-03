package com.dictionary.db;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

@SpringBootTest
@Transactional
class DictionaryUpdateServiceIT {

  private static final String SAMPLE_HEAD_WORD = "sampleHeadWord";
  private static final List<DictionaryEntry> SAMPLE_ENTRY = List.of(new DictionaryEntry(SAMPLE_HEAD_WORD, "sampleTranslaction1"));

  @Autowired
  private DictionaryCrudRepository crudRepository;
  @Autowired
  private DictionaryInsertionService insertionService;
  @Autowired
  private DictionaryUpdateService updateService;

  @Test
  void testIncreasingHeadWordRating() {
    insertionService.insert(SAMPLE_ENTRY);

    for (int i = 0; i < 10; i++) {
      updateService.increaseWordRating(SAMPLE_HEAD_WORD);
    }

    assertThat(crudRepository.findByHeadWord(SAMPLE_HEAD_WORD).get().getRating()).isEqualTo(10);
  }

}
