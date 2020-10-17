package com.prosight.restfulapi.repository;

import com.prosight.restfulapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Book 资源
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-16 13:35
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
