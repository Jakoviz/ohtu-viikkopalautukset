package ohtu;

import java.util.Random;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
	    System.out.println(driver.getPageSource());
        
//        WebElement element = driver.findElement(By.linkText("login"));
//        WebElement element = driver.findElement(By.linkText("register new user"));
//        element.click();

//        clickLinkWithText("login", driver);
        clickLinkWithText("register new user", driver);

        sleep(2);

	    System.out.println(driver.getPageSource());
        
        WebElement element;
        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
        element.sendKeys("uusiKayttaja"+new Random().nextInt(100000));

        element = driver.findElement(By.name("password"));
//        element.sendKeys("akkep");
//        element.sendKeys("vaaraSalasana");
        element.sendKeys("uusiSalasana");

        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("uusiSalasana");

//        element = driver.findElement(By.name("login"));
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();
    }
    
    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while( trials++<5 ) {
            try{
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;           
            } catch(Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
