package com.it_academy.parsers.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import static com.it_academy.xml.services.DomXmlServices.*;

public class DomParserRunner {

    private static final String XML_PATH = "journal.xml";

    public static void main(String[] args) {
        Document document = DomParserUtils.parseXMLDocument(XML_PATH);
        NodeList nodeList = DomParserUtils.getNodeList(document);
        setJournalWithXMLNodeValues(nodeList);
        outputParsedByDomJournal();
    }
}
