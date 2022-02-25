package br.com.sicred.test.flow;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.sicred.page.AddRecordPO;
import br.com.sicred.page.ListPO;
import br.com.sicred.test.BaseTest;
import br.com.sicred.util.ElementUtil;

/**Classe responsável pelos testes da página de lista de cadastros.*/
public class ListFlowTest extends BaseTest {
    
    private static ListPO listPO;
    private static AddRecordPO addRecordPO;
    private static ElementUtil elementUtil;
    private int maximumWaitingTime = 10;

    /**Método responsável por iniciar todos os pontos necessários antes da execução dos testes*/
    @BeforeClass
    public static void initListTest() {
        listPO = new ListPO(driver);
        addRecordPO = new AddRecordPO(driver);
        elementUtil = new ElementUtil();
    }

    /**Deve ser capaz de buscar um cadastro pelo nome do cliente*/
    @Test
    public void TC001_must_search_for_a_record_by_customer_name_and_deleted() throws InterruptedException {
        String customerName = "Teste Sicredi";
        final String DELETE_CONFIRMATION_TEXT = "Are you sure that you want to delete";
        final String INVALID_CHECK_TEXT = "Against Evidence";
        String messagePopUpSuccessfullyDeleted = "Your data has been successfully deleted from the database.";

        navigateToAdd(listPO.selectVersionV4Theme, maximumWaitingTime);
        createRecord(maximumWaitingTime);
        elementUtil.waitElementVisibility(maximumWaitingTime, addRecordPO.linkGoBackToList, driver);
        addRecordPO.linkGoBackToList.click();

        listPO.filterListReecord(maximumWaitingTime, listPO.inputSearchCustomerName, customerName, elementUtil);

        Thread.sleep(5000);
        listPO.checkBox.click();
        
        elementUtil.waitElementVisibility(maximumWaitingTime, listPO.buttonDeleteCheckBoxSelected, driver);
        listPO.buttonDeleteCheckBoxSelected.click();

        elementUtil.waitElementToBeClickable(maximumWaitingTime, driver, listPO.buttonDeleteConfirmation);
        if(checkMultipleDelete()) {
            assertTrue(listPO.modalDeleteConfirmationTextMultiple.getText().contains(DELETE_CONFIRMATION_TEXT));
            assertFalse(listPO.modalDeleteConfirmationTextMultiple.getText().contains(INVALID_CHECK_TEXT));
        }else {
            assertTrue(listPO.modalDeleteConfirmationTextMultipleOne.getText().contains(DELETE_CONFIRMATION_TEXT));
            assertFalse(listPO.modalDeleteConfirmationTextMultipleOne.getText().contains(INVALID_CHECK_TEXT));
        }
        
        elementUtil.waitElementVisibility(maximumWaitingTime, listPO.buttonDeleteConfirmation, driver);
        listPO.buttonDeleteConfirmation.click();
        
        elementUtil.waitElementVisibility(maximumWaitingTime, listPO.buttonClosePopUpSuccessfullyDeleted, driver);
        assertTrue(listPO.textPopUpItemSuccessfullyDeleted.getText().contains(messagePopUpSuccessfullyDeleted));
        assertFalse(listPO.textPopUpItemSuccessfullyDeleted.getText().contains(INVALID_CHECK_TEXT));
    }
}
