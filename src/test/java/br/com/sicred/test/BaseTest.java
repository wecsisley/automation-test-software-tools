package br.com.sicred.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.sicred.builder.AddRecordBuilder;
import br.com.sicred.page.AddRecordPO;
import br.com.sicred.page.ListPO;
import br.com.sicred.util.ElementUtil;

/**Classe base para todos os testes do sistema. Ao criar uma nova classe de testes a mesma deve herdar desta classe.*/
public abstract class BaseTest {
    
    static ElementUtil elementUtil;
    static AddRecordPO addRecordPO;
    static ListPO listPO;
    static AddRecordBuilder addRecordBuilder;
    /**Driver utilizado pelo sistema.*/
    protected static WebDriver driver;
    /**URL Base para acesso à aplicação que será testada.*/
    private static final String URL_BASE = "https://www.grocerycrud.com/v1.x/demo/my_boss_is_in_a_hurry/bootstrap";
    /**Caminho onde está armazenado o driver executável do navegador.*/
    private static final String PATH_CHROME_DRIVER = "src/ressource/chromedriver-98.exe";
    /**Instancia da classe AddRecrdBuilder*/
    /**Instancia da classe AddRecordPO*/


    /**Método de inicialização dos testes, esse método será executado antes de qualquer classe de testes.*/
    @BeforeClass
    public static void initTest() {
        System.setProperty("webdriver.chrome.driver", PATH_CHROME_DRIVER);

        driver = new ChromeDriver();
        addRecordPO = new AddRecordPO(driver);
        listPO = new ListPO(driver);
        addRecordBuilder = new AddRecordBuilder(addRecordPO);
        elementUtil = new ElementUtil();

        driver.manage().window().maximize();
        driver.get(URL_BASE);
    }

    /**Método de encerramento dos testes, esse método será executado ao final de qualquer classe de testes.*/
    @AfterClass
    public static void endTest() {
        // driver.quit();
    }

    /**Método responsável por preencher o formulario de cadastro.*/
    public void createRecord(Integer maximumWaitingTime) {
        addRecordBuilder
            .withCustomerName(addRecordPO.register[0])
            .withContactLastName(addRecordPO.register[1])
            .withContactFirstName(addRecordPO.register[2])
            .withPhone(addRecordPO.register[3])
            .withAddressLine1(addRecordPO.register[4])
            .withAddressLine2(addRecordPO.register[5])
            .withCity(addRecordPO.register[6])
            .withState(addRecordPO.register[7])
            .withPostalCode(addRecordPO.register[8])
            .withCountry(addRecordPO.register[9])
            .withSalesRepEmployee(addRecordPO.register[10])
            .withCreditLimit(addRecordPO.register[11])
            .createRecord(maximumWaitingTime, driver);

        addRecordPO.saveButton.click();
    }

    /**Método responsável por navegar para tela de novo cadastro*/
    public void navigateToAdd(WebElement selectVersion, Integer maximumWaitingTime) {
        addRecordPO.selectVersion(maximumWaitingTime, selectVersion, driver);

        elementUtil.waitElementVisibility(maximumWaitingTime, listPO.buttonAddRecord, driver);
        listPO.buttonAddRecord.click();
    }

    /**Método responsável por verificar se existem multiplos registros para serem deletados.*/
    public boolean checkMultipleDelete() {
        boolean checkMultipleDelete;

        if(listPO.modalDeleteConfirmationTextMultiple.getText().equals("")) {
            checkMultipleDelete = false;
        }else {
            checkMultipleDelete = true;
        }
        return checkMultipleDelete;
    }
}
