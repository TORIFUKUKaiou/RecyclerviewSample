package jp.torifuku.recyclerviewsample.model;

import junit.framework.TestCase;

public class DataTest extends TestCase {

    public void testGetTitle() throws Exception {
        Data data = new Data("title", "thumbnail", "hogehoge");
        assertEquals("getTitle", "title", data.getTitle());
    }

    public void testGetThumbnailUrl() throws Exception {
        Data data = new Data("title", "thumbnail", "hogehoge");
        assertEquals("getThumbnailUrl", "thumbnail", data.getThumbnailUrl());
    }
}