package com.quote.generator.repository;

import com.quote.generator.model.QuoteDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ufhopla
 * on 10/08/2021.
 */
public interface QuoteRepository extends JpaRepository<QuoteDao, Long> {

   @Query(value = "SELECT * FROM quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
   public List<QuoteDao> findRandomQuotes();
}
