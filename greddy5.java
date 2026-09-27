//problem1
class Solution {
    public boolean isMatch(String s, String p) {
        int sp = 0, pp = 0;
        int sStar = -1, pStar = -1;

        while(sp < s.length()){
            if(pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }else if(pp < p.length() && p.charAt(pp) == '*'){
                pStar = pp;
                sStar = sp;
                pp++;
            }else{
                if(pStar == -1) return false;
                sStar++;
                sp = sStar;
                pp = pStar + 1;
            }
        }

        while(pp < p.length()){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }

        return true;
    }
}
//problem2
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<workers.length; i++){
            for(int j=0; j<bikes.length; j++){
                int dist = calculateDistance(workers[i], bikes[j]);
                map.putIfAbsent(dist, new ArrayList<>());

                map.get(dist).add(new int[]{i,j}); //worker, bike
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }

        int[] result = new int[workers.length];
        int count = 0;
        boolean[] workersAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[bikes.length];

        for(int i=min; i<=max; i++){
            if(!map.containsKey(i)) continue;
            
            List<int[]> list = map.get(i);
            for(int[] wb: list){
                int worker = wb[0];
                int bike = wb[1];

                if(!workersAssigned[worker] && !bikesAssigned[bike]){
                    result[worker] = bike;
                    workersAssigned[worker] = true;
                    bikesAssigned[bike] = true;
                    count++;
                    if(count == workers.length) return result;
                }
            }
        }
        return result;
    }

    private int calculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}
