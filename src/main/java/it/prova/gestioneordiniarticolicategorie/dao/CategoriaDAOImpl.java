package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO{

	
	private EntityManager entityManager;

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();

	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input.");
		}
		input = entityManager.merge(input);
		
	}

	@Override
	public void insert(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input.");
		}
		entityManager.persist(input);		
	}

	@Override
	public void delete(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input.");
		}
		entityManager.remove(entityManager.merge(input));		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

	public List<String> listCodiciCategoriaDuranteUnMese(int anno, int mese) throws Exception {
		TypedQuery<String> query = entityManager.createQuery(
				"select distinct c.codice from Categoria c join c.articoli a join a.ordine o where month(o.dataSpedizione) = :mese and year(o.dataSpedizione) = :anno",
				String.class).setParameter("mese", mese).setParameter("anno", anno);
		return query.getResultList();
	}
}
