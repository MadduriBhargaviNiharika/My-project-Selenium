package testNg;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.Loginpage;


public class LoginTest extends BaseTest {

    
    Loginpage lpage;

    @BeforeClass
    public void lauchBrowser() {
        launchBrowser("Chrome", "https://www.saucedemo.com/");
        lpage = new Loginpage(driver);
    }

	@Test
	@Parameters({ "username" , "password" })
	public void SigninApplication(String username, String password)
	{
		
		lpage.signOn(username, password);

	}

}
