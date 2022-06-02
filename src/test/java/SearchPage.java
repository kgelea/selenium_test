import org.junit.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

// page for searching for articles and navigating search results. gotoPDFDownloader can navigate to the ith search result.
public class SearchPage extends PageBase{
	
	private final String searchBoxXPath = "//*[@id=\"query\"]";
	private final String sortButtonXPath = "//*[@id=\"main-container\"]/div[2]/div[1]/div/form/div[2]/div[3]/button";
	private final String resultDownloaderXPath1 = "//*[@id=\"main-container\"]/div[2]/ol/li[";
	private final String resultDownloaderXPath2 = "]/div/p/span/a[2]";
	private final String sortbyXPath = "//*[@id=\"order\"]";
	
	public SearchPage(WebDriver driver){
		
		super(driver);
		}
	
	public void search(String text){
		
		this.nav.findXPathAndType(searchBoxXPath, text+"\n");

	}

	public void search(){ // I just learned how to make default parameters in java...
		search("gan"); //generative adverserial networks
	}
	
	public void sortByRelevanceAndReSearch(){
		this.nav.findXpathAndSelect(sortbyXPath, "Relevance");
		this.nav.findXpathAndClick(sortButtonXPath);
	}
	
	public PDFDownloaderPage gotoPDFDownloader(int resultNumber) throws InterruptedException{
		resultNumber++; // indexing starts from 0 -> 1
		
		String fullXPath = resultDownloaderXPath1 + String.valueOf(resultNumber) + resultDownloaderXPath2;
		//*[@id="main-container"]/div[2]/ol/li[1]/div/p/span/a[2]
		//*[@id="main-container"]/div[2]/ol/li[2]/div/p/span/a[2]
		//*[@id="main-container"]/div[2]/ol/li[3]/div/p/span/a[2]
		
		System.out.println(fullXPath);
		TimeUnit.SECONDS.sleep(1);
		this.nav.findXpathAndClick(fullXPath);
		TimeUnit.SECONDS.sleep(1);
		return new PDFDownloaderPage(this.driver);
	}
}