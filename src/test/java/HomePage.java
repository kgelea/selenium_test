import org.junit.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

// class for the homepage with navigation methods to search page and login page
public class HomePage extends PageBase{
	
	private final String loginXPath = "//*[@id=\"header\"]/div[1]/a";
	private final String dropdownXPath = "//*[@id=\"search-category\"]";
	private final String searchXPath = "//*[@id=\"adv-search-btn\"]";
	private final String logoutButtonXPath = "//*[@id=\"header\"]/div[1]/a[2]";
	
	public HomePage(WebDriver driver){
		
		super(driver);
	}
	
	public void loadHomePage(){
		this.driver.get("https://arxiv.org/");
	}
	
	public LoginPage gotoLogin(){
		this.nav.findXpathAndClick(loginXPath);
		return new LoginPage(this.driver);
	}
	
	public SearchPage gotoCSSearchPage(){
		nav.findXpathAndSelect(dropdownXPath, "Computer Science");
		nav.findXpathAndClick(searchXPath);
		return new SearchPage(this.driver);
	}
	
	public void logout(){
		nav.findXpathAndClick(logoutButtonXPath);
	}
}