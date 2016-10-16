package cz.codecamp.logger.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by honzapua on 16.10.2016.
 */
public class ExtractFileNameTest {

    @Test
    public void testExtract_1() throws Exception {
        String expected = "file";
        String actual = FileUtils.extractFileName("file.ext");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExtract_2() throws Exception {
        String expected = "file.name";
        String actual = FileUtils.extractFileName("file.name.ext");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExtract_3() throws Exception {
        String expected = "file_ext";
        String actual = FileUtils.extractFileName("file_ext");

        Assert.assertEquals(expected, actual);
    }

}
