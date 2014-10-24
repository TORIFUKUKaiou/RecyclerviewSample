package jp.torifuku.net;

import junit.framework.TestCase;

public class BitmapDownloaderTest extends TestCase {

    public void testDownload() throws Exception {
        // テストするデバイスはインターネットにつながっていること
        // 画像のパスは使えなくなるかも？
        assertNotNull("Bitmapが取得できること", BitmapDownloader.download("https://lh6.ggpht.com/8KvifSf3U8NtBAH3ic27yuYzUHccC5FpdC0EK6qwYg6BqB2bHXlTuZ6rbSw0EZ4-l8f0=w300-rw"));

        assertNull("Bitmapが取得できない", BitmapDownloader.download(null));
    }
}