package cz.codecamp.logger.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by honzapua on 16.10.2016.
 */
public class ExtractFileExtensionTest {

    @Test
    public void testExtensionExists() throws Exception {
        String expected = "ext";
        String actual = FileUtils.extractFileExtension("file.ext");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testNoExtension() throws Exception {
        Assert.assertNull(FileUtils.extractFileExtension("file"));
    }

}
