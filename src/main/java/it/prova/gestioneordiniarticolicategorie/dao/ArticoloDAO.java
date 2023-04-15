package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo>{

	public Articolo caricaArticolo (Long idArticolo) throws Exception;

	public void deleteCompletoArticolo(Long idArticolo);

	public Articolo caricaArticoloEager(Long idArticolo);

	public Double sumPrezzoArticoliDiUnaCategoria(Long idCategoria);
	
	public Double sumPrezzoArticoliDiUnDestinatario (String nomeDestinatario) throws Exception;

	public List<Articolo> listaArticoliConErroriInOrdine();
}
