package com.app;
import kong.unirest.Unirest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

public class Parser {
    private String time;
    private String money;

    public Parser(String time, String money) {
        setTime(time);
        setMoney(money);
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + time;
    }

    public String getMoney(){
        return this.money;
    }

    public void setMoney(String money){
        this.money = money;
    }

    public String getValue() throws ParserConfigurationException, IOException, SAXException {
        String body = Unirest.get(getTime()).asString().getBody();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(body);
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);
        NodeList nList = doc.getElementsByTagName("CharCode");

        int count = 0;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if(Objects.equals(nNode.getTextContent(), this.getMoney())){
                count = temp + 1;
            }

        }
        NodeList nList1 = doc.getElementsByTagName("Value");
        Node nNode1 = nList1.item(count - 1);
        return nNode1.getTextContent();
    }
}