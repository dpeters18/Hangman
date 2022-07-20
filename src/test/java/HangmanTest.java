import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class HangmanTest {
    public HangmanMethods method= new HangmanMethods();
@Test
  void randomWord()
{
    for(int i=0;i<20;i++)
    {
        System.out.println(method.randomWord());
    }
}
@Test
  void alphabetic()
{
    assertFalse(method.isAlphabetic("3e"));
    assertTrue(method.isAlphabetic("q"));
    assertFalse(method.isAlphabetic("3"));
    assertFalse(method.isAlphabetic(null));
    assertFalse(method.isAlphabetic("=+="));
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
    assertEquals(method.contains("rectangle","e"), new ArrayList<>(List.of(1, 8)));
    assertEquals(method.contains("rectangle","s"),new ArrayList<Integer>());
    assertEquals(method.contains("rectangle","3"),new ArrayList<Integer>());
}
@Test
    void hasBlanks()
{
    assertFalse(method.hasBlanks("asdjlfbgkdjvxzvgkbft"));
    assertTrue(method.hasBlanks("asjd_h"));
    assertFalse(method.hasBlanks(""));
    assertTrue(method.hasBlanks("_sjfh"));
    assertTrue(method.hasBlanks("asjdf_"));


}

}
