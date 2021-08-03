package com.dictionary.rating;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class DictionaryUsageRatingServiceImpl implements DictionaryUsageRatingService {

  @Autowired
  private final DictionaryUsageRatingRepository repository;

  @Override
  public DictionaryUsageRating getUsageRating() {
    return new DictionaryUsageRating(getWordRating());
  }

  private List<WordRating> getWordRating() {
    return repository.findAllProjectedBy().stream()
        .map(this::mapToWordRating)
        .sorted(comparingLong(WordRating::getRating).reversed())
        .collect(toList());
  }

  private WordRating mapToWordRating(ProjectionOnWordRatings projection) {
    return new WordRating(projection.getHeadWord(), projection.getRating());
  }

}
