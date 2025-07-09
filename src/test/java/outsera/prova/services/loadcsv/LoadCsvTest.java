package outsera.prova.services.loadcsv;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import outsera.prova.repositories.movies.MoviesRep;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoadCsvTest {
    @Autowired
    private MoviesRep moviesRep;

    @Test
    void shouldLoadMoviesFromCsv() {
        var movies = moviesRep.findAll();
        assertFalse(movies.isEmpty(), "CSV não foi carregado corretamente");
    }
}