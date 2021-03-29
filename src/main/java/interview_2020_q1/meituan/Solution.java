package interview_2020_q1.meituan;

import java.util.*;


public class Solution {
    /**
     *
     * @param s string字符串 only contains digit
     * @return string字符串ArrayList
     */
    public ArrayList<String> restoreIpAddresses (String s) {
        if(s.length() < 4) {
            return new ArrayList<>();
        }
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> ipSegments = new ArrayList<>();
        restoreIpAddressesCore(s, 0, ipSegments, result);
        return result;
    }

    private void restoreIpAddressesCore(
            String s, int start, ArrayList<String> ipSegments, ArrayList<String> result) {
        if(ipSegments.size() == 3) {
            if(isLegalIpSegment(s.substring(start, s.length()))) {
                ipSegments.add(s.substring(start));
                result.add(ipSegments.get(0) + "." + ipSegments.get(1) + "."
                        + ipSegments.get(2) + "." + ipSegments.get(3));
                ipSegments.remove(ipSegments.size() - 1);
            }
            return;
        }

        for(int i = start+1; i< s.length(); i++) {
            if(!isLegalIpSegment(s.substring(start, i))) {
                return;
            }
            ipSegments.add(s.substring(start, i));
            restoreIpAddressesCore(s, i, ipSegments, result);
            ipSegments.remove(ipSegments.size() - 1);
        }
    }

    private boolean isLegalIpSegment(String segment) {
        int ip = Integer.parseInt(segment);
        return ip>=0 && ip<=255;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.restoreIpAddresses("25525511125"));
    }

}