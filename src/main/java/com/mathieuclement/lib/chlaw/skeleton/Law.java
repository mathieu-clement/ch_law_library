package com.mathieuclement.lib.chlaw.skeleton;

public class Law extends Compilation {
    private String webPageId;

    public Law(String name, String id, String webPageId) {
        super(name, id);
        this.webPageId = webPageId;
    }

    public String getWebPageId() {
        return webPageId;
    }

    public void setWebPageId(String webPageId) {
        this.webPageId = webPageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Law law = (Law) o;

        if (webPageId != null ? !webPageId.equals(law.webPageId) : law.webPageId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (webPageId != null ? webPageId.hashCode() : 0);
        return result;
    }
}
