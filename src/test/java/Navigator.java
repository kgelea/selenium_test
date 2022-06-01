import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
//import Navigator.*;

public class Navigator{
	
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public Navigator(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	//public void findAndClick(By locator){
	//	this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    //    WebElement element = this.driver.findElement(locator);
	//	element.click();
	//}
	
	public void findXpathAndClick(String path){
			By locator = By.xpath(path);
			this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			WebElement element = this.driver.findElement(locator);
			element.click();
	}
	
	public void findXPathAndType(String path, String keys){
		By locator = By.xpath(path);
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		WebElement element = this.driver.findElement(locator);
		element.sendKeys(keys);
	}

}