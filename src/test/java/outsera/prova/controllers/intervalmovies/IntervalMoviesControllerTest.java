package outsera.prova.controllers.intervalmovies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import outsera.prova.models.Movies;
import outsera.prova.models.Producers;
import outsera.prova.models.Studios;
import outsera.prova.repositories.movies.MoviesRep;
import outsera.prova.repositories.producers.ProducersRep;
import outsera.prova.repositories.studios.StudiosRep;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class IntervalMoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MoviesRep moviesRep;

    @Autowired
    private StudiosRep studiosRep;

    @Autowired
    private ProducersRep producersRep;

    @BeforeEach
    void setup() {
        moviesRep.deleteAll();

        Producers producer = new Producers();
        producer.setName("John Doe");

        Studios studio = new Studios();
        studio.setName("Studio X");

        Movies movie1 = new Movies();
        movie1.setTitle("Bad Movie 1");
        movie1.setYear(2000);
        movie1.setWinner(true);
        movie1.setProducers(producersRep.save(producer));
        movie1.setStudios(List.of(studiosRep.save(studio)));

        Movies movie2 = new Movies();
        movie2.setTitle("Bad Movie 2");
        movie2.setYear(2005);
        movie2.setWinner(true);
        movie2.setProducers(producersRep.save(producer));
        movie2.setStudios(List.of(studiosRep.save(studio)));

        moviesRep.saveAll(List.of(movie1, movie2));
    }

    @Test
    void shouldReturnWorstProducersWithInterval() throws Exception {
        mockMvc.perform(get("/worst_movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min[0].producer").value("John Doe"))
                .andExpect(jsonPath("$.min[0].interval").value(5));
    }
}