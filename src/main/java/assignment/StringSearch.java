package assignment;
import java.util.*;
public class StringSearch {
    // Counts character comparisons in search.
    public static long comparisons;
    public static List<Integer> naive(String text,String pattern) {
        if(pattern.isEmpty()) throw new IllegalArgumentException("Pattern must not be empty");
        comparisons=0; List<Integer> out=new ArrayList<>(); if(pattern.length()>text.length()) return out;
        for(int i=0;i<=text.length()-pattern.length();i++) { int j=0; while(j<pattern.length()) { comparisons++; if(text.charAt(i+j)!=pattern.charAt(j)) break; j++; } if(j==pattern.length()) out.add(i); } return out;
    }
    public static int[] lps(String p) {
        if(p.isEmpty()) throw new IllegalArgumentException("Pattern must not be empty");
        int[] lps=new int[p.length()]; int len=0,i=1; while(i<p.length()) { if(p.charAt(i)==p.charAt(len)) lps[i++]=++len; else if(len!=0) len=lps[len-1]; else lps[i++]=0; } return lps;
    }
    public static List<Integer> kmp(String text,String pattern) {
        if(pattern.isEmpty()) throw new IllegalArgumentException("Pattern must not be empty");
        comparisons=0; List<Integer> out=new ArrayList<>(); if(pattern.length()>text.length()) return out; int[] lps=lps(pattern); int i=0,j=0;
        while(i<text.length()) { comparisons++; if(text.charAt(i)==pattern.charAt(j)) {i++;j++; if(j==pattern.length()){out.add(i-j);j=lps[j-1];}} else if(j!=0) j=lps[j-1]; else i++; } return out;
    }
}
