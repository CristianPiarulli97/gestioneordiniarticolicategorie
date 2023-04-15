package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public interface ArticoloService {

	void setArticoloDAO(ArticoloDAO articoloDAOInstance);

	Articolo caricaSingoloElemento(Long id) throws Exception;

	void aggiorna(Articolo articoloInstance) throws Exception;

	void inserisciNuovo(Articolo articoloInstance) throws Exception;

	void rimuovi(Long idArticolo) throws Exception;

	List<Articolo> listAll() throws Exception;

	

}
