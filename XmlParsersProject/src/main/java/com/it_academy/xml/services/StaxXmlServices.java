package com.it_academy.xml.services;

import com.it_academy.entity.Journal;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StaxXmlServices {

    public static Journal currJournal = null;
    public static Journal.Сontact currJournalContact = null;
    public static Journal.Article currJournalArticle = null;

    public static List<Journal> journalsList = null;
    public static List<Journal.Article> articlesList = null;

    public static String tagContent = null;

    public static void setJournalWithXMLNodeValuesByStaxParser(XMLStreamReader reader){

        while (true) {
            try {
                if (!reader.hasNext()) break;
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            int event = 0;
            try {
                event = reader.next();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    if ("journal".equals(reader.getLocalName())) {
                        currJournal = new Journal();
                    }
                    if ("contacts".equals(reader.getLocalName())) {
                        currJournalContact = currJournal.new Сontact();
                    }
                    if ("articles".equals(reader.getLocalName())) {
                        articlesList = new ArrayList<>();
                    }
                    if ("article".equals(reader.getLocalName())) {
                        currJournalArticle = currJournal.new Article();
                        currJournalArticle.setId(reader.getAttributeValue(0));
                    }
                    if ("hotkeys".equals(reader.getLocalName())) {
                        currJournalArticle.hotkeysList = new ArrayList<>();
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "journal_title":
                            currJournal.setJournalTitle(tagContent);
                            break;
                        case "address":
                            currJournalContact.setAddress(tagContent);
                            break;
                        case "tel":
                            currJournalContact.setTel(tagContent);
                            break;
                        case "email":
                            currJournalContact.setEmail(tagContent);
                            break;
                        case "journal_url":
                            currJournalContact.setJournalUrl(tagContent);
                            break;
                        case "title":
                            currJournalArticle.setTitle(tagContent);
                            break;
                        case "author":
                            currJournalArticle.setAuthor(tagContent);
                            break;
                        case "url":
                            currJournalArticle.setUrl(tagContent);
                            break;
                        case "hotkey":
                            currJournalArticle.hotkeysList.add(tagContent);
                            break;
                        case "article":
                            articlesList.add(currJournalArticle);
                            break;
                    }
                    break;

                case XMLStreamConstants.START_DOCUMENT:
                    journalsList = new ArrayList<>();
                    break;
            }
        }
    }

    public static void outputParsedByStaxJournal() {
        System.out.println(currJournal);
        System.out.println(currJournalContact);
        for (Journal.Article article : articlesList) {
            System.out.println(article);
        }
    }
}
