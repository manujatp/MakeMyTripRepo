/*
 * 
 * @author manuja
 *
 */

//package declaration
package com.mytrip.qa.util;

//importing libraries
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Helper {
	
	public static int WAITTIME=30;
	
	//Click function
	public static void click(WebElement el){
			el.click();
	}
	
	//click() with explicit wait
	public static void clickByExplicitWait(WebElement el, WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, WAITTIME);
		wait.until(ExpectedConditions.elementToBeClickable(el));
		el.click();
	}
	
	//Set function
    public static void set(WebElement el,String str){
    	el.click();
    	el.clear();
    	el.sendKeys(str);
    	el.sendKeys(Keys.TAB);
    }
    
  //Set() with explicit wait
    public static void setByExplicitWait(WebElement el,WebDriver driver,String str){
    	WebDriverWait wait = new WebDriverWait(driver, WAITTIME);
		wait.until(ExpectedConditions.elementToBeClickable(el));
    	el.click();
    	el.clear();
    	el.sendKeys(str);
    	el.sendKeys(Keys.TAB);
    }
    
    
    //click function by JavascriptExecutor
    public static void clickElementByJs(WebElement el,WebDriver driver){
    	    			
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", el);
	
	}
    
  
       
}



