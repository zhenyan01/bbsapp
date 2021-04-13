package com.example.bbsapp.activity;

import android.widget.Button;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginTest extends ActivityTestRule<Login> {
    private Login mLogin;
    private EditText musername, mpassword;
    private Button mloginButton, msignUpButton;

    public LoginTest(){
        super(Login.class);
        launchActivity(getActivityIntent());

        mLogin = getActivity();
        //musername = mLogin.getDrawable();
        musername = mLogin.getUsername();
        assertNotNull(musername);
        assertEquals(1,1);
    }
    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();


    }

    @Test
    public void testPreconditions() {
        assertNotNull(musername);
        assertEquals(1,1);
    }


    protected void afterActivityFinished() {
        super.afterActivityFinished();
        if (!getActivity().isFinishing()) {
            mLogin.finish();
        }
    }

}