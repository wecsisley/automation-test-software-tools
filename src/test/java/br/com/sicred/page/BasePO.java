package br.com.sicred.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.sicred.util.ElementUtil;

/**Classe base para todos os PageObjects do sistema. Ao criar um novo PageObject o mesma deve herdar desta classe.*/
public abstract class BasePO {
    
    /**Driver que será utilizado por todas as páginas do sistema.*/
    protected WebDriver driver;

    /**Instancia do util de elementos.*/
    private ElementUtil elementUtil;

    /**Botão para abrir o grid de opções de versões no select da Dashboard*/
    @FindBy(id = "switch-version-select")
    public WebElement selectVersionButton;

    /**Versão V4 Theme dentro do select*/
    @FindBy(css = ".switch-version :nth-child(1)")
    public WebElement selectVersionFlexGrid;

    /**Versão V4 Theme dentro do select*/
    @FindBy(css = ".switch-version :nth-child(2)")
    public WebElement selectVersionDataTables;

    /**Versão V3 Theme dentro do select*/
    @FindBy(css = ".switch-version :nth-child(3)")
    public WebElement selectVersionV3Theme;

    /**Versão V4 Theme dentro do select*/
    @FindBy(css = ".switch-version :nth-child(4)")
    public WebElement selectVersionV4Theme;
    
    /**Construtor da classe, também responsável por iniciar o PageFactory.
     * @param driver Drive que será utilizado pelos PageObjects do sistema.
    */
    public BasePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**Método responsável por selecionar uma versão desejada no comboSelect*/
    public void selectVersion(Integer maximumWaitingTime, WebElement selectVersion, WebDriver driver){
        elementUtil = new ElementUtil();
        
        elementUtil.waitElementVisibility(maximumWaitingTime, selectVersionButton, driver);
        selectVersionButton.click();

        elementUtil.waitElementVisibility(maximumWaitingTime, selectVersion, driver);
        selectVersion.click();
    }
}
