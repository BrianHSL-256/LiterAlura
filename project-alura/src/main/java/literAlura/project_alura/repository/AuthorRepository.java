package literAlura.project_alura.repository;

import literAlura.project_alura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

        Optional<Author> findByNombre(String nombre);


        @Query("SELECT a FROM Author a WHERE a.fallecimiento >= :año ")
        List<Author> autoresVivosEnUnAño(int año);


}
