package com.mathieuclement.lib.chlaw.skeleton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compilation {
    private String name;
    private String id;

    public static final String UNKNOWN_NAME = "UNKNOWN";

    public Compilation(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Href of compilations
    private static Pattern idPattern = Pattern.compile("(\\d+)\\.html");

    // URL to Law page
    private static Pattern lawUrlPattern = Pattern.compile("/opc/fr/classified-compilation/(\\d+)/index\\.html");

    // Links like "173.41". Note: some may have three groups: "935.621.31"
    private static Pattern linkWithDigitsPattern = Pattern.compile("^\\d+\\.\\d+");

    public List<Compilation> getCompilations(Language language) throws IOException {

        Document doc = Jsoup.connect(String.format(
                "http://www.admin.ch/opc/%s/classified-compilation/%s.html",
                language.getShortCode(), this.id)).get();
        Elements elements;
        // 1st type of page: Only links to compilations
        elements = doc.select("#content table.law-index tr td[style=padding-left:0px] a");
        List<Compilation> compilations = new ArrayList<Compilation>(elements.size());
        for (Element linkElement : elements) {
            Matcher matcher = idPattern.matcher(linkElement.attr("href"));
            if (matcher.find()) {
                compilations.add(new Compilation(linkElement.text(), matcher.group(1)));
            }
        }

        // 2nd type of page. Links to some law on some compilation,
        // and direct links to actual laws
        elements = doc.select("#content table.law-index tr td[style=width: 30px]");
        if (elements != null)
            elements = doc.select("#content table.law-index tr");
        for (Element trElement : elements) {
            // Filter out rows with bold titles (and only 1 td cell)
            if (trElement.getElementsByTag("td").size() == 2) {
                // Parse links in the right column
                for (Element linkElement : trElement.getElementsByTag("td").get(1).getElementsByTag("a")) {
                    Matcher matcher = linkWithDigitsPattern.matcher(linkElement.text());
                    if (matcher.find()) {
                        compilations.add(new Law(UNKNOWN_NAME, linkElement.text()));
                    }
                    matcher = lawUrlPattern.matcher(linkElement.attr("href"));
                    if (matcher.find()) {
                        compilations.add(new Law(linkElement.text(),
                                trElement.getElementsByTag("td").get(0).text(),
                                matcher.group(1)));
                    }
                }
            }
        }
        return compilations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Compilation that = (Compilation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
