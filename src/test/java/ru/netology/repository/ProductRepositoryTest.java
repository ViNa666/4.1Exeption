package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();


    Product book1 = new Book(1, "Книга 'Бессонница'", 1000, "Стивен Кинг");
    Product book2 = new Book(2, "Книга 'Сказки'", 500, "Александр Сергеевич Пушкин");
    Product smartphone1 = new Smartphone(3, "Смартфон Samsung Galaxy A50", 30000, "Samsung");
    Product smartphone2 = new Smartphone(4, "Смартфон iPhone 8", 25000, "Apple");


    @Test
    public void shouldSaveOneItem() {

        repository.save(book1);

        Product[] expected = new Product[]{book1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);


    }

    @Test
    public void shouldSaveTwoDifferentItems() {

        repository.save(book1);
        repository.save(smartphone1);

        Product[] expected = new Product[]{book1, smartphone1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);


    }

    @Test
    public void shouldFindAllIfExistsOneProduct() {


        repository.save(book1);


        Product[] expected = new Product[]{book1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);


    }

    @Test
    public void shouldFindAllIfExistDifferentProducts() {


        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);


        Product[] expected = new Product[]{book1, book2, smartphone1, smartphone2};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldRemoveByIdIfExistsOneProduct() {


        repository.save(book1);
        int id = 1;

        Product[] expected = new Product[]{};
        Product[] actual = repository.removeById(id);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfExistSeveralProducts() {


        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);

        int id = 2;

        Product[] expected = new Product[]{book1, smartphone1, smartphone2};
        Product[] actual = repository.removeById(id);

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldRemoveByIdIfIdDoesNotExist() {


        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(smartphone2);

        int id = 5;


        assertThrows(NotFoundException.class, () -> repository.removeById(id));
    }
}


