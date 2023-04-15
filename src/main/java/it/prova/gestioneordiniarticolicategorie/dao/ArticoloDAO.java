package it.prova.gestioneordiniarticolicategorie.dao;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo>{

	public Articolo caricaArticolo (Long idArticolo) throws Exception;

	public void deleteCompletoArticolo(Long idArticolo);

	public Articolo caricaArticoloEager(Long idArticolo);
	
}
