package com.dictionary.translation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dictionary.db.DictionaryUpdateService;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import lombok.Getter;

class TranslationServiceImplTest {

  private static final String UNKNWON_HEAD_WORD = "unknownHeadWord";

  private TranslationService translationService;
  private MockTranslationRepository translationRepository;
  private MockDictionaryUpdateService dictionaryUpdateService;

  @BeforeEach
  void setUp() {
    translationRepository = new MockTranslationRepository();
    dictionaryUpdateService = new MockDictionaryUpdateService();
    translationService = new TranslationServiceImpl(translationRepository, dictionaryUpdateService);
  }

  @Test
  void eachWordIsTranslated() {
    assertThat(translationService.translateSentence("word1 word2")).isEqualTo("word1-translated word2-translated");
  }

  @Test
  void ifDictionaryNotContainsWordThenOriginalWordIsReturned() {
    assertThat(translationService.translateSentence("word1 " + UNKNWON_HEAD_WORD)).isEqualTo("word1-translated " + UNKNWON_HEAD_WORD);
  }

  @Test
  void eachTranslationIncrementWordRating() {
    translationService.translateSentence("word1 word2 word1 " + UNKNWON_HEAD_WORD);

    assertThat(dictionaryUpdateService.getRatingMap().get("word1")).isEqualTo(2);
    assertThat(dictionaryUpdateService.getRatingMap().get("word2")).isEqualTo(1);
    assertThat(dictionaryUpdateService.getRatingMap().get(UNKNWON_HEAD_WORD)).isNull();
    ;
  }

  @Test
  void translateSentenceInQuotedSentenceMode() {
    assertThat(translationService.translateQuotedSentence("\"word1\" \"word2\"")).isEqualTo("word1-translated word2-translated");
  }

  @Test
  void translateUnQuotedWordInQuotedSentenceModeThrowAnException() {
    assertThatExceptionOfType(SentenceValidationException.class)
        .isThrownBy(() -> translationService.translateQuotedSentence("\"word1\" word2"))
        .withMessage("Each word has to be quoted.");
  }

  private static class MockTranslationRepository implements TranslationRepository {
    @Override
    public Optional<ProjectionOnTranslations> findByHeadWord(String headWord) {
      if (headWord.equals(UNKNWON_HEAD_WORD)) {
        return Optional.empty();
      } else {
        return Optional.of(new ProjectionOnTranslations(headWord + "-translated"));
      }
    }
  }

  @Getter
  private static class MockDictionaryUpdateService implements DictionaryUpdateService {
    private Map<String, Integer> ratingMap = new HashMap<>();

    @Override
    public void increaseWordRating(String headWord) {
      int count = ratingMap.containsKey(headWord) ? ratingMap.get(headWord) : 0;
      ratingMap.put(headWord, count + 1);
    }
  }

}
