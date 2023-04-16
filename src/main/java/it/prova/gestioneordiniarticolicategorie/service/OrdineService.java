package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineService {

	public void setOrdineDAO(OrdineDAO ordineDAO);

	public void setArticoloDAO(ArticoloDAO articoloDAO);

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public List<Ordine> listAll() throws Exception;

	public void aggiorna(Ordine ordineDaAggiornare) throws Exception;

	void rimuovi(Long idOrdine) throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	

	public List<Ordine> listaOrdiniPerCategoria(Long idCategoria) throws Exception;

	public List<String> indirizziConArticoliContenentiNumeroSeriale(String stringaNumeroSeriale);

	public Ordine ordinePiuRecentePerCategoria(Long idNuovaCategoria1);

}
