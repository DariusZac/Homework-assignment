package com.movie.wiki;

import com.movie.wiki.business.repository.MovieDetailRepository;
import com.movie.wiki.business.repository.model.MovieDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WikiApplicationIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private MovieDetailRepository repository;

    private final String URL = "/details/100";

    @Test
    void contextLoads() throws Exception {
        //given
        MovieDetail detail = new MovieDetail();
        detail.setId(100L);
        detail.setDescription("Test");
        when(repository.findById(anyLong())).thenReturn(Optional.of(detail));
        //when
        ResponseEntity<String> forEntity = restTemplate.getForEntity(URL, String.class);
        //then
        Assertions.assertEquals(HttpStatus.OK, forEntity.getStatusCode());
        String expectedBody = "{\"id\":100,\"description\":\"Test\",\"budget\":0,\"date\":null}";
        JSONAssert.assertEquals(expectedBody, forEntity.getBody(), false);
    }

}
