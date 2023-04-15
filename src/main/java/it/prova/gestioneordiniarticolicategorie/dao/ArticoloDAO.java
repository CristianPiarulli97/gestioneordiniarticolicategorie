package it.prova.gestioneordiniarticolicategorie.dao;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo>{

	public Articolo caricaArticolo (Long idArticolo) throws Exception;
	
}
