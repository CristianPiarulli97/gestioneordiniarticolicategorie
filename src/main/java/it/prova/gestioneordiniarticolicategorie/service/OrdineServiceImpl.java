package it.prova.gestioneordiniarticolicategorie.service;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.dao.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class OrdineServiceImpl implements OrdineService{

	private OrdineDAO ordineDAO;
	private ArticoloDAO articoloDAO;
	
	@Override
	public void setOrdineDAO(OrdineDAO ordineDAO) {
		this.ordineDAO = ordineDAO;
		
	}

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAOInstance) {
		this.articoloDAO = articoloDAO;		
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			entityManager.getTransaction().begin();

			// injection
			ordineDAO.setEntityManager(entityManager);

			ordineDAO.insert(ordineInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

}
