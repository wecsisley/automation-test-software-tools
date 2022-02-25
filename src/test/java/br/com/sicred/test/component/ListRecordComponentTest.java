package br.com.sicred.test.component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.sicred.page.AddRecordPO;
import br.com.sicred.test.BaseTest;
import br.com.sicred.util.ElementUtil;

/**Classe de teste dos componentes da página ListRecord.*/
public class ListRecordComponentTest extends BaseTest{
    
    private static AddRecordPO addRecordPO;
    private static ElementUtil elementUtil;
    private int maximumWaitingTime = 10;

    /**Método responsável por iniciar todos os pontos necessários antes da execução dos testes*/
    @BeforeClass
    public static void initAddRecordTest() {
        addRecordPO = new AddRecordPO(driver);
        elementUtil = new ElementUtil();
    }

    /**Deve ser capaz de alterar a versão do combo select de "V3 Theme" para "V4 Theme"*/
    @Test
    public void TC001_must_change_select_version() {
        addRecordPO.selectVersion(maximumWaitingTime, addRecordPO.selectVersionFlexGrid, driver);
        assertTrue(elementUtil.selectedElement(addRecordPO.selectVersionFlexGrid));
        assertFalse(elementUtil.selectedElement(addRecordPO.selectVersionV4Theme));

        addRecordPO.selectVersion(maximumWaitingTime, addRecordPO.selectVersionDataTables, driver);
        assertTrue(elementUtil.selectedElement(addRecordPO.selectVersionDataTables));
        assertFalse(elementUtil.selectedElement(addRecordPO.selectVersionV4Theme));

        addRecordPO.selectVersion(maximumWaitingTime, addRecordPO.selectVersionV3Theme, driver);
        assertTrue(elementUtil.selectedElement(addRecordPO.selectVersionV3Theme));
        assertFalse(elementUtil.selectedElement(addRecordPO.selectVersionV4Theme));

        addRecordPO.selectVersion(maximumWaitingTime, addRecordPO.selectVersionV4Theme, driver);
        assertTrue(elementUtil.selectedElement(addRecordPO.selectVersionV4Theme));
        assertFalse(elementUtil.selectedElement(addRecordPO.selectVersionV3Theme));
    }
}
