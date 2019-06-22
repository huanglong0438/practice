package Amazon;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int findDifferentWays(int size, int allowedChanges, String str)
    {
        // WRITE YOUR CODE HERE
        int max = 0;
        int cnt = 0;
        int maxs = 0;
        for (int i = 0; i < size; i++){
            if(str.charAt(i) == '0'){
                // replace 0 to 1
                String newStr = str.substring(0,i)+"1"+str.substring(i+1,size);
                for(int j = 0; j < size; j++){
                    if(newStr.charAt(j) == '1'){
                        cnt++;
                    }
                    else{
                        if(cnt > max){
                            max = cnt;
                            maxs = 1;
                        }
                        else if(cnt!=0 && cnt == max){
                            maxs++;
                        }
                        cnt = 0;
                    }

                }
                if(cnt > max){
                    max = cnt;
                    maxs = 1;
                }
                else if(cnt != 0 && cnt == max){
                    maxs++;
                }
                cnt = 0;
            }
        }
        return maxs;
    }
    // METHOD SIGNATURE ENDS

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findDifferentWays(5,1 ,"01010"));
    }
}