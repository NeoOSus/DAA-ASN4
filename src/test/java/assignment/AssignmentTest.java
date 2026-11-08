package assignment;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*; import java.util.*;
class AssignmentTest {
 @Test void distanceReferencesAndRandom(){assertEquals(3,EditDistance.table("kitten","sitting"));assertEquals(2,EditDistance.table("flaw","lawn"));assertEquals(5,EditDistance.table("intention","execution"));assertEquals(3,EditDistance.table("","abc"));assertEquals(0,EditDistance.table("abc","abc"));Random r=new Random(7);for(int k=0;k<100;k++){String a=rand(r),b=rand(r);assertEquals(EditDistance.table(a,b),EditDistance.recursive(a,b));assertEquals(EditDistance.table(a,b),EditDistance.twoRows(a,b));}}
 String rand(Random r){StringBuilder s=new StringBuilder();for(int i=r.nextInt(7);i>0;i--)s.append((char)('a'+r.nextInt(3)));return s.toString();}
 @Test void lpsReferences(){assertArrayEquals(new int[]{0,1,2,0,1,2,3,3},StringSearch.lps("AAACAAAA"));assertArrayEquals(new int[]{0,0,1,2,0,1,2,3,4},StringSearch.lps("ABABCABAB"));}
 @Test void searchesAndEdges(){assertEquals(List.of(10),StringSearch.kmp("ABABDABACDABABCABAB","ABABCABAB"));assertEquals(List.of(0,1,2,3),StringSearch.naive("AAAAA","AA"));assertEquals(List.of(0,2,4),StringSearch.kmp("ABABABA","ABA"));assertThrows(IllegalArgumentException.class,()->StringSearch.kmp("abc",""));assertTrue(StringSearch.naive("a","long").isEmpty());Random r=new Random(9);for(int x=0;x<100;x++){String t=rand(r)+rand(r),p=rand(r);if(p.isEmpty())p="a";assertEquals(StringSearch.naive(t,p),StringSearch.kmp(t,p));}}
}
