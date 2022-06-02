import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.*;

// base class for all webpages
public class PageBase{	
	
	public WebDriver driver;
	public WebDriverWait wait;
	public Navigator nav;
	
	public PageBase(WebDriver d){
		
		driver = d;
		wait = new WebDriverWait(driver, 20);
		nav = new Navigator(driver, wait);
	}
}