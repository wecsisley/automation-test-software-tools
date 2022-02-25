package br.com.sicred.test.endToEnd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.sicred.page.AddRecordPO;
import br.com.sicred.page.ListPO;
import br.com.sicred.test.BaseTest;
import br.com.sicred.util.ElementUtil;

/**Classe responsável pelo teste de rotina completa*/
public class SystemTest extends BaseTest{

    private static AddRecordPO addRecordPO;
    private static ListPO listPO;
    private static ElementUtil elementUtil;
    /**URL da página de novo cadastro.*/
    private final String ADD_RECORD_URL_V4 = "https://www.grocerycrud.com/v1.x/demo/my_boss_is_in_a_hurry/bootstrap-v4/add";
    /**URL da página lista de cadastros. */
    private final String LIST_RECORD_URL_V4 = "https://www.grocerycrud.com/v1.x/demo/my_boss_is_in_a_hurry/bootstrap-v4/";
    /**URL inválida para testes de contra prova.*/
    private final String INVALID_URL = "https://www.invalidURL.com";
    /**Mensagem de sucesso ao adicionar um novo cadastro.*/
    private final String ADD_SUCCESS_TEXT = "Your data has been successfully stored into the database.";
    /**Mensagem de sucesso ao atualizar um cadastro.*/
    private final String UPDATE_SUCCESS_TEXT = "Your data has been successfully updated.";
    /**Mensagem inválida para testes de contra prova.*/
    private final String INVALID_TEXT = "Invalid text";
    /**Mensagem de confirmação para deletar cadastro.*/
    private final String DELETE_CONFIRMATION_TEXT = "Are you sure that you want to delete";
    /**Mensagem de sucesso ao deletar um cadastro.*/
    private final String MESSAGE_POPUP_SUCCESSFULLY_DELETED = "Your data has been successfully deleted from the database.";
    /**Nome correto para ser atualizado no cadastro criado.*/
    private final String CORRECT_NAME = "Wecsisley";
    /**Nome do cliente para filtra a busca na lista de cadastros.*/
    private String customerName = "Teste Sicredi";
    /**Tempo máximo de espera em segundos, à ser utilizado nos métodos de espera.*/
    private int maximumWaitingTime = 10;
    
    /**Método responsável por iniciar todos os pontos necessários antes da execução dos testes.*/
    @BeforeClass
    public static void initSystem() {
        addRecordPO = new AddRecordPO(driver);
        listPO = new ListPO(driver);
        elementUtil = new ElementUtil();
    }

    /**Teste responsável por realizar um CRUD completo no cadastro.*/
    @Test
    public void TC001_crud_reacord()throws InterruptedException {
        /**Seleciando a versão V4 antes de iniciar o CRUD*/
        addRecordPO.selectVersion(maximumWaitingTime, addRecordPO.selectVersionV4Theme, driver);
        /**Verificando a versão se a versão foi alterada com sucesso.*/
        assertTrue(elementUtil.selectedElement(addRecordPO.selectVersionV4Theme));
        /**Contra prova de verificação da versão.*/
        assertFalse(elementUtil.selectedElement(addRecordPO.selectVersionV3Theme));

        /**Navegando para tela de cadastro.*/
        listPO.buttonAddRecord.click();
        /**Verificando se a navegação foi realizada com sucesso*/
        assertEquals(ADD_RECORD_URL_V4, driver.getCurrentUrl());
        /**Contra prova de verificação da navegação. */
        assertNotEquals(INVALID_URL, driver.getCurrentUrl());

        /**Criando um novo cadastro através de um builder.*/
        createRecord(maximumWaitingTime);
        /**Verificando se a criação do cadastro foi realizada com sucesso.*/
        assertTrue(addRecordPO.reportSuccess.getText().contains(ADD_SUCCESS_TEXT));
        /**Contra prova da verificação de criação de cadastro.*/
        assertFalse(addRecordPO.reportSuccess.getText().contains(INVALID_TEXT));

        /**Aguardando a visibilidade de um elemento específico.*/
        elementUtil.waitElementVisibility(maximumWaitingTime, addRecordPO.linkGoBackToList, driver);

        /**Retornando para página de lista de cadastros.*/
        addRecordPO.linkGoBackToList.click();
        /**Verificando se a navegação foi realizada com sucesso.*/
        assertEquals(driver.getCurrentUrl(), LIST_RECORD_URL_V4);
        /**Contra prova de verificação da navegação.*/
        assertNotEquals(INVALID_URL, driver.getCurrentUrl());

        /**Filtrando os cadastros através de um campo input específico.*/
        listPO.filterListReecord(maximumWaitingTime, listPO.inputSearchCustomerName, customerName, elementUtil);
            
        Thread.sleep(5000);
        /**Clicando no botão de edição do cadastro.*/
        listPO.buttonEditRecord.click();

        /**Aguardando a visibilidade de um elemento específico.*/
        elementUtil.waitElementVisibility(maximumWaitingTime, addRecordPO.inputContactFirstName, driver);

        /**Limpando o campo input do primeiro nome.*/
        addRecordPO.inputContactFirstName.clear();
        /**Inserindo o novo nome correto.*/
        addRecordPO.inputContactFirstName.sendKeys(CORRECT_NAME);
        /**Salvando a edição do cadastro.*/
        addRecordPO.saveButton.click();
        /**Verificando se o cadastro foi atualizado com sucesso.*/
        assertTrue(addRecordPO.reportSuccess.getText().contains(UPDATE_SUCCESS_TEXT));
        /**Contra prova de verificação da atualização de cadastro.*/
        assertFalse(addRecordPO.reportSuccess.getText().contains(INVALID_TEXT));

        /**Aguardando a visibilidade de um elemento específico.*/
        elementUtil.waitElementVisibility(maximumWaitingTime, addRecordPO.linkGoBackToList, driver);

        /**Retornando para página de lista de cadastros.
         * Esse elemento é mapeado através de um @FindAll por possuir 2 identificadores diferentes. 
         * Essa prática não é a ideal pois com o @FindAll há uma perca grande de desempenho e consequentemente,
         * uma demora grande na execusção do teste. Porém, optei por utilizar essa anotação para demonstrar 
         * que existe o recurso, mas em um cenário real teria optado por outra solução.
         */
        addRecordPO.linkGoBackToList.click();

        /**Aguardando a visibilidade de um elemento específico.*/
        elementUtil.waitElementVisibility(maximumWaitingTime, listPO.checkBox, driver);

        Thread.sleep(5000);
        /**Clicando o check box para selecionar todos os cadastros.*/
        listPO.checkBox.click();
            
        /**Aguardando a visibilidade de um elemento específico.*/
        elementUtil.waitElementVisibility(maximumWaitingTime, listPO.buttonDeleteCheckBoxSelected, driver);

        /**Clicando para deletar todos os cadastros selecionados.*/
        listPO.buttonDeleteCheckBoxSelected.click();

        /**Aguardando um elemento específico ser clicável.*/
        elementUtil.waitElementToBeClickable(maximumWaitingTime, driver, listPO.buttonDeleteConfirmation);

        /**Neste ponto a mensagem de confirmação antes de deletar o(s) cadastro(s) varia dependendo se existe apenas 1 cadastro
         * ou mais. Por tanto foi criado um método para identificar se existe 1 ou mais cadastros para serem deletados e dar 
         * seguencia no teste. O ideal seria que a equipe de desenvolvimento mantivesse o mesmo identificador para o elemento, 
         * e mudasse apenas a mensagem que é apresentada.
         */
        if(checkMultipleDelete()) {
            assertTrue(listPO.modalDeleteConfirmationTextMultiple.getText().contains(DELETE_CONFIRMATION_TEXT));
            assertFalse(listPO.modalDeleteConfirmationTextMultiple.getText().contains(INVALID_TEXT));
        }else {
            assertTrue(listPO.modalDeleteConfirmationTextMultipleOne.getText().contains(DELETE_CONFIRMATION_TEXT));
            assertFalse(listPO.modalDeleteConfirmationTextMultipleOne.getText().contains(INVALID_TEXT));
        }
            
        /**Aguardando a visibilidade de um elemento específico.*/
        elementUtil.waitElementVisibility(maximumWaitingTime, listPO.buttonDeleteConfirmation, driver);
        /**Clicando no botão para excluir os cadastros selecionados.*/
        listPO.buttonDeleteConfirmation.click();
            
        /**Aguardando a visibilidade de um elemento específico.*/
        elementUtil.waitElementVisibility(maximumWaitingTime, listPO.buttonClosePopUpSuccessfullyDeleted, driver);
        /**Verificando se o(s) cadastro(s) foi(s) excluido com sucesso.*/
        assertTrue(listPO.textPopUpItemSuccessfullyDeleted.getText().contains(MESSAGE_POPUP_SUCCESSFULLY_DELETED));
        /**Contra prova de verificação de exclusão.*/
        assertFalse(listPO.textPopUpItemSuccessfullyDeleted.getText().contains(INVALID_TEXT));
    }
}
