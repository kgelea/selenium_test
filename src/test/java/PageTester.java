import org.junit.*;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

// tests pages based on a struct calles PageTest that contains the url of the page to test
// and an expected phrase within the body of the given page to check it loaded correctly.
public class PageTester extends PageBase{
	
		public PageTester(WebDriver driver){super(driver);}
		
		public void testPage(PageTest test){
			this.driver.get(test.pageURL);
			String body = this.nav.getBodyText();
			boolean contains = body.contains(test.bodyContains);
			assertTrue(contains);
			System.out.println(test.pageURL + " contains " + test.bodyContains + " is " + String.valueOf(contains));
		}
}