package br.com.sicred.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

/**PageObject da página Dashboard*/
public class AddRecordPO extends BasePO {
    
    public String[] register = {"Teste Sicredi", "Teste", "Wecsisleyy", "51 9999-9999", "Av Assis Brasil, 3970", 
                            "Torre D", "Porto Alegre", "RS", "91000-000", "Brasil", "Teste", "200"};

    /**Botão para cancelar uma gravação*/
    @FindBy(id = "cancel-button")
    public WebElement cancelButton;

    /**Botão para salvar uma gravação*/
    @FindBy(id = "form-button-save")
    public WebElement saveButton;

    /**Campo input para preencher o nome do cliente*/
    @FindBy(id = "field-customerName")
    public WebElement inputCustomerName;

    /**Campo input para preencher o ultimo nome*/
    @FindBy(id = "field-contactLastName")
    public WebElement inputContactLastName;

    /**Campo input para preencher o primeiro nome*/
    @FindBy(id = "field-contactFirstName")
    public WebElement inputContactFirstName;

    /**Campo input para preencher o campo de telefone*/
    @FindBy(id = "field-phone")
    public WebElement inputPhone;

    /**Campo input para preencher a linha 1 do endereço*/
    @FindBy(id = "field-addressLine1")
    public WebElement inputAddressLine1;

    /**Campo input para preencher a linha 2 do endereço*/
    @FindBy(id = "field-addressLine2")
    public WebElement inputAddressLine2;

    /**Campo input para preencher o campo de cidade*/
    @FindBy(id = "field-city")
    public WebElement inputCity;

    /**Campo input para preencher o campo de estado*/
    @FindBy(id = "field-state")
    public WebElement inputState;
    
    /**Campo input para preencher o campo de código postal*/
    @FindBy(id = "field-postalCode")
    public WebElement inputPostalCode;

    /**Campo input para preencher o campo de país*/
    @FindBy(id = "field-country")
    public WebElement inputCountry;

    /**Campo input para preencher o campo de número do representante de venda*/
    @FindBy(id = "field-salesRepEmployeeNumber")
    public WebElement inputSalesRepEmployee;

    /**Campo input para preencher o campo de limite de crédito*/
    @FindBy(id = "field-creditLimit")
    public WebElement inputCreditLimit;

    /**Texto de sucesso no cadastro*/
    @FindBy(css = "#report-success p")
    public WebElement reportSuccess;

    /**Link Go back to list*/
    @FindAll ({
        @FindBy(css = "#report-success p :nth-child(2)"),
        @FindBy(css = "#report-success p :nth-child(1)")
    })
    public WebElement linkGoBackToList;

    /**Construtor da classe DashboardPO
     * @param driver Drive utilizado pela classe, o mesmo é herdado pela classe pai.
     */
    public AddRecordPO(WebDriver driver) {
        super(driver);
    }

    
}
