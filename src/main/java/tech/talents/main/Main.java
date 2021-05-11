package tech.talents.main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import tech.talents.controller.MercadoLivreController;

public class Main {
	
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		PropertyConfigurator.configure("resources\\log4j.properties");
		
		log.info("iniciando automa��o");
		
		MercadoLivreController mercadoLivreController = new MercadoLivreController();
		mercadoLivreController.startFlow();
		
		log.error("SIMULA��O ERRO");
		log.warn("SIMULA��O WARNING");
		log.info("finalizando automa��o");
	}
	
}
