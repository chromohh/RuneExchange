package com.example.runeexchange;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.runeexchange.LocalData.DBTools;
import com.example.runeexchange.model.ItemAsFavourite;

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

        assertEquals("com.example.runeexchange", appContext.getPackageName());
    }

    /*
    @Test
    public void someDbTesting(){
        DBTools db = new DBTools(InstrumentationRegistry.getInstrumentation().getTargetContext());
        ItemAsFavourite asd1 = new ItemAsFavourite(1, 43, "test1");
        ItemAsFavourite asd2 = new ItemAsFavourite(2, 13, "test2");
        ItemAsFavourite asd3 = new ItemAsFavourite(3, 34, "test3");

        db.addItemToDb(asd1);
        db.addItemToDb(asd2);
        db.addItemToDb(asd3);

        System.out.println(db.getAllFavourites().toString());
        assertEquals(db.getAllFavourites().size(), 3);
    }
    */
}
