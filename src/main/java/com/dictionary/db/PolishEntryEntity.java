package com.dictionary.db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "PolishDictionary")
@Data
@EqualsAndHashCode(callSuper = true)
class PolishEntryEntity extends DictionaryEntryEntity {

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "Polish_English", 
      joinColumns = @JoinColumn(name = "polish_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "english_id", referencedColumnName = "id"))
  private List<EnglishEntryEntity> englishTranslactions;

  private long rating;

}
