package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Produto;

public class ProdutoDao {
	

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("estoque");

    public void salvar(Produto produto) {
    	
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try {
        	
            entityManager.getTransaction().begin();
            entityManager.persist(produto);
            entityManager.getTransaction().commit();
            
        } catch (Exception e) {
        	
            entityManager.getTransaction().rollback(); 
            
        } finally {
        	
            entityManager.close();
        }
    }

}
