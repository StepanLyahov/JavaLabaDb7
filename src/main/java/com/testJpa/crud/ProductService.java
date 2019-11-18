package com.testJpa.crud;

import com.testJpa.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductService {
    public EntityManager em = Persistence.createEntityManagerFactory("LABA").createEntityManager();

    public Product add(Product product){
        em.getTransaction().begin();
        Product productFromDB = em.merge(product);
        em.getTransaction().commit();
        return productFromDB;
    }

    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Product get(long id){
        return em.find(Product.class, id);
    }

    public void update(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public List<Product> getAll(){
        TypedQuery<Product> namedQuery = em.createNamedQuery("Product.getAll", Product.class);
        return namedQuery.getResultList();
    }

    public List<Product> filter(String str) {
        String main = "SELECT p from Productврпроапров p" + str;
        System.out.println(main);
        TypedQuery<Product> namedQuery = em.createQuery(main, Product.class);
        return namedQuery.getResultList();
    }
}
