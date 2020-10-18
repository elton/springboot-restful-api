package com.prosight.restfulapi.repository;

import com.prosight.restfulapi.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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
public interface BookRestRepository extends PagingAndSortingRepository<Book, Long> {}
