import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class HangmanTest {
    public HangmanMethods method= new HangmanMethods();
@Test
  void randomword()
{
    for(int i=0;i<20;i++)
    {
        System.out.println(method.randomword());
    }
}
@Test
  void alphabetic()
{
    assertFalse(method.alphabetic("3e"));
    assertTrue(method.alphabetic("q"));
    assertFalse(method.alphabetic("3"));
    assertFalse(method.alphabetic(null));
    assertFalse(method.alphabetic("=+="));
}
@Test
    void phase()
{
    assertEquals(method.phase(-1),"Not a valid turn number.");
    assertEquals(method.phase(7),"Not a valid turn number.");
    assertEquals(method.phase(3)," +---+\n O   |\n |   |\n/    |\n     |\n=======");
}
@Test
    void contains()
{
    assertEquals(method.contains(("rectangle").toCharArray(),'e'), new ArrayList<>(List.of(1, 8)));
    assertEquals(method.contains(("rectangle").toCharArray(),'s'),new ArrayList<Integer>());
    assertEquals(method.contains(("rectangle").toCharArray(),'3'),new ArrayList<Integer>());
}
@Test
    void hasBlanks()
{
    assertFalse(method.hasBlanks(("asjdfh").toCharArray()));
    assertTrue(method.hasBlanks(("asjd_h").toCharArray()));
    assertFalse(method.hasBlanks(("").toCharArray()));
    assertTrue(method.hasBlanks(("_sjfh").toCharArray()));
    assertTrue(method.hasBlanks(("asjdf_").toCharArray()));

}
}
