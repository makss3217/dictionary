package com.dictionary.rating;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 class DictionaryUsageRatingServiceImplTest {
   
   private static final WordRating WORD_RATING_1 = new WordRating("headWord1", 1);
   private static final WordRating WORD_RATING_2 = new WordRating("headWord2", 2);
   private static final WordRating WORD_RATING_3 = new WordRating("headWord3", 3);
   
   private static final ProjectionOnWordRatings PROJECTION_WORD_RATING_1 = new ProjectionOnWordRatings("headWord1", 1);
   private static final ProjectionOnWordRatings PROJECTION_WORD_RATING_2 = new ProjectionOnWordRatings("headWord2", 2);
   private static final ProjectionOnWordRatings PROJECTION_WORD_RATING_3 = new ProjectionOnWordRatings("headWord3", 3);
   
   private DictionaryUsageRatingRepository repository;
   private DictionaryUsageRatingService service;
   
   @BeforeEach
   void setUp() {
     repository = new MockRepository();
     service = new DictionaryUsageRatingServiceImpl(repository);
   }
   
   @Test
   void resultsAreSortedByDecreasingRatings() {
    assertThat(service.getUsageRating().getWordUsageRatings())
    .containsExactlyElementsOf(List.of(WORD_RATING_3, WORD_RATING_2, WORD_RATING_1));
   }
   
   private static class MockRepository implements DictionaryUsageRatingRepository {

    @Override
    public List<ProjectionOnWordRatings> findAllProjectedBy() {
      return List.of(PROJECTION_WORD_RATING_2, PROJECTION_WORD_RATING_1, PROJECTION_WORD_RATING_3);
    }
     
   }
   
}
