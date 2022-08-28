package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmozonExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("Webdriver.chrome.driver", "chromedriver");
		WebDriver driver = new ChromeDriver();

		//Launch Amazon.in
		driver.get("https://www.Amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
		
		//parent window handler
		String ParentWin = driver.getWindowHandle();
		//searching for Samsung
		WebElement SearchInput = driver.findElement(By.id("twotabsearchtextbox"));
		SearchInput.sendKeys("Samsung");
		
		//click search button
		WebElement SearchButton = driver.findElement(By.id("nav-search-submit-button"));
		SearchButton.click();
		
		//print product details and price
		List<WebElement> ProductDesc = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		System.out.println("Total num of links is " + ProductDesc.size());
		List<WebElement> ProductPrice = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
		for(int index=0;index<ProductDesc.size();index++)  {
			
		System.out.print(ProductDesc.get(index).getText() + "Price : Rupees ");
		System.out.println(ProductPrice.get(index).getText());
	}
		//click on 1st product link
		ProductDesc.get(0).click();
		String toValidate = ProductDesc.get(0).getText();
		System.out.println(toValidate);
		
		//validation on parent and child wimdow
		Set<String> allWinHan = driver.getWindowHandles();
		System.out.println("Before Clicking Tab button the win is " + ParentWin);
		
		for(String win:allWinHan)  {
			if(!win.equals(ParentWin)) {
				driver.switchTo().window(win);
			}
		}
		WebElement HeadingOnNewTab = driver.findElement(By.xpath("//div[@id='title_feature_div']//span"));
		String headerString = HeadingOnNewTab.getText();
		System.out.println(headerString);
		
		if(headerString.equals(toValidate))  {
			System.out.println("TC Passed");
		}else  {
			System.out.println("TC Failed");
		}
		
	}
}
