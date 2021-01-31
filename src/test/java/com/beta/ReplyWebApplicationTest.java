package com.beta;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReplyWebApplicationTest extends TestDataProvider {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnSuccessDefaultMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(DEFAULT_REPLY_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("Message is empty"));
    }

    @Test
    public void shouldReturnSuccessSharedMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_ONE, "hello")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("hello"));
    }

    @Test
    public void shouldReturnSuccessDerivedReverseResponseMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "1-hello")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("olleh"));
    }

    @Test
    public void shouldReturnSuccessDerivedEncodedResponseMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "2-hello")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("5d41402abc4b2a76b9719d911017c592"));
    }

    @Test
    public void shouldReturnSuccessDerivedDoubleReverseResponseMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "11-kbzw9ru")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("kbzw9ru"));
    }

    @Test
    public void shouldReturnSuccessDerivedDoubleEncodedResponseMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "22-kbzw9ru")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("e8501e64cf0a9fa45e3c25aa9e77ffd5"));
    }

    @Test
    public void shouldReturnSuccessDerivedReversedAndEncodedResponseMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "12-kbzw9ru")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("5a8973b3b1fafaeaadf10e195c6e1dd4"));
    }

    @Test
    public void shouldReturnFailureOnMissingRules() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "-kbzw9ru")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("Error: Doesn't contain both rule and string."));
    }

    @Test
    public void shouldReturnFailureOnMissingString() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "11-")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("Error: Doesn't contain both rule and string."));
    }

    @Test
    public void shouldReturnFailureOnPassingUnsupportedDigitForRuleGeneration() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "123-kbzw9ru")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("Error: Number(3) Not Supported For Rule Generation!"));
    }

    @Test
    public void shouldReturnFailureOnPassingInvalidCharactersForString() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "121-kbz#w9ru")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnFailureOnPassingUpperCaseCharactersForString() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(REPLY_ENDPOINT_VERSION_TWO, "12-HELLO")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }
}
