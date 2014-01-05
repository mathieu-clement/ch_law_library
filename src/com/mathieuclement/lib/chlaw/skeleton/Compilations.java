package com.mathieuclement.lib.chlaw.skeleton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compilations {
    private static Pattern idPattern = Pattern.compile("(\\d+)\\.html ?$");

    public static Collection<Compilation> getCompilations(Language language) throws IOException {
        Collection<Compilation> compilations = new ArrayList<Compilation>(9);

        Document doc = Jsoup.connect("http://www.admin.ch/bundesrecht/00566/index.html?lang=" +
                language.getShortCode()).get();
        Elements elements = doc.select("#webContentDiv .webText > ol > li > a");
        assert elements.size() != 0;


        for (Element element : elements) {
            Matcher matcher = idPattern.matcher(element.attr("href"));
            matcher.find();
            compilations.add(new Compilation(
                    element.text(), matcher.group(1)));
        }

        return compilations;
    }
}
