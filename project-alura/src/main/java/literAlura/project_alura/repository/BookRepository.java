package literAlura.project_alura.repository;


import literAlura.project_alura.model.Author;
import literAlura.project_alura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findByTitulo(String titulo);

    List<Book> findByIdiomas(String nombre);

}
