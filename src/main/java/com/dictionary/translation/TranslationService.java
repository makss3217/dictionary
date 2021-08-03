package com.dictionary.translation;

public interface TranslationService {
  String translateSentence(String sentence);
  
  String translateQuotedSentence(String quotedSentence);
}
