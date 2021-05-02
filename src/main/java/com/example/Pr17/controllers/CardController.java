package com.example.Pr17.controllers;

import com.example.Pr17.dao.CardDao;
import com.example.Pr17.models.Bank;
import com.example.Pr17.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardDao cardDao;

    @GetMapping
    public List<Card> getAll() {
        return cardDao.getAllCards();
    }

    @PostMapping
    public void save(@RequestBody Card card) {
        cardDao.save(card);
    }

    @GetMapping("/filter/number/{number}")
    public List<Card> getByCardNumber(@PathVariable("number") int number) {
        return cardDao.findCardsByCardNumber(number);
    }
    @GetMapping("/filter/id/{id}")
    public List<Card> getById(@PathVariable("id") Long id) {
        return cardDao.findCardsById(id);
    }
    @GetMapping("/filter/code/{code}")
    public List<Card> getByCode(@PathVariable("code") int code) {
        return cardDao.findCardsByCode(code);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        cardDao.delete(id);
    }

}
