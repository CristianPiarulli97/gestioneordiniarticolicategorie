package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO{

	private EntityManager entityManager;

	
	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();

	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);		
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));		
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	public List<Ordine> findAllByCategoria(Long idCategoria) throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery(
				"select o from Ordine o join o.articoli a join a.categorie c where c.id = ?1", Ordine.class);
		query.setParameter(1, idCategoria);
		return query.getResultList();
	}

	@Override
	public List<String> indirizziDegliOrdiniContenentiNumeroSeriale(String stringaNumeroSeriale) {
		TypedQuery<String> query = entityManager.createQuery(
				"select distinct o.indirizzoSpedizione from Ordine o join o.articoli a where a.numeroSeriale like ?1", String.class);
		query.setParameter(1,"%"+stringaNumeroSeriale+"%");
		return query.getResultList();
	
	}

	@Override
	public Ordine getRecentOrdineByCategoria(Long idCategoria) {
		TypedQuery<Ordine> query = entityManager.createQuery(
				"select o from Ordine o join o.articoli a join a.categorie c where c.id = ?1 order by o.dataSpedizione desc",Ordine.class);
				query.setParameter(1, idCategoria);
		return query.getResultStream().findFirst().orElse(null);
	
	}

}
