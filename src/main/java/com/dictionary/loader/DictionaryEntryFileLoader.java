package com.dictionary.loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

import com.dictionary.db.DictionaryEntry;
import com.dictionary.db.DictionaryInsertionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
class DictionaryEntryFileLoader implements DictionaryEntryLoader {

  private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

  private String initDataPath;
  private DictionaryInsertionService insertionService;

  @Autowired
  public DictionaryEntryFileLoader(
      @Value("${init.data.path}") String initDataPath,
      DictionaryInsertionService insertionService) {
    this.initDataPath = initDataPath;
    this.insertionService = insertionService;
  }

  @PostConstruct
  public void initData() throws IOException {
    loadData();
  }

  @Override
  public void loadData() throws IOException {
    Map<String, String> entryMap = JSON_MAPPER.readValue(readStringFromFile(initDataPath), Map.class);
    List<DictionaryEntry> dictionaryEntries = createDictionaryEntries(entryMap);
    insertionService.insert(dictionaryEntries);
  }

  private String readStringFromFile(String path) throws IOException {
    FileInputStream fis = new FileInputStream(path);
    return IOUtils.toString(fis, StandardCharsets.UTF_8);
  }

  private List<DictionaryEntry> createDictionaryEntries(Map<String, String> entryMap) {
    return entryMap.entrySet().stream()
        .map(this::mapToDictionaryEntry)
        .collect(toList());
  }

  DictionaryEntry mapToDictionaryEntry(Entry<String, String> mapEntry) {
    return new DictionaryEntry(mapEntry.getKey(), mapEntry.getValue());
  }

}
