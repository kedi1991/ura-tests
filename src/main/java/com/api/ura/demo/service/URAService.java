package com.api.ura.demo.service;

import com.api.ura.demo.model.URAModel;
import org.springframework.stereotype.Service;

import javax.xml.soap.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class URAService {
    /**
     *GETS THE model
     */
    //create dummy data
    List<URAModel> dummyList;

    URAModel uraModel;

    public URAModel getService(int id){
        URAModel uraModel1 = new URAModel(0,87,"okurut henry kedi");
        URAModel uraModel2 = new URAModel(1,54,"Jayden Kedi");
        URAModel uraModel3 = new URAModel(2, 29,"Akudo Angella");
        URAModel uraModel4 = new URAModel(3, 40,"Cooper akol");
        URAModel uraModel5 = new URAModel(4, 21,"Etuk Albo");

        //combine all into one list
        dummyList = Arrays.asList(uraModel1, uraModel2, uraModel3, uraModel4, uraModel5);

        for (URAModel uraModel: dummyList){
            if (id == uraModel.getId()){
                this.uraModel = uraModel;
            }
        }
        return this.uraModel;
    }
    //get the ura details
    public SOAPMessage generatePrnRequest() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage genPrn = messageFactory.createMessage();
        SOAPPart soapPart = genPrn .getSOAPPart();
        String serverURI = "http://tempuri.org/IPaymentService/";

        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration("tem","http://tempuri.org/");
        soapEnvelope.addNamespaceDeclaration("urap","http://schemas.datacontract.org/2004/07/URAPaymentGateway.DataContracts");
        SOAPBody getPrnumber = genPrn.getSOAPBody();
        SOAPElement getPRN = getPrnumber.addChildElement("GetPRN","tem");
        SOAPElement prnRequest = getPRN.addChildElement("PRNRequest","tem");
        SOAPElement additionalFees = prnRequest.addChildElement("AdditionalFees", "urap");
        additionalFees.addTextNode("0");
        SOAPElement amount1 = prnRequest.addChildElement("Amount", "urap");
        amount1.addTextNode("100000");
        SOAPElement assessmentDate = prnRequest.addChildElement("AssessmentDate","urap");
        assessmentDate.addTextNode("2023-07-24T12:16:15.534");
        SOAPElement buildingName = prnRequest.addChildElement("BuildingName","urap");
        buildingName.addTextNode("test");
        SOAPElement contactNo = prnRequest.addChildElement("ContactNo","urap");
        contactNo.addTextNode("256781126017");


        SOAPElement email1 = prnRequest.addChildElement("Email","urap");
        email1.addTextNode("asaulkiapnde@gmail.com");
        SOAPElement expiryDays = prnRequest.addChildElement("ExpiryDays","urap");
        expiryDays.addTextNode("1");

        SOAPElement grossAmount = prnRequest.addChildElement("GrossAmount","urap");
        grossAmount.addTextNode("0");

        SOAPElement mobileNo = prnRequest.addChildElement("MobileNo", "urap");
        mobileNo.addTextNode("256781126017");
        SOAPElement noOfForms = prnRequest.addChildElement("NoOfForms","urap");
        noOfForms.addTextNode("1");

        SOAPElement paymentBankCode = prnRequest.addChildElement("PaymentBankCode", "urap");
        paymentBankCode.addTextNode("DTB");
        SOAPElement paymentMode = prnRequest.addChildElement("PaymentMode", "urap");
        paymentMode.addTextNode("CASH");
        SOAPElement paymentType = prnRequest.addChildElement("PaymentType", "urap");
        paymentType.addTextNode("DT");
        SOAPElement referenceNo = prnRequest.addChildElement("ReferenceNo", "urap");
        referenceNo.addTextNode("0001069329");
        SOAPElement SRCSystem = prnRequest.addChildElement("SRCSystem", "urap");
        SRCSystem.addTextNode("frontoffice");
        SOAPElement tIN = prnRequest.addChildElement("TIN", "urap");
        tIN.addTextNode("1000035867");
        SOAPElement taxHead = prnRequest.addChildElement("TaxHead", "urap");
        taxHead.addTextNode("URSB84");
        SOAPElement taxPayerBankCode = prnRequest.addChildElement("taxPayerBankCode", "urap");
        taxPayerBankCode.addTextNode("DTB");
        SOAPElement taxPayerName = prnRequest.addChildElement("TaxPayerName", "urap");
        taxPayerName.addTextNode("Akankwasa");
        SOAPElement signature = getPRN.addChildElement("concatenatedUsernamePasswordSignature","tem");
        signature.addTextNode("HIeQxRmTz2Kv67QI1lvLNKJe9sM0NbxEgWuEUM9TmNaPV6PpE4R9SE8JNsMugPnNs9O5TiJEUmgrKFVc3KDHzc6NehFZVb7ZCGoXNACOxhFJ0yNIylh0mW8FjezhXDQZUJql+TPZ8v2d9Sc1K5AZxtxW/34sRDJkm2L1ey3XOF+vNHb6H4YZEvC5QaCScof/zLRek9PmVmuSXK/fL96RvWHVi8j1lD9lcw3N/6VVcSNHCBh6jmPpQTPUmIZJVnStxtsxRRHzP+hfMj0IfVk98jAamwK5d3XjaR3+XH8o+syCAK0p+7lGyDwuV1ujpKQEcAeRNPE/HaOB1S/dFJRw5w==");
        SOAPElement password = getPRN.addChildElement("encryptedConcatenatedUsernamePassword","tem");
        password.addTextNode("QEFXAcXUHXq+fgFdvl2vy47+0zrnNwXzolsw31bg6Rw6bNX1A2iEpXxkJ2JMug/S3mq/j4tlVaVBhuVMFVxbFUTuGqnM4dl2bfL6KHNzwUc6YKuAAluAJLn/EMtYd1poxU/XC64re80UVlinpBWQzKmFGOzfhJO33NXXaT+j9xHkBTi1fXwAhHdeEjxE4OYDk+AczVsv1tziWkOGeW0r72wumxLgBoz9DGpzJrZ+qRNx6CRCzVNB1/xNhIp766TNG15mk0a+rc3DZjQByU/i7lw3PLMHRFF6ojrtxy4bskDkr26bkTFBpNFtVTFQdTSo/iOvgAtDzL+QgxJ7UqTa2w==");
        SOAPElement userName = getPRN.addChildElement("userName","tem");
        userName.addTextNode("URSB");

        MimeHeaders headers = genPrn.getMimeHeaders();
        headers.addHeader("SOAPAction",serverURI + "GetPRN");

        genPrn.saveChanges();

        return genPrn;
    }

    public String ReadPRNResponse() throws SOAPException, IOException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String url = "https://testpayments.ura.go.ug/MDAService/PaymentServices.svc";
        SOAPMessage soapResponse = soapConnection.call(generatePrnRequest(), url);
        //soapResponse.writeTo(System.out);

        String prn = soapResponse.getSOAPBody().getElementsByTagName("a:PRN").item(0).getTextContent();
        String SearchCode = soapResponse.getSOAPBody().getElementsByTagName("a:SearchCode").item(0).getTextContent();

        soapConnection.close();
        return "{" + "\"" + "prn" + "\"" + ":" + prn + "," + "\"" + "searchCode" + "\"" + ":" + SearchCode + "}";
    }


}
