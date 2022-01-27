package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import java.nio.file.Path;

@Testcontainers
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
class VerificationTest {
    @Container
    static DockerComposeContainer<?> compose = new DockerComposeContainer<>(
            Path.of("docker-compose.yml").toFile()
    );

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldPerformProductCRUD() throws Exception {
        // getAll
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/orders/getAll")
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                        {
                                         "orders": [
                                          {
                                          "id": 1,
                                          "salesman": "Andrey",
                                          "price": 3723470,
                                          "bitcoin": 0.3,
                                          "review": 100,
                                          "min_amount": 1000,
                                          "max_amount": 5000000,
                                          "successful_deals": 0,
                                          "status": "online",
                                          "proStatus": "image.jpeg"
                                        },
                                        {
                                        "id": 2,
                                        "salesman": "Vanya",
                                        "price": 3723470,
                                        "bitcoin": 0.3,
                                        "review": 100,
                                        "min_amount": 1000,
                                        "max_amount": 5000000,
                                        "successful_deals": 0,
                                        "status": "online",
                                        "proStatus": "image.jpeg"
                                        }
                                        ]
                                        }
                                        """
                        )
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/orders/getById")
                                .queryParam("id", String.valueOf(2))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                                {
                                                  "order": {
                                                    "id": 2,
                                                    "salesman": "Vanya",
                                                    "price": 3723470,
                                                    "bitcoin": 0.3,
                                                    "review": 100,
                                                    "min_amount": 1000,
                                                    "max_amount": 5000000,
                                                    "successful_deals": 0,
                                                    "status": "online",
                                                    "proStatus": "image.jpeg"
                                                  }
                                                }
                                        """
                        )
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/orders/save")
                                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                                .content(
                                        // language=JSON
                                        """
                                                        {
                                                            "id": 1,
                                                            "salesman": "Misha",
                                                            "price": 3700000,
                                                            "bitcoin": 3,
                                                            "review": 5,
                                                            "min_amount": 1000,
                                                            "max_amount": 500000,
                                                            "successful_deals": 15,
                                                            "status": "online",
                                                            "proStatus": "no"
                                                        }
                                                """
                                )
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                              {
                                                   "order": {
                                                     "id": 1,
                                                     "salesman": "Misha",
                                                     "price": 3700000,
                                                     "bitcoin": 3.0,
                                                     "review": 5,
                                                     "min_amount": 1000,
                                                     "max_amount": 500000,
                                                     "successful_deals": 15,
                                                     "status": "online",
                                                     "proStatus": "no"
                                                   }
                                                 }
                                        """
                        )
                );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/orders/getById")
                                .queryParam("id", String.valueOf(222))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isNotFound()
                );

    }
}
