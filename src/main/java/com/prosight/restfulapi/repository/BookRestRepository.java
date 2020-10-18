package com.prosight.restfulapi.repository;

import com.prosight.restfulapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Spring Data Rest 资源类，在application.yml中配置了v2路径
 * @RestController 完全自定义控制器，完全交由自己处理
 * @RepositoryRestResource 完全使用已设置的Spring Data REST配置，不需要自定义控制
 * @RepositoryRestController 希望使用已设置的Spring Data REST配置，但是部分需要自定义
 *
 * @author elton
 * @version 1.0
 * @date 2020-10-18 16:42
 */
@RepositoryRestResource(path = "books", collectionResourceRel = "books")
public interface BookRestRepository extends JpaRepository<Book, Long> {
    /**
     * 自己定义删除的方法
     * @param aLong 删除的id
     */
    @Modifying
    @Query("UPDATE Book SET status = false WHERE id=:id")
    void delete(@Param("id") Long aLong);

    /**
     * 重写删除方法
     *
     * @param book 删除的实体
     */
    @Override
    default void delete(Book book) {
        delete(book.getId());
    }

    /**
     * 通过作者查找
     *
     * @param author 作者
     * @return 书单
     */
    @RestResource(path = "authors", rel = "authors")
    Book findBookByAuthor(@Param("author") String author);
}
