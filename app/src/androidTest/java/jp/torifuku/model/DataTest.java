package jp.torifuku.model;

import junit.framework.TestCase;

public class DataTest extends TestCase {

    public void testGetTitle() throws Exception {
        Data data = new Data("title", "url");
        assertEquals("getTitle", "title", data.getTitle());
    }

    public void testGetUrl() throws Exception {
        Data data = new Data("title", "url");
        assertEquals("getUrl", "url", data.getUrl());
    }
}