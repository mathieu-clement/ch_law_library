package com.mathieuclement.lib.chlaw.skeleton;

public class Language {
    private String shortCode;
    private String name;

    public Language(String name, String shortCode) {
        this.shortCode = shortCode;
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Language GERMAN = new Language("german", "de");
    public static final Language FRENCH = new Language("french", "fr");
    public static final Language ITALIAN = new Language("italian", "it");
    public static final Language RUMANTSH = new Language("rumantsch", "rm");
    public static final Language ENGLISH = new Language("english", "en");
}
