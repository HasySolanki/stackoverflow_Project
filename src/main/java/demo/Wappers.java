package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

public class Wappers {
    ChromeDriver driver;

    public void clickOnElement(ChromeDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(element));
            if (element != null) {
                element.click();
            }

        } catch (Exception e) {
            System.out.println("Element is not present in page " + e.getMessage());
        }

    }

    public void clickOnElementUsingJS(ChromeDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(element));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);

        } catch (Exception e) {
            System.out.println("Element is not present in page " + e.getMessage());
        }

    }

    public void SearchenterText(ChromeDriver driver, WebElement element, String value) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(element));

            if (element != null) {
                element.click();
                element.clear();
                element.sendKeys(value);
                element.sendKeys(Keys.ENTER);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Element is not present in page " + e.getMessage());

        }

    }

    public void enterText(ChromeDriver driver, WebElement element, String value) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(element));

            if (element != null) {
                element.click();
                element.clear();
                element.sendKeys(value);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Element is not present in page " + e.getMessage());

        }

    }

    public void printSearchResult(ChromeDriver driver, List<WebElement> elements, String value) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));

            if (elements.size() != 0) {
                for (WebElement element : elements) {

                    String text = element.getText();

                    if (text.contains("value")) {
                        System.out.println("Text data is relevant: " + text);
                    } else {
                        System.out.println("Text Data is NOT relevant: " + text);
                    }
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No Element found in search result " + e.getMessage());
        }

    }

    public void sortedValue(ChromeDriver driver, List<WebElement> elementesList) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(elementesList));
            List<Integer> listValue = new ArrayList<>();

            for (WebElement element : elementesList) {
                String text = element.getText();
                String numberOnly = text.replaceAll("[^0-9]", "");
              //  System.out.println(numberOnly);
             
                if (!numberOnly.isEmpty()) {
                    listValue.add(Integer.parseInt(numberOnly));
                  //  System.out.println("listValue:-" + listValue);
                }
            }
           // listValue.sort(Comparator.reverseOrder());
            // Collections.sort(listValue, Collections.reverseOrder());
            System.out.println("Value are in descending order. :- " + listValue);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Value are not in descending order. " + e.getMessage());
        }

    }

}
