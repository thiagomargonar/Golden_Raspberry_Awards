package outsera.prova.repositories.studios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import outsera.prova.models.Movies;
import outsera.prova.models.Producers;
import outsera.prova.models.Studios;

import java.util.Optional;

@Repository
public interface StudiosRep extends JpaRepository<Studios, Long> {
    Optional<Studios> findFirstByName(String name);
}
