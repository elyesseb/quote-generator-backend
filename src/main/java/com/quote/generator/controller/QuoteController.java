package com.quote.generator.controller;

import com.quote.generator.model.QuoteDao;
import com.quote.generator.model.UserDao;
import com.quote.generator.repository.QuoteRepository;
import com.quote.generator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author ufhopla
 * on 10/08/2021.
 */

@CrossOrigin
@RestController
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/quotes")
    public ResponseEntity<List<QuoteDao>> getRandomQuotes() {
            List<QuoteDao> quoteDaos = quoteRepository.findRandomQuotes();
            return new ResponseEntity<>(quoteDaos, HttpStatus.OK);
    }

    @GetMapping("/quotes/{id}")
    public ResponseEntity<QuoteDao> getQuoteById(@PathVariable("id") long id) {
        Optional<QuoteDao> quoteData = quoteRepository.findById(id);

        if (quoteData.isPresent()) {
            return new ResponseEntity<>(quoteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/quotes/{username}")
    public ResponseEntity<UserDao> createQuote(@RequestBody QuoteDao quoteDao, Authentication authentication) {
        try {
            UserDao userDao = userRepository.findByUsername(authentication.getName());
            userDao.getQuoteDaos().add(quoteDao);

            UserDao newUserDao = userRepository.save(userDao);
            /*Quote _quote = quoteRepository
                    .save(new Quote(quote.getId(), quote.getText()));*/
            return new ResponseEntity<>(newUserDao, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<QuoteDao> updateQuote(@PathVariable("id") long id, @RequestBody QuoteDao quoteDao) {
        Optional<QuoteDao> quoteData = quoteRepository.findById(id);

        if (quoteData.isPresent()) {
            QuoteDao _quoteDao = quoteData.get();
            _quoteDao.setText(quoteDao.getText());
            return new ResponseEntity<>(quoteRepository.save(_quoteDao), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quotes/{id}")
    public ResponseEntity<HttpStatus> deleteQuote(@PathVariable("id") long id) {
        try {
            quoteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
