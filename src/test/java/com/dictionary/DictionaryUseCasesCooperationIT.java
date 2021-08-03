package com.dictionary;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dictionary.rating.DictionaryUsageRating;
import com.dictionary.rating.WordRating;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class DictionaryUseCasesCooperationIT {

  private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void useCasesCooperationTest() throws Exception {
    translateSentence();
    translateQuotedSentence();
    showRating();
  }

  private void translateSentence() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/translate?sentence=Ala ma kota"))
        .andExpect(status().isOk())
        .andReturn();

    String result = mvcResult.getResponse().getContentAsString();
    assertThat(result).isEqualTo("Alice has a cat");
  }

  private void translateQuotedSentence() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/translate/quoted?sentence=\"Ala\" \"ma\" \"psa\""))
        .andExpect(status().isOk())
        .andReturn();

    String result = mvcResult.getResponse().getContentAsString();
    assertThat(result).isEqualTo("Alice has psa");
  }

  private void showRating() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/rating"))
        .andExpect(status().isOk())
        .andReturn();

    DictionaryUsageRating ratingResult = JSON_MAPPER.readValue(mvcResult.getResponse().getContentAsString(), DictionaryUsageRating.class);
    assertThat(ratingResult.getWordUsageRatings())
        .contains(new WordRating("Ala", 2))
        .contains(new WordRating("ma", 2))
        .contains(new WordRating("kota", 1))
        .contains(new WordRating("spodnie", 0));
  }
}
