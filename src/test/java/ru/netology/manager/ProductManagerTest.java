package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product book1 = new Book(1, "Книга 'Бессонница'", 1000, "Стивен Кинг");
    Product book2 = new Book(2, "Книга 'Сказки'", 500, "Александр Сергеевич Пушкин");
    Product smartphone1 = new Smartphone(3, "Смартфон Samsung Galaxy A50", 30000, "Samsung");
    Product smartphone2 = new Smartphone(4, "Смартфон iPhone 8", 25000, "Apple");

    @Test
    public void shouldAddWhenEmpty() {

        manager.add(book1);

        Product[] expected = new Product[]{book1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldAddWhenNotEmpty() {

        manager.add(book1);
        manager.add(smartphone1);

        Product[] expected = new Product[]{book1, smartphone1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenOneMatch() {

        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);

        String text = "Сказки";
        Product[] expected = new Product[]{book2};
        Product[] actual = manager.searchBy(text);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenTwoMatch() {

        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);

        String text = "Смартфон";
        Product[] expected = new Product[]{smartphone1, smartphone2};
        Product[] actual = manager.searchBy(text);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenNoMatch() {

        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);

        String text = "Футболка";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(text);

        assertArrayEquals(expected, actual);
    }
}
