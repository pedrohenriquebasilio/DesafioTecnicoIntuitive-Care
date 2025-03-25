package org.intuitivecare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    }


    public void AceitaCookies(){

    }
}
