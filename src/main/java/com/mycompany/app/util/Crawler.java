package com.mycompany.app.util;

import com.mycompany.app.model.TenderDetail;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mycompany.app.provider.TenderLinkProvider.getTenderLinks;
import static com.mycompany.app.provider.TenderLinkProvider.getTenderLinks_;
import static com.mycompany.app.util.CsvWriter.writeToCsv;

public class Crawler {
    private WebDriver driver = null;
    public void login() {
        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        try {
            // Navigate to Foody
            driver.get("https://www.tendernews.com/reg.aspx");

            // Wait a bit to load
            Thread.sleep(2000);

            // 🔐 Fill in login info
            WebElement emailField = driver.findElement(By.id("txtuserid"));
            WebElement passwordField = driver.findElement(By.id("txtpass"));
            WebElement loginButton = driver.findElement(By.id("btnlogin"));

            // TODO: Replace with your actual credentials
            emailField.sendKeys("marketing@savvycomsoftware.com");
            passwordField.sendKeys("5362658");

            // Click Login
            loginButton.click();

            // Wait for login to complete
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
//            driver.quit();
        }
    }
    public void excute(String link) {
        try {
            // ✅ Now you're logged in — navigate to data page
            driver.get(link);

            Thread.sleep(2000);

            List<TenderDetail> tenderDetails = new ArrayList<>();

            String country = driver.findElement(By.id("divcountry")).getText().trim();
            String purchaser = driver.findElement(By.id("divpurchaser")).getText().trim();
            String tenderDetail = driver.findElement(By.id("divtenderDetail")).getText().trim();
            String divPostingDate = driver.findElement(By.id("divPostingDate")).getText().trim();
            String bidDeadline = driver.findElement(By.id("divDeadline")).getText().trim();

            TenderDetail tender = new TenderDetail(country, purchaser, tenderDetail,
                    divPostingDate, bidDeadline);
            tenderDetails.add(tender);
            writeToCsv("src/main/resources/tenders.csv", tenderDetails);
            System.out.println("country " + country);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
//            driver.quit();
        }
    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.login();
        List links = getTenderLinks();
        for (Object link: links) {
            crawler.excute(link.toString());
        }
    }
}
