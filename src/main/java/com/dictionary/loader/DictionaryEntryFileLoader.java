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
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

import com.dictionary.db.DictionaryEntry;
import com.dictionary.db.DictionaryInsertionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
class DictionaryEntryFileLoader implements DictionaryEntryLoader {

  private static final String INIT_DATA_PATH = "/src/main/resources/dictionary.json";
  private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

  private DictionaryInsertionService insertionService;
  
  @PostConstruct
  public void initData() throws  IOException   {
    loadData();
  }

  @Override
  public void loadData() throws  IOException  {
    Map<String, String> entryMap = JSON_MAPPER.readValue(readStringFromFile(createAbsolutePath()), Map.class);
    List<DictionaryEntry> dictionaryEntries = createDictionaryEntries(entryMap);
    insertionService.insert(dictionaryEntries);
  }

  private String createAbsolutePath() {
    return System.getProperty("user.dir") + INIT_DATA_PATH;
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
