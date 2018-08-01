import org.junit.Assert;
import org.junit.Test;

public class ConstructorTest {

    @Test
    public void stringConstructorTest() {
        BooleanFunction booleanFunction = new BooleanFunction("1");
        Assert.assertEquals("1", booleanFunction.getStringRepresentation(false));
        Assert.assertEquals(0, booleanFunction.getArgumentsNumber());
        Assert.assertEquals(1, booleanFunction.getBasesNumber());

        booleanFunction = new BooleanFunction("11");
        Assert.assertEquals("11", booleanFunction.getStringRepresentation(false));
        Assert.assertEquals(1, booleanFunction.getArgumentsNumber());
        Assert.assertEquals(1, booleanFunction.getBasesNumber());

        booleanFunction = new BooleanFunction("1111");
        Assert.assertEquals("1111", booleanFunction.getStringRepresentation(false));
        Assert.assertEquals(2, booleanFunction.getArgumentsNumber());
        Assert.assertEquals(1, booleanFunction.getBasesNumber());

        booleanFunction = new BooleanFunction("11111111111111111111111111111111");
        Assert.assertEquals(
                "11111111111111111111111111111111",
                booleanFunction.getStringRepresentation(false)
        );
        Assert.assertEquals(5, booleanFunction.getArgumentsNumber());
        Assert.assertEquals(1, booleanFunction.getBasesNumber());

        booleanFunction = new BooleanFunction("1111111111111111111111111111111111111111111111111111111111111111");
        Assert.assertEquals(
                "1111111111111111111111111111111111111111111111111111111111111111",
                booleanFunction.getStringRepresentation(false)
        );
        Assert.assertEquals(6, booleanFunction.getArgumentsNumber());
        Assert.assertEquals(2, booleanFunction.getBasesNumber());
    }
}
