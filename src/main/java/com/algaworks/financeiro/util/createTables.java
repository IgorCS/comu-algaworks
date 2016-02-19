package com.algaworks.financeiro.util;



import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class createTables {

	/**
	 * @param args
	 */

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("FinanceiroPU");
	}

}


