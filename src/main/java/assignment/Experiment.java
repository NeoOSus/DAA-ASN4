package assignment;
import java.io.*; import java.nio.file.*; import java.util.*;
public class Experiment {
 static Random random=new Random(424242); static String word(int n){StringBuilder s=new StringBuilder();for(int i=0;i<n;i++)s.append((char)('a'+random.nextInt(4)));return s.toString();}
 static double median(List<Double> x){Collections.sort(x);return x.get(x.size()/2);}
 static void row(PrintWriter w,String alg,String input,int n,double ms,long count){w.printf(Locale.US,"%s,%s,%d,%.6f,%d%n",alg,input,n,ms,count);}
 public static void main(String[] args)throws Exception { Path out=Paths.get("results/results.csv");Files.createDirectories(out.getParent());try(PrintWriter w=new PrintWriter(Files.newBufferedWriter(out))){w.println("algorithm,input,n,time_ms,counter");
 int[] sizes={3,6,9,12}; for(int n:sizes){for(String type:new String[]{"random","same"}){String a=type.equals("same")?"a".repeat(n):word(n),b=type.equals("same")?a:word(n); for(String alg:new String[]{"recursive","table","twoRows"}) {List<Double> ts=new ArrayList<>();long ops=0;for(int r=0;r<3;r++){long start=System.nanoTime();if(alg.equals("recursive"))EditDistance.recursive(a,b);else if(alg.equals("table"))EditDistance.table(a,b);else EditDistance.twoRows(a,b);ts.add((System.nanoTime()-start)/1e6);ops+=EditDistance.operations;}row(w,alg,type,n,median(ts),ops/3); } }}
 int[] ns={1000,2000,4000,8000,16000}; for(int n:ns){for(String type:new String[]{"early_mismatch","late_match"}){String pat=type.equals("early_mismatch")?"b"+"a".repeat(31):"a".repeat(31)+"b";String text=type.equals("early_mismatch")?"a".repeat(n):"a".repeat(Math.max(0,n-1))+"b";for(String alg:new String[]{"naive","kmp"}){List<Double> ts=new ArrayList<>();long c=0;for(int r=0;r<3;r++){long st=System.nanoTime();if(alg.equals("naive"))StringSearch.naive(text,pat);else StringSearch.kmp(text,pat);ts.add((System.nanoTime()-st)/1e6);c+=StringSearch.comparisons;}row(w,alg,type,n,median(ts),c/3);}}}
 } System.out.println("Wrote "+out); }
}

