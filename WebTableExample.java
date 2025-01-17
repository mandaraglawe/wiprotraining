/*
 https://www.dezlearn.com/webtable-example/

Basic Interactions:
1.	Row Count:
Write a Selenium script to count the total number of rows in the web table.
2.	Column Count:
Write a Selenium script to count the total number of columns in the web table.
3.	Retrieve Cell Value:
Write a Selenium script to retrieve the value of a specific cell (e.g., 3rd row and 2nd column).
4.	Fetch All Data:
Write a Selenium script to fetch and print all the data in the web table.
Conditional Search:
5.	Find a Row Based on a Value:
Write a Selenium script to find the row number where a specific value (e.g., "Mayur") exists.
6.	Retrieve Data from a Specific Column:
Write a Selenium script to extract all the data from a specific column (e.g., "Name" column).
7.	Validate Presence of a Value:
Write a Selenium script to verify if a specific value (e.g., "monica") exists in the web table.
Advanced Interactions:
10.	Click Link or Button in a Row:
Write a Selenium script to click a link or button in a specific row based on a condition (e.g., click the "Type" dropdown for the row where the name is "Alvin").
11.	Highlight Specific Rows:
Write a Selenium script to highlight all rows that satisfy a condition (e.g., Standard selected).
 


  
  
  */
package TestNGPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebTableExample {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.dezlearn.com/webtable-example/");
    }

    @Test(priority=1)
    public void rowCount() {
    	
    	List<WebElement> rows=driver.findElements(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr"));
        System.out.println("Total number of rows: " + rows.size());
        Assert.assertTrue(rows.size() > 0, "Row count should be greater than 0");
    }

    @Test(priority=2)
    public void columnCount() {
        List<WebElement> columns = driver.findElements(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr[1]/th"));
        System.out.println("Total number of columns: " + columns.size());
        Assert.assertTrue(columns.size() > 0, "Column count should be greater than 0");
    }

    @Test(priority=3)
    public void retrieveCellValue() {
        WebElement cellValue = driver.findElement(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr[3]/td[2]"));
        System.out.println("Value in 3rd row and 2nd column: " + cellValue.getText());
    }

    @Test(priority=4)
    public void fetchAllData() {
    	List<WebElement> rows=driver.findElements(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " ");
            }
            System.out.println();
        }
    }

    @Test(priority=5)
    public void findRowBasedOnValue() {
    	List<WebElement> rows=driver.findElements(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr"));
        int rowIndex = 0;
        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            if (row.getText().contains("Mayur")) {
                rowIndex = i + 1; // WebElements are zero-indexed, adding 1 for actual row number
                break;
            }
        }
        System.out.println("Row number where 'Mayur' exists: " + rowIndex);
        Assert.assertTrue(rowIndex > 0, "'Mayur' should be present in the table");
    }

    @Test(priority=6)
    public void retrieveDataFromSpecificColumn() {
    	List<WebElement> columnData=driver.findElements(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr[2]/td[1]"));
        //List<WebElement> columnData = driver.findElements(By.xpath("//table[@id='tblInfo']/tbody/tr/td[1]"));
        System.out.println("Data from 'Name' column:");
        for (WebElement data : columnData) {
            System.out.println(data.getText());
        }
    }

    @Test(priority=7)
    public void validatePresenceOfValue() {
    	List<WebElement> rows=driver.findElements(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr"));
        boolean valuePresent = false;
        for (WebElement row : rows) {
            if (row.getText().contains("monica")) {
                valuePresent = true;
                break;
            }
        }
        Assert.assertTrue(valuePresent, "'monica' should be present in the table");
    }

    @Test(priority=8)
    public void clickLinkOrButtonInRow() {
    	List<WebElement> rows=driver.findElements(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr"));
        for (WebElement row : rows) {
            if (row.getText().contains("Alvin")) {
                WebElement typeDropdown = row.findElement(By.xpath(".//select[@name='type']"));
                typeDropdown.click();
                break;
            }
        }
    }

    @Test(priority=9)
    public void highlightSpecificRows() {
    	List<WebElement> rows=driver.findElements(By.xpath("//*[@id=\"post-9373\"]/div/div/section/div/div/div/section/div/div/div/div/div/table/tbody/tr"));
        for (WebElement row : rows) {
            if (row.getText().contains("Standard")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'yellow'", row);
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
