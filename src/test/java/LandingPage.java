import org.junit.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

// simple page that pops up after login. Can navigate back to the homepage.
public class LandingPage extends PageBase{
	
	private final String homapageXPath = "//*[@id=\"header\"]/h1/a/img";
	
	public LandingPage(WebDriver driver){
		
		super(driver);
	}
	
	public HomePage gotoHome (){
		HomePage hp = new HomePage(this.driver);
		hp.loadHomePage();
		return hp;
	}

}