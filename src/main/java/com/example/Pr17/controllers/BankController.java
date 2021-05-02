package com.example.Pr17.controllers;

import com.example.Pr17.dao.BankDao;
import com.example.Pr17.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankDao bankDao;

    @GetMapping
    public List<Bank> getAll() {
        return bankDao.getAllBanks();
    }

    @GetMapping("/filter/address/{address}")
    public List<Bank> getByAddress(@PathVariable("address") String address) {
        return bankDao.findBanksByAddress(address);
    }
    @GetMapping("/filter/id/{id}")
    public List<Bank> getById(@PathVariable("id") Long id) {
        return bankDao.findBanksById(id);
    }
    @GetMapping("/filter/name/{name}")
    public List<Bank> getByName(@PathVariable("name") String name) {
        return bankDao.findBanksByName(name);
    }

    @PostMapping
    public void save(@RequestBody Bank bank) {
        bankDao.save(bank);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bankDao.delete(id);
    }

}
