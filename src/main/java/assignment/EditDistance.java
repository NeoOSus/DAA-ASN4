package assignment;

public class EditDistance {
    public static long operations;
    public interface Method { int distance(String a, String b); }

    // State: solve prefixes a[0..i) and b[0..j). Transition: match costs 0; otherwise 1 + min(delete, insert, replace).
    // Base case: an empty prefix needs the length of the other prefix.
    public static int recursive(String a, String b) { operations=0; return rec(a, b, a.length(), b.length()); }
    private static int rec(String a, String b, int i, int j) {
        operations++;
        if (i == 0) return j; if (j == 0) return i;
        int cost = a.charAt(i-1) == b.charAt(j-1) ? 0 : 1;
        return Math.min(Math.min(rec(a,b,i-1,j)+1, rec(a,b,i,j-1)+1), rec(a,b,i-1,j-1)+cost);
    }

    // State: dp[i][j] is the distance between prefixes of lengths i and j. Transition: min of deletion, insertion and replacement.
    // Base case: first row and column contain prefix lengths.
    public static int table(String a, String b) {
        operations=0;
        int[][] dp = new int[a.length()+1][b.length()+1];
        for(int i=0;i<=a.length();i++) dp[i][0]=i; for(int j=0;j<=b.length();j++) dp[0][j]=j;
        for(int i=1;i<=a.length();i++) for(int j=1;j<=b.length();j++) {
            operations++;
            int cost=a.charAt(i-1)==b.charAt(j-1)?0:1;
            dp[i][j]=Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1]+cost);
        } return dp[a.length()][b.length()];
    }

    // State: previous and current rows hold distances for adjacent prefixes. Transition: same recurrence as table.
    // Base case: previous row is 0..b.length; after each row, swap the two arrays.
    public static int twoRows(String a, String b) {
        operations=0;
        int[] prev=new int[b.length()+1], cur=new int[b.length()+1];
        for(int j=0;j<=b.length();j++) prev[j]=j;
        for(int i=1;i<=a.length();i++) { cur[0]=i; for(int j=1;j<=b.length();j++) {
            operations++;
            int cost=a.charAt(i-1)==b.charAt(j-1)?0:1; cur[j]=Math.min(Math.min(prev[j]+1,cur[j-1]+1),prev[j-1]+cost);
        } int[] tmp=prev;prev=cur;cur=tmp; } return prev[b.length()];
    }
}
