package com.quote.generator.controller;

import com.quote.generator.model.QuoteDao;
import com.quote.generator.model.UserDao;
import com.quote.generator.repository.QuoteRepository;
import com.quote.generator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ufhopla
 * on 10/08/2021.
 */

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping("/users")
    public ResponseEntity<UserDao> getUserByUsername(Authentication authentication) {
        UserDao userData = userRepository.findByUsername(authentication.getName());

        if (userData != null) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<List<QuoteDao>> getQuoteByUsername(@PathVariable("username") String username) {
        UserDao userData = userRepository.findByUsername(username);
        List<QuoteDao> userQuote = userData.getQuoteDaos().stream().toList();

        if (userData != null) {
            return new ResponseEntity<>(userQuote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
