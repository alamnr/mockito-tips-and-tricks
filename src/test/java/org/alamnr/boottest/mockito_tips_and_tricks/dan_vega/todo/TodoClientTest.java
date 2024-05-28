/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package org.alamnr.boottest.mockito_tips_and_tricks.dan_vega.todo;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Alam
 */
public class TodoClientTest {

    // System under test (sut)
    private TodoClient todoClient = new TodoClient();

    /**
     * Test of findAll method, of class TodoClient.
     */
    @Test
    void testFindAll() throws Exception {

        List<Todo> todos = todoClient.findAll();
        assertEquals(200, todos.size());
    }

    @Test
    void shouldReturnTodoGivenValidId() throws IOException, InterruptedException, TodoNotFoundException{
        Todo todo = todoClient.findById(1);
        assertEquals(1, todo.userId());
        assertEquals(1, todo.id());
        assertEquals("delectus aut autem", todo.title());
        assertFalse(todo.completed());
    }

    @Test
    void shouldThrowTodoNotFoundExceptionGivenInvalidId(){
        TodoNotFoundException todoNotFoundException = assertThrows(TodoNotFoundException.class, () -> todoClient.findById(999));
        assertEquals("Todo not Found", todoNotFoundException.getMessage());
    }

    @Test
    void shouldCreateNewTodo() throws IOException, InterruptedException{
        Todo todo = new Todo(2010, 2010,    "Learn Java", false);
        HttpResponse<String> response = todoClient.create(todo);
        assertEquals(201, response.statusCode());
    }

    @Test
    void shouldUpdateExistingTodo() throws IOException, InterruptedException{
        Todo todo = new Todo(1, 1, "New Title", true);
        HttpResponse<String> response = todoClient.update(todo);
        assertEquals(200, response.statusCode());
    }

    @Test
    void shouldDeleteExistingTodo() throws IOException, InterruptedException, TodoNotFoundException{
        Todo todo = todoClient.findById(1);
        HttpResponse<String> response = todoClient.delete(todo);
        assertEquals(200, response.statusCode());
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

}