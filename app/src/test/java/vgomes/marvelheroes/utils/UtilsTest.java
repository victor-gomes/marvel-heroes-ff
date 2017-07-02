package vgomes.marvelheroes.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by victorgomes on 02/07/17.
 */

public class UtilsTest {

    /**
     * Test MD5 hashing algorithm to validate if its working as expected.
     */
    @Test
    public void testMD5() {
        Assert.assertEquals(Utils.md5("1abcd1234"), "ffd275c5130566a2916217b101f26150");
    }
}
