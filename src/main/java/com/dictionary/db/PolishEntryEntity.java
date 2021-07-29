package com.dictionary.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Value;

@Entity
@Table(name = "PolishDictionary")
@Value
@Builder
class PolishEntryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Column(nullable = false)
  private String headWord;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "Polish_English", 
  joinColumns = @JoinColumn(name = "polish_id", referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "english_id", referencedColumnName = "id"))
  private List<EnglishEntryEntity> englishTranslactions;
  
  private long rating; 
  
}
