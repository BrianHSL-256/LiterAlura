package literAlura.project_alura.model;

import jakarta.persistence.*;





@Entity
@Table(name = "books" )
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    private String idiomas;
    private Integer descargas;


    @ManyToOne
    private Author autor;


    public Book(){}


    public Book(DataBook d){
        this.titulo = d.titulo();

        DataAuthor dataAuthor = d.autor().get(0); // Toma el primer autor de la lista
        this.autor = new Author(dataAuthor.nombre(), dataAuthor.nacimiento(), dataAuthor.fallecimiento());


        this.idiomas = d.idiomas().get(0);
        this.descargas = d.descargas();


    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idiomas=" + idiomas +
                ", descargas=" + descargas ;
    }
}
