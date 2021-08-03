package com.dictionary.translation;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dictionary.db.DictionaryUpdateService;

import lombok.AllArgsConstructor;
import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
class TranslationServiceImpl implements TranslationService {

  private final TranslationRepository translationRepository;
  private final DictionaryUpdateService dictionaryUpdateService;

  @Override
  public String translateSentence(String sentence) {

    List<String> wordsToTranslate = Arrays.asList(StringUtils.split(sentence));
    return translateAndUpdateWordsRating(wordsToTranslate);
    
  }

  @Override
  public String translateQuotedSentence(String quotedSentence) {
    List<String> wordsToTranslate = validateAndSplitQuotedSentence(quotedSentence);
    return translateAndUpdateWordsRating(wordsToTranslate);
  }

  private List<String> validateAndSplitQuotedSentence(String quotedSentence) {
    return Arrays.asList(StringUtils.split(quotedSentence)).stream()
        .map(this::removeQuotesOrThrow)
        .collect(toList());

  }

  private String removeQuotesOrThrow(String quotedWord) {
    if (quotedWord.startsWith("\"") && quotedWord.endsWith("\"")) {
      return quotedWord.substring(1, quotedWord.length() - 1);
    } else {
      throw new SentenceValidationException("Each word has to be quoted.");
    }
  }

  private String translateAndUpdateWordsRating(List<String> wordsToTranslate) {
    List<String> translatedWords = wordsToTranslate.stream()
        .map(this::translateAndIncreaseRating)
        .collect(toList());
    return StringUtils.join(translatedWords, " ");
  }

  private String translateAndIncreaseRating(String word) {
    return translationRepository.findByHeadWord(word)
        .map(t -> getValueAndIncreaseRating(t, word))
        .orElse(word);
  }

  private String getValueAndIncreaseRating(ProjectionOnTranslations translation, String headWord) {
    dictionaryUpdateService.increaseWordRating(headWord);
    return translation.getTranslation();
  }

}
