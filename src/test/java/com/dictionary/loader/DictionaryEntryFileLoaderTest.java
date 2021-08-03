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

  private static final String INIT_TEST_DATA_FILE = "src/test/resources/testDictionaryData.json";

  private DictionaryEntryLoader dictionaryEntryLoader;
  private MockInsertionService mockInsertionService;

  @BeforeEach
  void setUp() {
    mockInsertionService = new MockInsertionService();
    dictionaryEntryLoader = new DictionaryEntryFileLoader(INIT_TEST_DATA_FILE, mockInsertionService);
  }

  @Test
  void testLoadDataFromFile() throws Exception {
    dictionaryEntryLoader.loadData();

    assertThat(mockInsertionService.getDictionaryRepo()).containsExactlyElementsOf(List.of(
        new DictionaryEntry("headWord1", "translation1"),
        new DictionaryEntry("headWord2", "translation2")));
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
