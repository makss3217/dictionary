package com.dictionary.rating;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.dictionary.db.DictionaryRepository;

@Repository
public interface DictionaryUsageRatingRepository extends DictionaryRepository {

  List<ProjectionOnWordRatings> findAllProjectedBy();
  
}
