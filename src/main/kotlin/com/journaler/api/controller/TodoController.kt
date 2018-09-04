package com.journaler.api.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import com.journaler.api.data.Todo
import com.journaler.api.service.TodoService


@RestController
@RequestMapping("/todos")
class TodoController {
    @Autowired
    private lateinit var service: TodoService
    /**
     * Get todos.
     */
    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodos(): List<Todo> = service.getTodos()

    /**
     * Insert item.
     * It consumes JSON, that is: request body Todo.
     */
    @PutMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertTodo(
            @RequestBody todo: Todo
    ): Todo = service.insertTodo(todo)

    /**
     * Remove item by Id.
     * We introduced path variable for Id to pass.
     */
    @DeleteMapping(
            value = "/{id}",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteTodo(
            @PathVariable(name = "id") id: String
    ): Boolean = service.deleteTodo(id)

    /**
     * Update item.
     * It consumes JSON, that is: request body Todo.
     * As result it returns boolean. True == success.
     */
    @PostMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateTodo(@RequestBody todo: Todo): Boolean = service.updateTodo(todo)
}