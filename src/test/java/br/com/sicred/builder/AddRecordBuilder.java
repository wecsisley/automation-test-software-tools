package br.com.sicred.builder;

import org.openqa.selenium.WebDriver;

import br.com.sicred.page.AddRecordPO;
import br.com.sicred.util.ElementUtil;

/**Classe builder de AddRecordPO*/
public class AddRecordBuilder {

    private AddRecordPO addRecordPO;
    private ElementUtil elementUtil;
    private String customerName = "";
    private String contactLastName = "";
    private String contactFirstName = "";
    private String phoneField = "";
    private String addressLine1 = "";
    private String addressLine2 = "";
    private String city = "";
    private String state = "";
    private String postalCode = "";
    private String country = "";
    private String salesRepEmployee = "";
    private String creditLimit = "";


    /**
     * Construtor da classe.
     * @param addRecordPO Instancia de AddRecordPO que irá passar os atributos.
     */
    public AddRecordBuilder(AddRecordPO addRecordPO) {
        this.addRecordPO = addRecordPO;
    }

    /**Builder responsável por alterar o valor da String customerName*/
    public AddRecordBuilder withCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    /**Builder responsável por setar o valor da String contactLastName*/
    public AddRecordBuilder withContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
        return this;
    }

    /**Builder responsável por setar o valor da String contactFirstName*/
    public AddRecordBuilder withContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
        return this;
    }

    /**Builder responsável por setar o valor da String contactFirstName*/
    public AddRecordBuilder withPhone(String phoneField) {
        this.phoneField = phoneField;
        return this;
    }

    /**Builder responsável por setar o valor da String addressLine1*/
    public AddRecordBuilder withAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    /**Builder responsável por setar o valor da String addressLine2*/
    public AddRecordBuilder withAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    /**Builder responsável por setar o valor da String city*/
    public AddRecordBuilder withCity(String city) {
        this.city = city;
        return this;
    }
    
    /**Builder responsável por setar o valor da String state*/
    public AddRecordBuilder withState(String state) {
        this.state = state;
        return this;
    }
    
    /**Builder responsável por setar o valor da String postalCode*/
    public AddRecordBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }
    
    /**Builder responsável por setar o valor da String salesRepEmployeeNumber
*/
    public AddRecordBuilder withCountry(String country) {
        this.country = country;
        return this;
    }
    
    /**Builder responsável por setar o valor da String salesRepEmployeeNumber*/
    public AddRecordBuilder withSalesRepEmployee(String salesRepEmployee) {
        this.salesRepEmployee = salesRepEmployee;
        return this;
    }

    /**Builder responsável por setar o valor da String creditLimit*/
    public AddRecordBuilder withCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }
    public void createRecord(Integer maximumWaitingTime, WebDriver driver) {
        elementUtil = new ElementUtil();

        elementUtil.writeInputField(customerName, maximumWaitingTime, addRecordPO.inputCustomerName, driver);
        elementUtil.writeInputField(contactLastName, maximumWaitingTime, addRecordPO.inputContactLastName, driver);
        elementUtil.writeInputField(contactFirstName, maximumWaitingTime, addRecordPO.inputContactFirstName, driver);
        elementUtil.writeInputField(phoneField, maximumWaitingTime, addRecordPO.inputPhone, driver);
        elementUtil.writeInputField(addressLine1, maximumWaitingTime, addRecordPO.inputAddressLine1, driver);
        elementUtil.writeInputField(addressLine2, maximumWaitingTime, addRecordPO.inputAddressLine2, driver);
        elementUtil.writeInputField(city, maximumWaitingTime, addRecordPO.inputCity, driver);
        elementUtil.writeInputField(state, maximumWaitingTime, addRecordPO.inputState, driver);
        elementUtil.writeInputField(postalCode, maximumWaitingTime, addRecordPO.inputPostalCode, driver);
        elementUtil.writeInputField(country, maximumWaitingTime, addRecordPO.inputCountry, driver);
        elementUtil.writeInputField(salesRepEmployee, maximumWaitingTime, addRecordPO.inputSalesRepEmployee, driver);
        elementUtil.writeInputField(creditLimit, maximumWaitingTime, addRecordPO.inputCreditLimit, driver);
    }
}
