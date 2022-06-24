package com.bnpp.katas.developmentbooks.repo;

import com.bnpp.katas.developmentbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
