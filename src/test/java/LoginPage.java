import org.junit.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;
import java.nio.file.*;
import java.io.*;

// page for logging in with functionality to do so. It reads password from file so I don't upload my private password to github with the source.
public class LoginPage extends PageBase{
	
	private final String usernameXPath = "//*[@id=\"username\"]";
	private final String passwordXPath = "//*[@id=\"password\"]";
	private final String submitLoginXPath = "//*[@id=\"main-container\"]/content/div/div/form/fieldset/div[3]/div/input";
	
	private final String username = "kgele";
	private final String passwordPath = "D:\\pwd\\password.txt";
	private String password;
	
	public String errors = "";
	
	public LoginPage(WebDriver driver){
		
		super(driver);
		
		Path path = Path.of(passwordPath);
		path = path.toAbsolutePath();
		
		try{
			this.password = Files.readString(path);
		}catch(IOException e){
			System.out.println("Couldn't find password file!");
			errors += "IOException ";
		}
		
	}
	
	public LandingPage login(boolean delay){
		
		try{
			nav.findXPathAndType(usernameXPath, username);
			if(delay){TimeUnit.SECONDS.sleep(1);}
			nav.findXPathAndType(passwordXPath, password); // getting password from file, for security reasons
			if(delay){TimeUnit.SECONDS.sleep(1);}
			nav.findXpathAndClick(submitLoginXPath);
			if(delay){TimeUnit.SECONDS.sleep(1);}
			return new LandingPage(this.driver);
		}catch(InterruptedException e){
			System.out.println("something went wrong while waiting!");
			errors += "InterruptedException";
			return null;
		}
	}

}