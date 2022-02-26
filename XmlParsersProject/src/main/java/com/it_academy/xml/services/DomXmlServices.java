package com.it_academy.xml.services;

import com.it_academy.parsers.DOM.DomParserUtils;
import com.it_academy.entity.Journal;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DomXmlServices {

    public static final List<Journal> journals = new ArrayList<>();
    public static final List<Journal.Сontact> contacts = new ArrayList<>();
    public static final List<Journal.Article> articles = new ArrayList<>();

    private static Journal.Сontact setJournalWithContactsXMLChildNodeValues(Journal.Сontact contact, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case "address":
                contact.setAddress(content);
                break;
            case "tel":
                contact.setTel(content);
                break;
            case "email":
                contact.setEmail(content);
                break;
            case "journal_url":
                contact.setJournalUrl(content);
                break;
        }
        return contact;
    }

    private static Journal.Article setJournalWithIdOfArticlesXML(Journal.Article article, Node node) {
        String attribute = node
                .getAttributes()
                .getNamedItem("ID")
                .getNodeValue()
                .trim();
        switch (node.getNodeName()) {
            case "article":
                article.setId(attribute);
                break;
        }
        return article;
    }

    private static Journal.Article setJournalWithArticlesXMLChildNodeValues(Journal.Article article, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case "title":
                article.setTitle(content);
                break;
            case "author":
                article.setAuthor(content);
                break;
            case "url":
                article.setUrl(content);
                break;
            case "hotkey":
                article.setHotkey(content);
                break;
        }
        return article;
    }

    private static Journal.Article setArticleWithHotkeysXMLValues(Journal.Article article, Node node){
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case "hotkey":
                article.hotkeysList.add(content);
                break;
        }
        return article;
    }

    public static void setJournalWithXMLNodeValues(NodeList nodeList) {
        DomParserUtils.getNodeListStream(nodeList).forEach(node -> {
            //journal node
            if (node instanceof Element) {
                Journal journal = new Journal();
                //get content from journal_title node
                if (node.getNodeName() == "journal_title") {
                    journal.setJournalTitle(node.getTextContent());
                    journals.add(journal);
                }
                //contacts node
                if (node.getNodeName() == "contacts") {
                    Journal.Сontact contact = journal.new Сontact();
                    NodeList contactsNodes = node.getChildNodes();
                    DomParserUtils.getNodeListStream(contactsNodes).forEach(childNode -> {
                        //get content from contacts nodes
                        if (childNode instanceof Element) {
                            setJournalWithContactsXMLChildNodeValues(contact, childNode);
                        }
                    });
                    contacts.add(contact);
                }
                //articles nodes
                if (node.getNodeName() == "articles") {
                    NodeList articlesNodes = node.getChildNodes();
                    DomParserUtils.getNodeListStream(articlesNodes).forEach(articlesNode -> {
                        //article's childNodes
                        if (articlesNode instanceof Element) {
                            Journal.Article article = journal.new Article();
                            //get id from each article node
                            setJournalWithIdOfArticlesXML(article, articlesNode);
                            NodeList childNodesOfArticle = articlesNode.getChildNodes();
                            //get childNodes of each article
                            DomParserUtils.getNodeListStream(childNodesOfArticle).forEach(childNodeOfArticle -> {
                                if (childNodeOfArticle instanceof Element) {
                                    setJournalWithArticlesXMLChildNodeValues(article, childNodeOfArticle);
                                    article.hotkeysList = new ArrayList<>();
                                    NodeList hotkeysNodes = childNodeOfArticle.getChildNodes();
                                    //get hotkeys
                                    DomParserUtils.getNodeListStream(hotkeysNodes).forEach(hotkeyNode -> {
                                        if (hotkeyNode instanceof Element) {
                                            setArticleWithHotkeysXMLValues(article, hotkeyNode);
                                        }
                                    });
                                }
                            });
                            articles.add(article);
                        }
                    });
                }

            }
        });
    }

    public static void outputParsedByDomJournal(){
        System.out.println(journals);
        System.out.println(contacts);
        for (Journal.Article article : articles) {
            System.out.println(article);
        }
    }
}
