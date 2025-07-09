package outsera.prova.repositories.producers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import outsera.prova.models.Movies;
import outsera.prova.models.Producers;

import java.util.Optional;

@Repository
public interface ProducersRep extends JpaRepository<Producers, Long> {
    Optional<Producers> findFirstByName(String name);
}
