package practice;

import java.util.Deque;
import java.util.LinkedList;

public class TempMain {

    public static void main(String[] args) {
        TempMain main = new TempMain();
        System.out.println(main.validBracket("[{}]"));
        System.out.println(main.validBracket("[{]"));
        System.out.println(main.validBracket("[](){}"));
    }

    private boolean validBracket(String str) {
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                char left = stack.pop();
                if (!match(left, ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean match(char left, char right) {
        if (left == '(') {
            return right == ')';
        }
        if (left == '[') {
            return right == ']';
        }
        if (left == '{') {
            return right == '}';
        }
        return false;
    }

}
