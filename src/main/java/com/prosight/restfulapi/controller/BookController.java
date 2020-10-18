package com.prosight.restfulapi.controller;

import com.prosight.restfulapi.entity.Book;
import com.prosight.restfulapi.exception.InvalidRequestException;
import com.prosight.restfulapi.exception.ResourceNotFoundException;
import com.prosight.restfulapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Restful 风格 API
 *
 * <p>
 *
 * <ul>
 *   <li>GET /api/v1/books 所有书单
 *   <li>GET /api/v1/books/{id} 获取一条书单
 *   <li>POST /api/v1/books 创建一条书单
 *   <li>PUT /api/v1/books/{id} 更新一条书单，提供全部信息
 *   <li>PATCH /api/v1/books{id} 更新一条书单，提供部分信息
 *   <li>DELETE /api/v1/books/{id} 删除一条书单
 *   <li>DELETE /api/v1/books 删除所有书单
 * </ul>
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-16 13:40
 */
@RestController
@RequestMapping("/v1")
public class BookController {
  private final BookRepository bookRepository;

  @Autowired
  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  /**
   * 获取所有书单 GET /api/v1/books 所有书单
   *
   * <p>
   *
   * @return HTTP 响应
   *     <p>curl http://localhost:8080/api/v1/books
   */
  @GetMapping("/books")
  public HttpEntity<?> books() {
    return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
  }

  /**
   * 获得一个书单 GET /api/v1/books{id} 获得一个书单
   *
   * @param id 书单id
   * @return http 响应
   *     <p>curl http://localhost:8080/api/v1/books/1
   */
  @GetMapping("/books/{id}")
  public HttpEntity<?> booksOne(@PathVariable Long id) {
    return new ResponseEntity<>(
        bookRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException(String.format("Book by id %s not found", id))),
        HttpStatus.OK);
  }

  /**
   * 添加一个书单 POST /api/v1/books 创建一个书单
   *
   * @param book 书单
   * @return http 响应
   *     <p>curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"XBox
   *     360\",\"author\":\"Elton\",\"description\":\"test\",\"status\":1}"
   *     http://localhost:8080/api/v1/books
   */
  @PostMapping("/books")
  public HttpEntity<?> booksAdd(@Valid @RequestBody Book book, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      throw new InvalidRequestException("Invalid parameters", bindingResult);
    }
    return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
  }

  /**
   * 更新一个书单，提供一个书单的全部信息 PUT /api/v1/books/{id} 更新一个书单，提供全部信息
   *
   * @param id 更新的id
   * @param newBook 更新的书单
   * @return http 响应
   *     <p>curl -X PUT -H "Content-Type: application/json" -d "{\"name\":\"PlayStation
   *     4\",\"author\":\"Elton\",\"description\":\"test\",\"status\":1}"
   *     http://localhost:8080/api/v1/books/25
   */
  @PutMapping("/books/{id}")
  public HttpEntity<?> booksPut(@PathVariable Long id, @Valid @RequestBody Book newBook) {
    return bookRepository
        .findById(id)
        .map(
            book -> {
              book.setName(newBook.name);
              book.setAuthor(newBook.author);
              book.setDescription(newBook.description);
              book.setStatus(newBook.status);
              return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
            })
        .orElseGet(
            () -> {
              newBook.setId(id);
              return new ResponseEntity<>(bookRepository.save(newBook), HttpStatus.OK);
            });
  }

  /**
   * 删除一个书单 DELETE /api/v1/books/{id} 删除一个书单
   *
   * @param id 书单id
   * @return http 响应
   *     <p>curl -X DELETE http://localhost:8080/api/v1/books/25
   */
  @DeleteMapping("/books/{id}")
  public HttpEntity<?> booksDelete(@PathVariable Long id) {
    bookRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
