package com.dictionary.loader;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dictionary.db.DictionaryEntry;
import com.dictionary.db.DictionaryInsertionService;

import lombok.Getter;

public class DictionaryEntryFileLoaderTest {

  private DictionaryEntryLoader dictionaryEntryLoader;
  private MockInsertionService mockInsertionService;

  @BeforeEach
  void setUp() {
    mockInsertionService = new MockInsertionService();
    dictionaryEntryLoader = new DictionaryEntryFileLoader(mockInsertionService);
  }

  @Test
  void testLoadDataFromFile() throws Exception {
    dictionaryEntryLoader.loadData();

    assertThat(mockInsertionService.getDictionaryRepo())
        .hasSize(11)
        .containsAnyElementsOf(List.of(
            new DictionaryEntry("Ala", "Alice"),
            new DictionaryEntry("kot", "a cat")));
  }

  @Getter
  private static class MockInsertionService implements DictionaryInsertionService {

    private List<DictionaryEntry> dictionaryRepo = new ArrayList<>();

    @Override
    public void insert(List<DictionaryEntry> entries) {
      dictionaryRepo.addAll(entries);
    }

  }
}
