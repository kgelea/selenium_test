import org.junit.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;


// simple page with functionality for downloading the source of the given article and navigating back to the search page
public class PDFDownloaderPage extends PageBase{
	
	private final String downloadSourceXPath = "//*[@id=\"content\"]/dl/dd[2]/a";
	
	public PDFDownloaderPage(WebDriver driver){
		
		super(driver);
	}
	
	public void downloadPDF(){
		this.nav.findXpathAndClick(downloadSourceXPath);
		System.out.println("DOWNLOADING FILE!!!");
	}
	
}