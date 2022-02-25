package br.com.sicred.test.flow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.sicred.builder.AddRecordBuilder;
import br.com.sicred.page.AddRecordPO;
import br.com.sicred.test.BaseTest;
import br.com.sicred.util.ElementUtil;

/**Classe responsável pelos testes da página de novo cadastro.*/
public class AddRecordFlowTest extends BaseTest {
    
    static AddRecordPO addRecordPO;
    static AddRecordBuilder addRecordBuilder;
    static ElementUtil elementUtil;
    String listPageV4 = "https://www.grocerycrud.com/v1.x/demo/my_boss_is_in_a_hurry/bootstrap-v4/";
    int maximumWaitingTime = 10;

    /**Método responsável por iniciar todos os pontos necessários antes da execução dos testes*/
    @BeforeClass
    public static void initAddRecordTest() {
        addRecordPO = new AddRecordPO(driver);
        addRecordBuilder = new AddRecordBuilder(addRecordPO);
        elementUtil = new ElementUtil();
    }

    /**Deve ser capaz de alterar a versão do combo select de "V3 Theme" para "V4 Theme"*/
    @Test
    public void TC001_must_change_select_version_to_v4Theme() {
        addRecordPO.selectVersion(maximumWaitingTime, addRecordPO.selectVersionV4Theme, driver);

        assertTrue(elementUtil.selectedElement(addRecordPO.selectVersionV4Theme));
        assertFalse(elementUtil.selectedElement(addRecordPO.selectVersionV3Theme));
    }

    /**Deve ser capaz de preencher e salvar um formulário*/
    @Test
    public void TC002_must_fill_in_a_form_and_save_it() {
        final String SUCCESS_TEXT = "Your data has been successfully stored into the database. Edit Record or Go back to list";
        navigateToAdd(addRecordPO.selectVersionV4Theme, maximumWaitingTime);

        createRecord(maximumWaitingTime);
        assertTrue(elementUtil.visibleElement(addRecordPO.reportSuccess));
        assertEquals(addRecordPO.reportSuccess.getText(), SUCCESS_TEXT);
    }

    /**Deve voltar para lista após criar um novo cadastro*/
    @Test
    public void TC003_must_go_back_to_list() {
        navigateToAdd(addRecordPO.selectVersionV4Theme, maximumWaitingTime);

        createRecord(maximumWaitingTime);

        elementUtil.waitElementVisibility(maximumWaitingTime, addRecordPO.linkGoBackToList, driver);
        addRecordPO.linkGoBackToList.click();

        assertEquals(driver.getCurrentUrl(), listPageV4);
    }
}
