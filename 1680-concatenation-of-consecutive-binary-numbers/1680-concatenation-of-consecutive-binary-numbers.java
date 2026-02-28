class Solution {
    public int concatenatedBinary(int n) {
        long mod=1000000007;
        long ans=0;
        for(int i=1;i<=n;i++){
            int bits =32 - Integer.numberOfLeadingZeros(i);
            ans=(ans<<bits) % mod;
            ans=(ans+i)%mod;
        }
        return (int)ans;
    }
}