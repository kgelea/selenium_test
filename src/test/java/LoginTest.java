import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;
import java.nio.file.*;
import java.io.*;

public class LoginTest{
	
	private WebDriver driver;
    private WebDriverWait wait;
	private Navigator nav;
	private final By bodyLocator = By.tagName("body");
	private final String loginXPath = "//*[@id=\"header\"]/div[1]/a";
	private final String usernameXPath = "//*[@id=\"username\"]";
	private final String passwordXPath = "//*[@id=\"password\"]";
	private final String submitLoginXPath = "//*[@id=\"main-container\"]/content/div/div/form/fieldset/div[3]/div/input";
	private final String username = "kgele";
	private final String passwordPath = "D:\\pwd\\password.txt";
	private String password;
	
	
	
	    //String filePath = "files/record.txt";
        //File file = new File(filePath);
        //String path = file.getPath();
	
	@Before
    public void setup() throws IOException {
		System.out.println("############################################# SETUP #############################################");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
		nav = new Navigator(driver, wait);
		
		
		Path path = Path.of(passwordPath);
		//Path path = Paths.get("./password.txt");
		path = path.toAbsolutePath();
		this.password = Files.readString(path);
		
		//System.out.println(this.password);
    }
	
	


    @Test
    public void loginGetFormLogoutTest() throws InterruptedException {
		System.out.println("############################################# TEST #############################################");
        this.driver.get("https://arxiv.org/");
		
		TimeUnit.SECONDS.sleep(2);
		nav.findXpathAndClick(loginXPath);
		TimeUnit.SECONDS.sleep(2);
		
		nav.findXPathAndType(usernameXPath, username);
		nav.findXPathAndType(passwordXPath, password);
		
		
		nav.findXpathAndClick(submitLoginXPath);
		TimeUnit.SECONDS.sleep(2);
		

    }
    
    @After
    public void close() {
		System.out.println("############################################# AFTER #############################################");
        if (driver != null) {
            driver.quit();
        }
    }
}



        //WebElement resultElement = waitVisibiiltyAndFindElement(bodyLocator);
        //System.out.println(resultElement.getText());
        //Assert.assertTrue(resultElement.getText().contains("2021 ELTE Faculty of Informatics"));
        
        //WebElement searchTogglerElement = waitVisibiiltyAndFindElement(requestPassword);
        //searchTogglerElement.click();
        //
        //WebElement searchBarElement = waitVisibiiltyAndFindElement(typePassword);
        //searchBarElement.sendKeys("HEBLE \n");
        //
        //WebElement bodyElement = waitVisibiiltyAndFindElement(bodyLocator);
        //System.out.println(bodyElement.getText());
        //Assert.assertTrue(bodyElement.getText().contains("A new password has been sent to HEBLE"));
            //private final By typePassword = By.id("account");
			//private final By loginButtonLocator = By.class("requestPassword");