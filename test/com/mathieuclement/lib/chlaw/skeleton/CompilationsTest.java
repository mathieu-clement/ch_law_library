package com.mathieuclement.lib.chlaw.skeleton;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CompilationsTest {
    CloseableHttpClient httpClient;

    @Before
    public void setUp() throws Exception {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClient = httpClientBuilder.build();
    }

    @Test
    public void testGetCompilationsEnglish() throws Exception {
        List<Compilation> list = Compilations.getCompilations(Language.ENGLISH);
        assertEquals("State - People - Authorities", list.get(0).getName());
        assertEquals("Private law - Administration of civil justice - Enforcement", list.get(1).getName());
        assertEquals("Criminal law – Administration of criminal justice – Execution of sentences", list.get(2).getName());
        assertEquals("Education - Science - Culture", list.get(3).getName());
        assertEquals("National defence", list.get(4).getName());
        assertEquals("Finance", list.get(5).getName());
        assertEquals("Public works - Energy - Transport", list.get(6).getName());
        assertEquals("Health - Employment – Social security", list.get(7).getName());
        assertEquals("Economy – Technical cooperation", list.get(8).getName());
    }

    @After
    public void tearDown() throws Exception {
        httpClient.close();
    }
}
