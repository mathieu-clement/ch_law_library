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
    private static Pattern idPattern = Pattern.compile("(\\d+)\\.html#\\d+$");

    // URL to Law page
    private static Pattern lawUrlPattern = Pattern.compile("/opc/fr/classified-compilation/(\\d+)/index\\.html");

    // Redirection
    private static Pattern redirectPattern = Pattern.compile("([^→]+) →");

    public List<Compilation> getCompilations(Language language) throws IOException {

        Document doc = Jsoup.connect(String.format(
                "http://www.admin.ch/opc/%s/classified-compilation/%s.html",
                language.getShortCode(), this.id)).get();
        Elements elements;
        // 1st type of page: Only links to compilations
        elements = doc.select("table tbody td a[href]");
        List<Compilation> compilations = new ArrayList<Compilation>(elements.size());
        for (Element linkElement : elements) {
            Matcher matcher = lawUrlPattern.matcher(linkElement.attr("href"));
            if (matcher.find()) {
                compilations.add(new Law(linkElement.text(),
                        linkElement.parent().parent().select("a[name]").attr("name"),
                        matcher.group(1)));
            } else {
                String catTitle = linkElement.parent().text();
                matcher = redirectPattern.matcher(catTitle);
                if (matcher.find()) {
                    compilations.add(new Compilation(matcher.group(1), linkElement.text()));
                } else {
                    matcher = idPattern.matcher(linkElement.attr("href"));
                    if (matcher.find()) {
                        compilations.add(new Compilation(linkElement.text(),
                                linkElement.parent().parent().child(0).text()));
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
