package com.mathieuclement.lib.chlaw.skeleton;

import org.junit.Test;

import java.util.Collection;

import static com.mathieuclement.lib.chlaw.skeleton.CompilationTest.hasCompilation;
import static junit.framework.Assert.assertTrue;

public class CompilationsTest {

    @Test
    public void testGetCompilationsEnglish() throws Exception {
        Collection<Compilation> compilations = Compilations.getCompilations(Language.ENGLISH);
        assertTrue(hasCompilation(new Compilation("State - People - Authorities", "1"), compilations));
        assertTrue(hasCompilation(new Compilation("Private law - Administration of civil justice - Enforcement", "2"), compilations));
        assertTrue(hasCompilation(new Compilation("Criminal law – Administration of criminal justice – Execution of sentences", "3"), compilations));
        assertTrue(hasCompilation(new Compilation("Education - Science - Culture", "4"), compilations));
        assertTrue(hasCompilation(new Compilation("National defence", "5"), compilations));
        assertTrue(hasCompilation(new Compilation("Finance", "6"), compilations));
        assertTrue(hasCompilation(new Compilation("Public works - Energy - Transport", "7"), compilations));
        assertTrue(hasCompilation(new Compilation("Health - Employment – Social security", "8"), compilations));
        assertTrue(hasCompilation(new Compilation("Economy – Technical cooperation", "9"), compilations));
    }
}
