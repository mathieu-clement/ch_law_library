package com.mathieuclement.lib.chlaw.skeleton;

public class Law extends Compilation {
    private String webPageId;

    public Law(String name, String id) {
        super(name, id);
    }

    public Law(String name, String id, String webPageId) {
        this(name, id);
        this.webPageId = webPageId;
    }

    public String getWebPageId() {
        return webPageId;
    }

    public void setWebPageId(String webPageId) {
        this.webPageId = webPageId;
    }
}
