package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface CategoriaService {

	public void setCategoriaDAO(CategoriaDAO categoriaDAOInstance);

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public List<Categoria> listAll() throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Long idCategoria) throws Exception;

	public void aggiungiArticolo(Categoria categoriaInstance, Articolo articoloInstance) throws Exception;

	public Categoria caricaCategoriaEager(Long id);

	public void rimozioneCompletaCategoria(Long id);

	public List<String> listaCodiciCategoriaDiUnMese(int annoDaCercare, int meseDaCercare) throws Exception;

	

}
