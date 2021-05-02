package com.example.Pr17.dao;

import com.example.Pr17.models.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class BankDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        this.session = sessionFactory.openSession();
    }

    public void save(Bank bank) {
        session.saveOrUpdate(bank);
    }

    public void delete(Long id) {
        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from Bank where id=:id")
                .setParameter("id", id)
                .executeUpdate();

        transaction.commit();
    }

    public List<Bank> findBanksById(Long id) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
        Root<Bank> from = query.from(Bank.class);
        query.select(from).where(from.get("id").in(id));

        return session.createQuery(query).getResultList();
    }

    public List<Bank> findBanksByName(String name) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
        Root<Bank> from = query.from(Bank.class);
        query.select(from).where(from.get("name").in(name));

        return session.createQuery(query).getResultList();
    }

    public List<Bank> findBanksByAddress(String address) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Bank> query = criteriaBuilder.createQuery(Bank.class);
        Root<Bank> from = query.from(Bank.class);
        query.select(from).where(from.get("address").in(address));

        return session.createQuery(query).getResultList();
    }

    public List<Bank> getAllBanks() {
        return session.createQuery("from Bank", Bank.class)
                .getResultList();
    }

}
