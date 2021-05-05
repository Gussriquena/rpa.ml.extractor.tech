package tech.talents.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import tech.talents.model.Product;

public class Main {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		startRobot();
	}
	
	private static void startRobot() {
		WebDriver driver = new ChromeDriver(getChromeOptions());
		
		List<Product> products = new ArrayList<>();
		products.add(new Product("Celular", "", ""));
		products.add(new Product("Notebook", "", ""));
		products.add(new Product("Tv", "", ""));
		
		
		for (Product product : products) {
			driver.get("https://lista.mercadolivre.com.br/" + product.getNome());
			
			// Resgatar valor 
			List<WebElement> linksProdutos = driver.findElements(By.xpath("//li[@class='ui-search-layout__item']//a[1][@class='ui-search-link']"));
			List<String> urlProdutos = new ArrayList<>();
			
			// Extraindo url dos elementos
			for (WebElement webElement : linksProdutos) {
				urlProdutos.add(webElement.getAttribute("href"));
			}
			
			// acessando cada String URL
			for (String currentUrl : urlProdutos) {
				driver.get(currentUrl);
				
				// Extraindo texto do elemento
				String nomeProduto = driver.findElement(By.xpath("//h1[@class='ui-pdp-title']")).getText();
				String precoProduto = driver.findElement(By.xpath("(//div[@class='ui-pdp-price__second-line']/span/span[@class='price-tag-fraction'])[1]")).getText();
				
				System.out.println(nomeProduto + " - " + precoProduto);
			}
		}
		
		driver.close();
		driver.quit();
		
	}
	
	
	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
		
		// 0 = default, 1 = Allow, 2 = Block
		chromePreferences.put("profile.managed_default_content_settings.javascript", 0);
		chromePreferences.put("profile.managed_default_content_settings.images", 2);
		chromePreferences.put("profile.managed_default_content_settings.stylesheets", 0);
		chromePreferences.put("profile.managed_default_content_settings.geolocation", 2);
		chromePreferences.put("profile.managed_default_content_settings.media_stream", 2);
		chromePreferences.put("profile.managed_default_content_settings.cookies", 2);
		chromePreferences.put("profile.managed_default_content_settings.popups", 2);
		chromePreferences.put("profile.managed_default_content_settings.notifications", 2);
		chromePreferences.put("profile.managed_default_content_settings.plugins", 2);
		chromePreferences.put("download.default_directory", "\\downloads\\pastarobo");
		chromePreferences.put("profile.default_content_setting_values.automatic_downloads", "1");
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-infobars");
		options.setExperimentalOption("prefs", chromePreferences);
		
		return options;
	}
	
	
}
