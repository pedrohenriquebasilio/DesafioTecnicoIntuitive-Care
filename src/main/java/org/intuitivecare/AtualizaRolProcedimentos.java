package org.intuitivecare;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AtualizaRolProcedimentos {

    private WebDriver driver;
    public boolean configuraRobo() {
        boolean config = false;
        try {
            String chromeDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();

            config = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    public  void EntraNoSite(){
        driver.get("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos");
        if(foiEncontradoElemento(driver,By.xpath("/html/body/div[5]/div/div/div/div/div[2]/button[3]"))){
            System.out.println("achei");
        } else {
            System.out.println("não achei");
        }
    }


    public static boolean foiEncontradoElemento(WebDriver driver, By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
