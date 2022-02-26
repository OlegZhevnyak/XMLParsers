package com.it_academy.entity;

import java.util.List;

public class Journal {

    private String journalTitle;

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    @Override
    public String toString() {
        return "Journal \n\t{" +
                "JournalTitle='" + journalTitle +
                "\'}";
    }

    public class Сontact {
        private String address;
        private String tel;
        private String email;
        private String journalUrl;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getJournalUrl() {
            return journalUrl;
        }

        public void setJournalUrl(String journalUrl) {
            this.journalUrl = journalUrl;
        }

        @Override
        public String toString() {
            return "\tСontact \n\t\t{" +
                    "address='" + address + '\'' +
                    ", tel='" + tel + '\'' +
                    ", email='" + email + '\'' +
                    ", journalUrl='" + journalUrl + '\'' +
                    '}';
        }
    }

    public class Article {
        private String id;
        private String title;
        private String author;
        private String url;
        private String hotkey;

        public List<String> hotkeysList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHotkey() {
            return hotkey;
        }

        public void setHotkey(String hotkey) {
            this.hotkey = hotkey;
        }

        @Override
        public String toString() {
            return "\tArticle {" +
                    "ID = " + id +
                    " {title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", url='" + url + '\'' +
                    ", \n\t\thotkeys='" + hotkeysList + '\'' +
                    "}}";
        }
    }

    public class Articles<A> {
        public List<Article> article;
    }
}
