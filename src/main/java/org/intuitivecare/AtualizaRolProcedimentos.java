package org.intuitivecare;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AtualizaRolProcedimentos {

    private WebDriver driver;
    private final String downloadPath = System.getProperty("user.dir") + "\\src\\main\\resources\\anexos";

    public boolean configuraRobo() {
        try {
            String chromeDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-extensions", "--disable-notifications",
                    "--no-sandbox", "--disable-gpu", "--disable-dev-shm-usage");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadPath);
            prefs.put("download.prompt_for_download", false);
            prefs.put("plugins.always_open_pdf_externally", true);
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void entraNoSite() {
        driver.get("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos");

        // Aceitar cookies se o botão existir
        clicarSePresente(By.cssSelector(".btn-accept"));

        // Baixar os arquivos
        boolean foiBaixado1 = baixarArquivo("f710899c6c7a485ea62a1acc75d86c8c");
        boolean foiBaixado2 = baixarArquivo("aee04d07eec1412e8121f50c277d72b9");

        if (foiBaixado1 && foiBaixado2) {
            System.out.println("Anexos baixados corretamente.");
            System.out.println("Iniciando compressão em ZIP...");
            comprimirArquivosEmZip();
        } else {
            System.out.println("Erro ao baixar os anexos.");
        }
    }

    private boolean baixarArquivo(String fileUid) {
        Optional<WebElement> anexo = encontrarElemento(By.cssSelector("a[data-mce-href='resolveuid/" + fileUid + "']"));
        anexo.ifPresent(WebElement::click);
        return anexo.isPresent();
    }

    private void clicarSePresente(By by) {
        encontrarElemento(by).ifPresent(WebElement::click);
    }

    private Optional<WebElement> encontrarElemento(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return Optional.ofNullable(wait.until(ExpectedConditions.presenceOfElementLocated(by)));
        } catch (TimeoutException e) {
            return Optional.empty();
        }
    }

    public void comprimirArquivosEmZip() {
        String zipFilePath = downloadPath + "\\anexos_comprimidos.zip";

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            File folder = new File(downloadPath);
            File[] files = folder.listFiles();

            if (files == null || files.length == 0) {
                System.out.println("Nenhum arquivo encontrado para compactação.");
                return;
            }

            for (File file : files) {
                if (file.isFile()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zipOut.putNextEntry(zipEntry);
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            zipOut.write(buffer, 0, bytesRead);
                        }
                        zipOut.closeEntry();
                        System.out.println("Arquivo adicionado ao ZIP: " + file.getName());
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
