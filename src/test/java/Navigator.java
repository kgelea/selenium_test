import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Navigator{
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public Navigator(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	public WebElement getElementFromXPath(String xpath){
		By locator = By.xpath(xpath);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return this.driver.findElement(locator);
	}
	
	public void findXpathAndClick(String path){
			WebElement element = getElementFromXPath(path);
			element.click();
	}
	
	public void findXPathAndType(String path, String keys){
		WebElement element = getElementFromXPath(path);
		element.sendKeys(keys);
	}
	
	public String getBodyText(){
		By locator = By.tagName("body");
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return this.driver.findElement(locator).getText();
	}
	
	public void findXpathAndSelect(String path, String selection){
		Select dropdown= new Select(getElementFromXPath(path));
		dropdown.selectByVisibleText(selection);
	}
	
	public String getTitle(){
		return driver.getTitle();
	}

}