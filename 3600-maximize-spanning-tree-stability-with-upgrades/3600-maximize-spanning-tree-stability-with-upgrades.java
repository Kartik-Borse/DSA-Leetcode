class Solution {

    class DSU{
        int[] p,r;

        DSU(int n){
            p=new int[n];
            r=new int[n];
            for(int i=0;i<n;i++) p[i]=i;
        }

        int find(int x){
            if(p[x]!=x) p[x]=find(p[x]);
            return p[x];
        }

        boolean union(int a,int b){
            int pa=find(a),pb=find(b);
            if(pa==pb) return false;

            if(r[pa]<r[pb]) p[pa]=pb;
            else if(r[pb]<r[pa]) p[pb]=pa;
            else{
                p[pb]=pa;
                r[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int lo=0,hi=200000,ans=-1;

        while(lo<=hi){
            int mid=(lo+hi)/2;

            if(can(n,edges,k,mid)){
                ans=mid;
                lo=mid+1;
            }else hi=mid-1;
        }

        return ans;
    }

    boolean can(int n,int[][] edges,int k,int x){

        DSU dsu=new DSU(n);
        int used=0,up=0;

        List<int[]> normal=new ArrayList<>();
        List<int[]> upgrade=new ArrayList<>();

        for(int[] e:edges){
            int u=e[0],v=e[1],s=e[2],must=e[3];

            if(must==1){
                if(s<x) return false;
                if(!dsu.union(u,v)) return false;
                used++;
            }else{
                if(s>=x) normal.add(e);
                else if(s*2>=x) upgrade.add(e);
            }
        }

        for(int[] e:normal){
            if(used==n-1) break;
            if(dsu.union(e[0],e[1])) used++;
        }

        for(int[] e:upgrade){
            if(used==n-1) break;
            if(up==k) break;

            if(dsu.union(e[0],e[1])){
                used++;
                up++;
            }
        }

        return used==n-1;
    }
}