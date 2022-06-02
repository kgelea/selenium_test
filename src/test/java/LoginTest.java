import org.junit.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class LoginTest{
	
	private WebDriver driver;
	private final boolean delays = true; // set to false to skip waiting or true to view the browser during test
	
	@Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginGetFormDownloadLogoutTest() throws InterruptedException {
		
		// load home page and get title
		HomePage homePage = new HomePage(this.driver);
		homePage.loadHomePage();
		String homePageTitle = homePage.nav.getTitle();
		Assert.assertEquals("arXiv.org e-Print archive", homePageTitle);
		
		// go to the login page, enter username and password and log in
		if(delays){TimeUnit.SECONDS.sleep(1);}
		LoginPage loginPage = homePage.gotoLogin();
		Assert.assertEquals("", loginPage.errors); // make sure we could read password and login didn't time out
		assertTrue(loginPage.nav.getBodyText().contains("Log in to arXiv.org"));
		LandingPage landingPage = loginPage.login(delays);
		if(delays){TimeUnit.SECONDS.sleep(1);}
		assertTrue(landingPage.nav.getBodyText().contains("Your arXiv.org account: kgele"));
		
		// go back to the homepage, select the computer science topics
		homePage = landingPage.gotoHome();
		if(delays){TimeUnit.SECONDS.sleep(1);}
		SearchPage searchPage = homePage.gotoCSSearchPage();
		assertTrue(searchPage.nav.getBodyText().contains("Searching in archive"));
		if(delays){TimeUnit.SECONDS.sleep(1);}
		
		//search for Convolutional Neural Networks
		searchPage.search("cnn");
		if(delays){TimeUnit.SECONDS.sleep(1);}
		PDFDownloaderPage pdfPage;
		if(delays){TimeUnit.SECONDS.sleep(1);}
		
		//Now we go to the top n (in this case 3) results and click on the "other" link to download the pdf source.
		for (int i=0; i<3; i++){
			
			// sort search results by relevance rather than freshness
			searchPage.sortByRelevanceAndReSearch();
			assertTrue(searchPage.nav.getBodyText().contains("Sort results by"));
			if(delays){TimeUnit.SECONDS.sleep(1);}
			
			// go to the page of the individual result to download its PDFs source
			pdfPage = searchPage.gotoPDFDownloader(i);
			assertTrue(pdfPage.nav.getBodyText().contains("Note: Many of the formats above are served gzipped"));
			if(delays){TimeUnit.SECONDS.sleep(1);}
			pdfPage.downloadPDF();
			if(delays){TimeUnit.SECONDS.sleep(5);} // longer wait for download
			
			// go back to the cnn search results before we sort by relevance
			driver.navigate().back();
			driver.navigate().back();
			if(delays){TimeUnit.SECONDS.sleep(1);}
		}
		
		//logout
		homePage.loadHomePage();
		homePage.logout();
		if(delays){TimeUnit.SECONDS.sleep(1);}
    }
	
	@Test
	public void staticPageTest() throws InterruptedException{
		
		// a list of sites to test. contains structs that contain the url of the website to test and the text we ecpect to find once it successfully loaded
		ArrayList<PageTest> testList = new ArrayList<PageTest>();
		
		testList.add(new PageTest("https://arxiv.org/", "arXiv is a free distribution service and an open-access archive"));
		testList.add(new PageTest("https://arxiv.org/help/robots", "Indiscriminate automated downloads from this site are not permitted"));
		testList.add(new PageTest("https://arxiv.org/corr", "Welcome to the Computing Research Repository (CoRR)"));
		
		PageTester pageTester = new PageTester(driver);
		
		for(int i=0; i<testList.size(); i++){
			pageTester.testPage(testList.get(i));
			if(delays){TimeUnit.SECONDS.sleep(1);}
		}
	}
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}