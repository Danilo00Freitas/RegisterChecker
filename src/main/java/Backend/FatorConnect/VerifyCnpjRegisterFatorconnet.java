package Backend.FatorConnect;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class VerifyCnpjRegisterFatorconnet {
    private WebDriver driver;
    private String chromedriverPath;

    public VerifyCnpjRegisterFatorconnet() {
        String currentDirectory = System.getProperty("user.dir");
        this.chromedriverPath = currentDirectory + File.separator + "chrome/chromedriver.exe"; //windows
        /*this.chromedriverPath = currentDirectory + File.separator + "chrome/chromedriver"; *///linux
        System.setProperty("webdriver.chrome.driver",chromedriverPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        this.driver = new ChromeDriver(chromeOptions);
    }
    public Boolean verifyCnpj(String cnpj) {

        try{
            driver.get("https://canaldigital.fatorconnect.com.br/cadastro/validar-dados");
            //Iniciando operação
            WebDriverWait wait = new WebDriverWait(driver, 2);
            Actions builder = new Actions(driver);

            WebElement campoCnpj = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-0")));
            builder.moveToElement(campoCnpj, 0, 0).click().build().perform();
            campoCnpj.sendKeys(cnpj);

            WebElement campoEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-1")));
            builder.moveToElement(campoEmail, 0, 0).click().build().perform();

            By selector1 = By.xpath("//*[contains(text(), 'Ir para Login')]");
            By selector2 = By.xpath("//*[contains(text(), 'Favor entrar em contato com seu gerente comercial na Fator Seguradora para atualização de dados cadastrais.')]");

            try {
                WebElement elemento1 = wait.until(ExpectedConditions.presenceOfElementLocated(selector1));
                driver.navigate().refresh();
                return true;
            } catch (Exception e) {
                try {
                    WebElement elemento2 = wait.until(ExpectedConditions.presenceOfElementLocated(selector2));
                    driver.navigate().refresh();
                    return true;
                } catch (Exception exception) {
                    driver.navigate().refresh();
                    return false;
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void closeChromeDriver(){
        driver.close();
    }
}
