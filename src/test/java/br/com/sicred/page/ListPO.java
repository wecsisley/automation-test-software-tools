package br.com.sicred.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.sicred.util.ElementUtil;

public class ListPO extends BasePO {

    /**Campo input para buscar por nome do cliente*/
    @FindBy(name = "customerName")
    public WebElement inputSearchCustomerName;

    /**Campo input para marcar o check box*/
    @FindBy(css = ".select-all-none")
    public WebElement checkBox;
    
    /**Modal para apresentação do texto de confirmação para a exclusão do(s) item(s).*/
    @FindBy(css = ".modal-content .modal-body .alert-delete-multiple")
    public WebElement modalDeleteConfirmationTextMultiple;
    
    /**Modal para apresentação do texto de confirmação para a exclusão do(s) item(s).*/
    @FindBy(css = ".modal-content .modal-body .alert-delete-multiple-one")
    public WebElement modalDeleteConfirmationTextMultipleOne;
    
    /**Botão para deletar check box selecionado*/
    @FindBy(css = ".delete-selected-button")
    public WebElement buttonDeleteCheckBoxSelected;

    /**Botão para confirmação de exclusão do(s) item(s) selecionado.*/
    @FindBy(css = ".modal-content .modal-footer .delete-multiple-confirmation-button")
    public WebElement buttonDeleteConfirmation;

    /**Botão para editar um cadastro*/
    @FindBy(css = ".only-desktops .btn-outline-dark")
    public WebElement buttonEditRecord;

    /**Botão para fechar o popUp de sucesso ao deletar um(s) item(s)*/
    @FindBy(css = ".bounceInDown .close")
    public WebElement buttonClosePopUpSuccessfullyDeleted;
    
    /**Botão para adicionar uma gravação*/
    @FindBy(css = ".header-tools .floatL.t5 .btn.btn-default")
    public WebElement buttonAddRecord;

    /**Texto do popUp que parece ao deletar um(s) item(s) com sucesso.*/
    @FindBy(css = ".bounceInDown :nth-child(4) p")
    public WebElement textPopUpItemSuccessfullyDeleted;

    /**
     * Construtor da classe
     * @param Driver utilizado pela classe, o mesmo é herdado pela classe pai.
    */
    public ListPO(WebDriver driver) {
        super(driver);
    }

    /**Método responsável por filtrar consulta na lista de cadastro*/
    public void filterListReecord(Integer maximumWaitingTime, WebElement inputFilter, String textFilter, ElementUtil elementUtil) {
        elementUtil.waitElementToBeClickable(maximumWaitingTime, driver, inputFilter);
        inputFilter.sendKeys(textFilter);
    }
}
