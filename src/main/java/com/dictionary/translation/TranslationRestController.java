package com.dictionary.translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
class TranslationRestController {

  private final TranslationService translationService;

  @GetMapping(value = "/translate")
  public String translateSentence(@RequestParam String sentence) {
    return translationService.translateSentence(sentence);
  }

  @GetMapping(value = "/translate/quoted")
  public String translateQuotedSentence(@RequestParam String sentence) {
    return translationService.translateQuotedSentence(sentence);
  }
}
