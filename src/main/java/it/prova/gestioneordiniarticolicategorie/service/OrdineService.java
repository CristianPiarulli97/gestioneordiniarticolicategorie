package it.prova.gestioneordiniarticolicategorie.service;

import it.prova.gestioneordiniarticolicategorie.dao.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineService {

	void setOrdineDAO(OrdineDAO ordineDAO);

	void setArticoloDAO(ArticoloDAO articoloDAO);

	void inserisciNuovo(Ordine ordineInstance) throws Exception;

}
