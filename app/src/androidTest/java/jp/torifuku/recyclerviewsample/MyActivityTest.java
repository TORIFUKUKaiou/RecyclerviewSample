package jp.torifuku.recyclerviewsample;

import android.test.ActivityInstrumentationTestCase2;

public class MyActivityTest extends ActivityInstrumentationTestCase2<MyActivity> {

    MyActivity mActivity;

    public MyActivityTest() {
        super(MyActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(false);

        mActivity = getActivity();
    }

    public void testActivity() {
        assertNotNull(mActivity);
        assertNotNull(mActivity.findViewById(R.id.sample_content_fragment));
    }
}