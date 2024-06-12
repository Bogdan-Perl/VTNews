package org.example.myproject;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * The ArticleScraper class handles scraping articles from the VT website,
 * recording relevant information, and summarizing the text. It provides
 * methods to record article elements, filter and store articles, and utilize
 * the OpenAI API for text summarization.
 */
public class ArticleScraper {
    private Map<Integer, Article> articles = new HashMap<>();
    private List<WebElement> titleElements, dateElements, tagElements;
    private List<String> articleTexts = new ArrayList<>();

    /**
     * Constructs an ArticleScraper object.
     */
    public ArticleScraper() {
        setupWebDriver();
    }

    /**
     * Sets up the WebDriver with necessary options.
     */
    private void setupWebDriver() {
        String chromeDriverPath = "home/don/chromedriver-linux64/chromedriver";
        System.out.println("ChromeDriver Path: " + chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Records the article information from the VT website.
     */
    public void recordArticleInformation() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Run Chrome in headless mode
        options.addArguments("--window-size=1920,1080");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://news.vt.edu/articles.html");

        titleElements = driver.findElements(By.cssSelector(".vt-list-title.vt-list-item-title.vt-c-card-title a"));
        dateElements = driver.findElements(By.cssSelector("span.vt-list-item-date > span.vt-list-item-date.vt-list-date-formatted.vt-list-item-date.d4"));
        tagElements = driver.findElements(By.cssSelector("span.vt-list-item-category-text"));
        recordEachArticleText(driver, 3);
    }

    /**
     * Records the text of each article for a given number of articles.
     *
     * @param driver           The WebDriver instance.
     * @param numberOfArticles The number of articles to record.
     */
    private void recordEachArticleText(WebDriver driver, int numberOfArticles) {
        if (numberOfArticles == -1) {
            numberOfArticles = titleElements.size();
        }

        for (int i = 0; i < numberOfArticles; i++) {
            WebElement title = titleElements.get(i);
            title.click();

            StringBuilder text = new StringBuilder();
            List<WebElement> textElements = driver.findElements(By.className("vt-text"));
            textElements.forEach(element -> text.append(element.getText()));

            articleTexts.add(text.toString());

            try {
                Thread.sleep(500); // Ensure the page is loaded
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.navigate().back();
            driver.navigate().refresh(); // Avoid StaleElementReferenceException

            // Reinitialize the lists after navigating back
            titleElements = driver.findElements(By.cssSelector(".vt-list-title.vt-list-item-title.vt-c-card-title a"));
            dateElements = driver.findElements(By.cssSelector("span.vt-list-item-date > span.vt-list-item-date.vt-list-date-formatted.vt-list-item-date.d4"));
            tagElements = driver.findElements(By.cssSelector("span.vt-list-item-category-text"));
        }
    }

    /**
     * Retrieves the recorded article texts.
     *
     * @return The list of article texts.
     */
    public List<String> getArticleTexts() {
        recordArticleInformation();
        return articleTexts;
    }

    /**
     * Converts a date string from a WebElement into an integer.
     *
     * @param dateElement The WebElement containing the date.
     * @return The integer representation of the date.
     */
    private int parseDate(WebElement dateElement) {
        return Integer.parseInt(dateElement.getText().trim().substring(4, dateElement.getText().indexOf(',')));
    }

    /**
     * Records and filters articles based on the specified date and tag.
     *
     * @param chosenDate The chosen date for filtering articles.
     * @param chosenTag  The chosen tag for filtering articles.
     */
    public void recordAndFilterArticles(int chosenDate, String chosenTag) {
        recordArticleInformation();
        for (int i = 0; i < titleElements.size(); i++) {
            String articleTitle = titleElements.get(i).getText().replace(", article", "").trim();
            int date = parseDate(dateElements.get(i));
            String tag = tagElements.get(i).getText().trim();
            String text = articleTexts.get(i);

            if (date == chosenDate && tag.equals(chosenTag)) {
                articles.put(date, new Article(articleTitle, date, tag, text));
                System.out.println(articleTitle + ", " + date + ", " + tag + ", " + text);
            }
        }
    }

    /**
     * Retrieves the filtered articles.
     *
     * @return A map of articles with dates as keys.
     */
    public Map<Integer, Article> getFilteredArticles() {
        return articles;
    }

}
