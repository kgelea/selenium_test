import java.util.ArrayList;

// simple struct to put into the list of items to test. See PageTester for more.
class PageTest{
	public String pageURL;
	public String bodyContains;
	
	public PageTest(String url, String body){
		pageURL = url;
		bodyContains = body;
	}
}