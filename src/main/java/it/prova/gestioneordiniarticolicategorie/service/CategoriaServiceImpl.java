package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.dao.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categoriaDAO;
	private EntityManager entityManager;

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	public List<Categoria> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			// injection
			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			// injection
			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			entityManager.getTransaction().begin();

			// injection
			categoriaDAO.setEntityManager(entityManager);

			categoriaDAO.update(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			entityManager.getTransaction().begin();

			// injection
			categoriaDAO.setEntityManager(entityManager);

			categoriaDAO.insert(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idCategoria) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			entityManager.getTransaction().begin();

			// injection
			categoriaDAO.setEntityManager(entityManager);

			categoriaDAO.delete(categoriaDAO.get(idCategoria));

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void aggiungiArticolo(Categoria categoriaInstance, Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			entityManager.getTransaction().begin();

			// injection
			categoriaDAO.setEntityManager(entityManager);

			categoriaInstance = entityManager.merge(categoriaInstance);

			articoloInstance = entityManager.merge(articoloInstance);

			articoloInstance.addToCategorie(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Categoria caricaCategoriaEager(Long idCategoria) {

		TypedQuery<Categoria> query = entityManager
				.createQuery("from Categoria c join fetch c.articoli a where c.id = ?1", Categoria.class);
		query.setParameter(1, idCategoria);
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public void rimozioneCompletaCategoria(Long idCategoria) {
		entityManager.createNativeQuery("delete from articolo_categoria a where a.categoria_id = ?1")
				.setParameter(1, idCategoria).executeUpdate();
		entityManager.createNativeQuery("delete from categoria c where c.id = ?1").setParameter(1, idCategoria)
				.executeUpdate();

	}

	@Override
	public List<String> listaCodiciCategoriaDiUnMese(int annoDaCercare, int meseDaCercare) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {

			// injection
			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.listCodiciCategoriaDuranteUnMese(annoDaCercare,meseDaCercare);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
