package outsera.prova.repositories.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import outsera.prova.models.Movies;

@Repository
public interface MoviesRep extends JpaRepository<Movies, Long> {
}
