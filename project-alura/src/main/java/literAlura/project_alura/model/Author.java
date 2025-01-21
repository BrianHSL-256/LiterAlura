package literAlura.project_alura.model;

import jakarta.persistence.*;

import java.util.List;


@Entity   //Table on my database
@Table(name = "authors")
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(unique = true)
    private String nombre;

    private Integer nacimiento;
    private Integer fallecimiento;

    @Transient
    //@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;


    public Author(){}

    public Author(DataAuthor d){
         this.nombre = d.nombre();
         this.nacimiento = d.nacimiento();
         this.fallecimiento = d.fallecimiento();
    }

    public Author(String nombre, Integer nacimiento, Integer fallecimiento) {

        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.fallecimiento = fallecimiento;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<Book> getBooks() {
        return books;
    }



    public void setBooks(List<Book> books) {
        books.forEach(b -> b.setAutor(this));
        this.books = books;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", fallecimiento=" + fallecimiento
                ;
    }
}
