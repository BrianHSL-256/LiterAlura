package literAlura.project_alura;

import literAlura.project_alura.model.Author;
import literAlura.project_alura.model.Book;
import literAlura.project_alura.model.DataBook;
import literAlura.project_alura.model.DataResults;
import literAlura.project_alura.repository.AuthorRepository;
import literAlura.project_alura.repository.BookRepository;
import literAlura.project_alura.service.APIConsumption;
import literAlura.project_alura.service.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner keyboard = new Scanner(System.in);
    private APIConsumption apiConsumption = new APIConsumption();


    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private DataConverter conversor = new DataConverter();

    private BookRepository repository;
    private  AuthorRepository authorRepository;

    private List<DataBook> dataBooks = new ArrayList<>();

    private List<Book> books = new ArrayList<>();

    public Main(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;

    }

    public void showMenu(){


        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Search  book by title
                    2 - List of books searched
                    3 - List of books by language
                    4 - List of authors 
                    5 - List of authors that are alive in a year
      
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = keyboard.nextInt();
            keyboard.nextLine();


            switch (opcion) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    listBooksByLanguage();
                    break;
                case 4:
                    listAuthors();
                    break;
                case 5:
                    listAuthorAliveInAYear();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }






        }



    }



    public DataBook getDataBook(){
        System.out.println("Write the name of the book: ");
        var nameBook = keyboard.nextLine();


        var json = apiConsumption.obtainData(URL_BASE + nameBook.replace(" ", "+"));

        var dataResults = conversor.getData(json, DataResults.class);

        DataBook data = dataResults.resultados().get(0);

        return data;

    }

    public void searchByTitle(){

        DataBook datos = getDataBook();

        System.out.println(datos);


       // Verificar si el autor ya existe en la base de datos
      var dataAuthor = datos.autor().get(0);


        Author author   = authorRepository.findByNombre(dataAuthor.nombre())
                .orElseGet(() -> {
                    // Crear y guardar un nuevo autor si no existe
                    Author newAuthor = new Author(dataAuthor);
                    return authorRepository.save(newAuthor);
                });



        //Verificar si el libro buscado ya existe en la base de datos

        Book book = repository.findByTitulo(datos.titulo())
                        .orElseGet(() -> {

                            Book newBook = new Book(datos);
                            return  repository.save(newBook);

                        });




    }

    public void listBooks(){
        books = repository.findAll();
        books.forEach(System.out::println);
    }


    public void listBooksByLanguage(){

            var languageText = """
                    Choose the option with the language you want to list: 
                    
            
                    (1) english
                    (2) espanish
                    (3) french
                    
                        
                    """;


        System.out.println(languageText);

        var option = keyboard.nextInt();
        keyboard.nextLine();

        var language = "";

        switch (option){
            case 1:
                language = "en";
                break;

            case 2:
                language = "es";
                break;

            case 3:
                language = "fr";
                break;

            default:
                System.out.println("Opción inválida");


        }

        books = repository.findByIdiomas(language);
        books.forEach(System.out::println);

        System.out.println("Total of books: " + books.size());



    }

    public void listAuthors(){

        books = repository.findAll();


        books.stream()
                .map(a -> a.getAutor())
                .forEach(System.out::println);




    }


    public void listAuthorAliveInAYear(){

        System.out.println("Enter the year");

        var year= keyboard.nextInt();

        List<Author> autores = authorRepository.autoresVivosEnUnAño(year);

        autores.forEach(System.out::println);

    }


}
