package com.example.springbootgraphql;

import com.example.springbootgraphql.bookDetails.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

/**
 * The simplest way to start with Spring for GraphQL tests is through the GraphQLTester bean. We can use in mocked web environment.
 * You can also build tests for HTTP layer with another bean – HttpGraphQlTester. However, it requires us to provide an instance of WebTestClient.
 *
 * We need to annotate the whole test class with @AutoConfigureGraphQlTester.
 * Then we can use the DSL API provided by the GraphQLTester to get and verify data from backend.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class BookControllerTests {

    @Autowired
    private GraphQlTester tester;

    @Test
    void findBookById() {
        String query = "{ bookById(id: \"book-1\") { id name pageCount author { id firstName lastName } } }";
        Book book = tester.document(query)
                .execute()
                .path("data.bookById")
                .entity(Book.class)
                .get();
        System.out.println("bookResponse -->"+book);
        Assertions.assertNotNull(book);
        Assertions.assertNotNull(book.getId());
        Assertions.assertNotNull(book.getAuthorId());
    }

    @Test
    void findAuthByBookId() {
        String query = "{ bookById(id: \"book-1\") { id name pageCount author { id firstName lastName } } }";
        String authorId = tester.document(query)
                .execute()
                .path("data.bookById.author.id")
                .entity(String.class)
                .get();
        System.out.println("bookResponse authorId -->"+authorId);
        Assertions.assertNotNull(authorId);
    }

    @Test
    void findBookByName() {

        //`bookByName-query` is a GraphQL file present under /src/test/resources/graphql-test directory
        Book book = this.tester.documentName("bookByName-query")
                .variable("name", "Moby Dick")
                .execute()
                .path("data.bookByName")
                .entity(Book.class)
                .get();

        System.out.println("bookResponse -->"+book);
        Assertions.assertNotNull(book);
        Assertions.assertNotNull(book.getId());
    }
}
