package com.tikal;

import com.tikal.dao.model.PhotoMetaData;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * Created by pavel_sopher on 09/05/2017.
 */
public class MockitoTest {
    @Mock
    PhotoMetaData photoMetaData;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
//
//    @Test
//    public void testQuery()  {
//        PhotoMetaData t  = new PhotoMetaData(photoMetaData);
//        boolean check = t.query("* from t");
//        assertTrue(check);
//        verify(databaseMock).query("* from t");
//    }
}
