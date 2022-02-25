package br.com.sicred.util;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Classe util para manipulação de elementos. 
 * Todo método de manipulação de elemento genérico deve estar nessa classe para ser reutilizado por todo o sistema.
 */
public class ElementUtil {
    
     /**
     * Método para aguardar a visibilidade de um elemento específico.
     * @param elementWait : Elemento a ser aguardado.
     * @param maximumWaitingTime : Tempo máximo em segundos para aguardar a visibilidade do elemento.
    */
    public void waitElementVisibility(Integer maximumWaitingTime, WebElement elementWait, WebDriver driver){
        driver.manage().timeouts().implicitlyWait(maximumWaitingTime, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, maximumWaitingTime);
	    elementWait = wait.until(ExpectedConditions.visibilityOf(elementWait));
    }
     
    /**
     * Método para aguardar a invisibilidade de um elemento específico.
     * @param elementWait : Elemento a ser aguardado.
     * @param maximumWaitingTime : Tempo máximo em segundos para aguardar a visibilidade do elemento.
    */
    public void waitElementInvisisibility(Integer maximumWaitingTime, WebElement elementWait, WebDriver driver){
        driver.manage().timeouts().implicitlyWait(maximumWaitingTime, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, maximumWaitingTime);
	    wait.until(ExpectedConditions.invisibilityOf(elementWait));
    }

    /**
     * Método responsável por aguardar até que um determinado elemento esteja clicável
     * @param maximumWaitingTime Tempo máximo em segundos para aguardar o elemento ser clicável.
     * @param driver Driver utilizado pelo sistema.
     * @param elementToBeClickable Elemento a ser aguardado.
    */
    public void waitElementToBeClickable(Integer maximumWaitingTime, WebDriver driver, WebElement elementToBeClickable) {
        WebDriverWait wait = new WebDriverWait(driver, maximumWaitingTime);
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClickable));
    }

    /**Método responsável por escrever em um campo input
     * @param text Texto que será escrito no campo input informado.
     * @param maximumWaitingTime Tempo máximo em segundos que o sistema irá aguardar a visibilidade do cmapo input.
     * @param inputField Campo input que será utilizado.
     * @param driver Drive utilizado pelo sistema.
     */
    public void writeInputField(String text, Integer maximumWaitingTime, WebElement inputField, WebDriver driver){
        waitElementVisibility(maximumWaitingTime, inputField, driver);
        inputField.sendKeys(text);
    }

    /**
     * Método responsável por determinar se um elemento está visível.
     * @param visibleElement Elemento para ser verificado se esta visível na tela.
     * @return Retorna um boolean dependendo do status de visibilidade do elemento.
     * */
    public  boolean visibleElement(WebElement visibleElement) {
        boolean checkElement = false;
        try {
            if(visibleElement.isDisplayed()) {
                checkElement = true;
            }else {
                checkElement = false;
            }
        }catch(NoSuchElementException e) {
            System.out.println(visibleElement + " element not found");
        }
        return checkElement;
    }
    
    /**
     * Método responsável por determinar se um elemento está selecionado.
     * @param selectedElement Elemento para ser verificado se esta selecionado na tela.
     * @return Retorna um boolean dependendo do status de seleção do elemento.
     * */
    public  boolean selectedElement(WebElement selectedElement) {
        boolean checkElement = false;
        try {
            if(selectedElement.isSelected()) {
                checkElement = true;
            }else {
                checkElement = false;
            }
        }catch(NoSuchElementException e) {
            System.out.println(selectedElement + " element not found");
        }
        return checkElement;
    }
}
