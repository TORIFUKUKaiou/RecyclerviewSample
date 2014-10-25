package jp.torifuku.recyclerviewsample;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

public class ImageDetailActivityTest extends ActivityInstrumentationTestCase2<ImageDetailActivity> {

    ImageDetailActivity mActivity;

    public ImageDetailActivityTest() {
        super(ImageDetailActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(false);

        mActivity = getActivity();
    }

    public void testActivity() {
        assertNotNull(mActivity);
        assertNotNull(mActivity.findViewById(R.id.imageView));
    }

}