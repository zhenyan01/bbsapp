package com.example.bbsapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.AndroidJUnit4;

import com.example.bbsapp.activity.Login;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.bbsapp", appContext.getPackageName());
        //assertEquals(1,0);
    }

//    public void LoginTest(){
//        super(Login.class);
//        launchActivity(getActivityIntent());
//
//        mLogin = getActivity();
//        //musername = mLogin.getDrawable();
//        musername = mLogin.getUsername();
//        assertNotNull(musername);
//        assertEquals(1,0);
//    }
}