package com.mathieuclement.lib.chlaw.skeleton;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class CompilationTest {

    @Test
    public void testGetCompilationsOnly() throws Exception {
        Compilation compilation = new Compilation("Etat – Peuple – Autorités", "1");
        List<Compilation> compilations = compilation.getCompilations(Language.FRENCH);
        Assert.assertTrue(hasCompilation(new Compilation("Constitution fédérale", "10"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation("Armoiries. Siège de la Confédération. Fête nationale", "11"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation("Sécurité de la Confédération", "12"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation("Confédération et Cantons", "13"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation("Droit de cité. Etablissement. Séjour", "14"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation("Droits fondamentaux", "15"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation("Droits politiques", "16"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation("Autorités fédérales", "17"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation("Etat et église", "18"), compilations));
        Assert.assertTrue(hasCompilation(new Compilation(
                "Relations diplomatiques et consulaires. " +
                        "Organisations internationales. " +
                        "Règlement des conflits internationaux. " +
                        "Présence de la Suisse à l'étranger",
                "19"), compilations));
    }

    @Test
    public void testCompilationsWithLaws() throws Exception {
        Compilation compilation = new Compilation("Propriété intellectuelle et protection des données", "23");
        List<Compilation> compilations = compilation.getCompilations(Language.FRENCH);
        Assert.assertTrue(hasLaw(new Law(Compilation.UNKNOWN_NAME, "172.010.31"), compilations));
        Assert.assertTrue(hasLaw(new Law(Compilation.UNKNOWN_NAME, "173.41"), compilations));
        Assert.assertTrue(hasLaw(new Law(
                "Loi fédérale du 9 octobre 1992 sur le droit d’auteur et les droits voisins (Loi sur le droit d’auteur, LDA)",
                "231.1", "19920251"), compilations));
    }

    public static boolean hasCompilation(Compilation compilation, Collection<Compilation> compilations) {
        boolean found = false;
        for (Compilation comp : compilations) {
            if (comp.equals(compilation)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean hasLaw(Law law, Collection<Compilation> compilations) {
        boolean found = false;
        for (Compilation c : compilations) {
            if (c instanceof Law && ((Law) c).equals(law)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
