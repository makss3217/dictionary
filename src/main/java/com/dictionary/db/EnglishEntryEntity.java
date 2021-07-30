package com.dictionary.db;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "EnglishDictionary")
@Data
@EqualsAndHashCode(callSuper = true)
class EnglishEntryEntity extends DictionaryEntryEntity {
}
