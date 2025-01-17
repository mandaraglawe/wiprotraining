package MiniProject;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Miniproject1 
{
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://omayo.blogspot.com/?m=0");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		String actualTitle = driver.getTitle();
		System.out.println("Actual Title: "+actualTitle);
		String expectedTitle = "omayo (QAFox.com)";
		System.out.println("Expected title: "+expectedTitle);
		
		if(actualTitle.equals(expectedTitle))
		{
			System.out.println("Title Verification Successful");
		}
		else
		{
			System.out.println("Title Verification Unsucessful");
		}
		
		//Used implicit wait for 10 secs.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		/*4. Handling Iframes:
        Locate the iframe on the page and switch to it.
        Perform an action (e.g., enter text in the input field within the iframe).
       */
		//Located the frame which contains the searchbox using xpath.
		//Switched to the frame and entered text in the textbox.
		WebElement Frame1 = driver.findElement(By.xpath("//iframe[@name='navbar-iframe']"));
		driver.switchTo().frame(Frame1);
		
		//Located searchBox using id locator and sent text
		WebElement searchBox = driver.findElement(By.id("b-query"));
		searchBox.sendKeys("Selenium");
		
		//Located searchbutton using id and clicked.
		WebElement searchButton = driver.findElement(By.id("b-query-icon"));
		searchButton.click();
		
		Thread.sleep(2000);
		
		
		//Switching to defaultwindow using defaultContent() method
		driver.switchTo().defaultContent();
		
		
	       
		//Count the number of <a> tags on the page and print their count.
		//Used list to store all the links with tag name "a" & stored the size in count variable.
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int count = links.size();
		System.out.println("Total number of <a> tags on page are: "+count);
		
		//Located Dropdown menu using Xpath and selected an option.
		Thread.sleep(2000);
		WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"drop1\"]"));
		Select s = new Select(dropdown);
		s.selectByContainsVisibleText("doc 3");
		//Used list to store all the selected options and used for-each loop to print the selected option using getText()
		List<WebElement> sel = s.getAllSelectedOptions();
		for(WebElement z: sel)
		{
			System.out.println("Selected option is: "+z.getText());
		}
		
		
		//Located textarea by CssSelector and entered text.
		WebElement textArea = driver.findElement(By.cssSelector("textarea#ta1"));
		textArea.sendKeys("Mandar Aglawe");
		String enteredTextArea = textArea.getAttribute("value");
		System.out.println("Entered Text is: "+enteredTextArea);
		Thread.sleep(2000);
		
		//Handling Basic Controls
		//1.Click the click here button and verify the result
		// Click here button is not present in AUT
		
		
		//2. Handling Input Box:
		//Enter text in the "Search this blog" input box
		
		//Located "Search this blog using xpath locator and entered text.
		
		WebElement searchThisBlog = driver.findElement(By.xpath("//input[@class = 'gsc-input']"));
		searchThisBlog.sendKeys("Java");
		String enteredText = searchThisBlog.getAttribute("value");
		System.out.println("Entered Text is: "+enteredText);
		
		//3. Handling Checkboxes:
		//Select the checkbox "Option 2."
		//Located checkbox by id locator and clicked it
		WebElement checkbox2 = driver.findElement(By.id("checkbox2"));
		checkbox2.click();
		String selectedOption = checkbox2.getAttribute("value");
		//To print the selected option in checkbox
		System.out.println("Selected checkbox is: "+selectedOption);
		
		//4. Handling Radio Buttons:
		//Select the radio button "Male."
		
		WebElement maleRadio = driver.findElement(By.xpath("//input[@value='male']"));
		maleRadio.click();
		String selectedRadioOption = maleRadio.getAttribute("value");
		System.out.println("Selected radio button is: "+selectedRadioOption);
		
		//5. Handling Select Box:
		//Used Select class to select option from the select box.
		Select s1 = new Select(dropdown);
		s1.selectByIndex(1);
		//Created list to store all the selected options and used getAllSelectedOptions() method
		List<WebElement> selected = s1.getAllSelectedOptions();
		for(WebElement x: selected)
		{
			System.out.println("Selected option is: "+x.getText());
		}
		
		/*Handling Advanced Controls
         1. Handling Alert Boxes:
         Trigger the alert by clicking "Try it" under the Alert section.
         Capture and verify the alert text, then accept the alert.*/
		
		WebElement alert = driver.findElement(By.id("alert1"));
		alert.click();
		String actualAlertText = driver.switchTo().alert().getText();
		System.out.println("Alert text is: "+actualAlertText);
		String expectedAlertText = "Hello";
		if(actualAlertText.contentEquals(expectedAlertText))
		{
			System.out.println("Alert text verification successful");
		}
		else
		{
			System.out.println("Alert text verifcation unsuccessful");
		}
		
		driver.switchTo().alert().accept();
	
		
		/*2. Handling Datepicker:
        Locate the date input box and set the date (e.g., "2025-01-15").
        */
		//There is no datepicker in the AUT.
		
		
		
				
		/*3. Handling Multiple Windows/Tabs:
		  Click the "Open a popup window" button.
		 Switch to the new window, verify its title, and close it.*/
		
		//Click on Open a Popup window by using class name
		//Used linked text as class name is not available
				
	    WebElement openPopup = driver.findElement(By.linkText("Open a popup window"));
	    openPopup.click();
	    Thread.sleep(3000);
		Set<String> a = driver.getWindowHandles(); //Stores all window handles
		//Used iterator to iterate through 2 windows and stored in variables parent_window & child_window
		Iterator<String> it = a.iterator();
	    String parent_window = it.next();
	    String child_window = it.next();
	    driver.switchTo().window(child_window);
	    //Verified title of the new window and closed it
	    String ActualnewWindowTitle = driver.getTitle();
	    System.out.println("Actual New Window Title: "+ActualnewWindowTitle);
	    String expectedNewWindowTitle = "New Window";
	    System.out.println("Expected New Window Title: "+expectedNewWindowTitle);
	               if(ActualnewWindowTitle.equals(expectedNewWindowTitle))
			       {
			    	   System.out.println("Title in new Window is correct");
			       }
			       else
			       {
			    	   System.out.println("Title in new window is incorrect");
			       }
			       driver.close();
			       Thread.sleep(1000);
			       driver.switchTo().window(parent_window);
			       
			       Thread.sleep(2000);
			       driver.close();
			       

		
	}
	
	

}
