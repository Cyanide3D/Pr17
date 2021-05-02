package com.example.Pr17.dao;

import com.example.Pr17.models.Bank;
import com.example.Pr17.models.Card;
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
public class CardDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        this.session = sessionFactory.openSession();
    }

    public void save(Card card) {
        session.saveOrUpdate(card);
    }

    public void delete(Long id) {
        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from Card where id=:id")
                .setParameter("id", id)
                .executeUpdate();

        transaction.commit();
    }

    public List<Card> findCardsById(Long id) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Card> query = criteriaBuilder.createQuery(Card.class);
        Root<Card> from = query.from(Card.class);
        query.select(from).where(from.get("id").in(id));

        return session.createQuery(query).getResultList();
    }

    public List<Card> findCardsByCardNumber(int cardNumber) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Card> query = criteriaBuilder.createQuery(Card.class);
        Root<Card> from = query.from(Card.class);
        query.select(from).where(from.get("cardNumber").in(cardNumber));

        return session.createQuery(query).getResultList();
    }

    public List<Card> findCardsByCode(int code) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Card> query = criteriaBuilder.createQuery(Card.class);
        Root<Card> from = query.from(Card.class);
        query.select(from).where(from.get("code").in(code));

        return session.createQuery(query).getResultList();
    }

    public List<Card> getAllCards() {
        return session.createQuery("from Card", Card.class)
                .getResultList();
    }

}
