package com.dictionary.db;

import java.util.List;

public interface PolishDictionaryInsertionService {
  
  void insert(List<PolishToEnglishTranslation> entries);

}
