package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface ArticoloService {

	public void setArticoloDAO(ArticoloDAO articoloDAOInstance);

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public 	void rimuovi(Long idArticolo) throws Exception;

	public	List<Articolo> listAll() throws Exception;

	public void aggiungiCategoria(Articolo articoloInstance, Categoria categoriaInstance);

	public Articolo caricaArticoloEager(Long id);

	public void rimozioneArticoloCompleta(Long id) throws Exception;

	public Double sommaPrezzoArticoliDiUnaCategoria(Long idNuovaCategoria);

	public Double sommaPrezzoArticoliDiUnDestinatario (String nomeDestinatario) throws Exception;


	

}
