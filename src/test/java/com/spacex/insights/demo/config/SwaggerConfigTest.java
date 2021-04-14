package com.spacex.insights.demo.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SwaggerConfig.class)
@ExtendWith(SpringExtension.class)
class SwaggerConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void swaggerDocumentationShouldBeAvailableOnQA() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/swagger-ui.html"))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/v2/api-docs"))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/configuration/ui"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mvc.perform(MockMvcRequestBuilders.get("/swagger-resources/smth/smth"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mvc.perform(MockMvcRequestBuilders.get("/configuration/smth/smth"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mvc.perform(MockMvcRequestBuilders.get("/webjars/smth/smth"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}