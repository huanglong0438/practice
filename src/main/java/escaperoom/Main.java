package escaperoom;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: donglongcheng
 * @Description:
 * @Date: Create in 23:27 2021/11/13
 */
public class Main {

    private static final int BLOCK = -8;

    private static final int DOG = 1;

    private static final int EYE = 2;

    private static final int FIRE = 3;

    private static final int FOX = 4;

    private static final int YIN = Integer.MIN_VALUE;

    private static final int YANG = Integer.MAX_VALUE;

    private static final Map<Integer, String> CARD_DICT = Maps.newHashMap();
    static {
        CARD_DICT.put(DOG, " 犬神 ");
        CARD_DICT.put(EYE, "百目鬼");
        CARD_DICT.put(FIRE, "不知火");
        CARD_DICT.put(FOX, "九尾狐");
    }

    private List<Integer> cards = Lists.newArrayList(DOG, -DOG, EYE, -EYE, FIRE, -FIRE, FOX, -FOX);

    private Set<Integer> usedCards = Sets.newHashSet();

    private int[][] matrix = new int[4][4];
    {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = BLOCK;
            }
        }
        matrix[0][2] = YIN;
        matrix[1][0] = YIN;matrix[1][1] = YIN;matrix[1][2] = YANG;
        matrix[2][1] = YANG;matrix[2][2] = YANG;matrix[2][3] = YIN;
        matrix[3][2] = YANG;
    }

    public int[][] solve() {
        dfs(new int[]{0, 2});
        return matrix;
    }

    private boolean dfs(int[] position) {
        if (position == null) {
            printMatrix();
            return true;
        }
        for (int card : cards) {
            if (usedCards.contains(card)) { // 是否已使用
                continue;
            }
            if (!matchYinYang(card, position)) { // 匹配阴阳
                continue;
            }
            usedCards.add(card);
            int stashed = matrix[position[0]][position[1]]; // 存储，回来回溯
            matrix[position[0]][position[1]] = card;
            if (!check(position)) { // 校验
                matrix[position[0]][position[1]] = stashed;
                usedCards.remove(card);
                continue;
            }
            if (dfs(nextPosition(position))) { // 搜索下一个位置
                matrix[position[0]][position[1]] = stashed;
                usedCards.remove(card);
                return true;
            }
            matrix[position[0]][position[1]] = stashed;
            usedCards.remove(card);
        }
        return false;
    }

    private boolean matchYinYang(int card, int[] position) {
        int cardType = matrix[position[0]][position[1]];
        if (cardType == YANG) {
            return card > 0;
        } else {
            return card < 0;
        }
    }

    private int[] nextPosition(int[] position) {
        int row = position[0];
        int col = position[1]+1;
        while (true) {
            if (col >= 4) {
                row++;
                col = 0;
            }
            if (row >= 4) {
                break;
            }
            if (matrix[row][col] != BLOCK) {
                return new int[]{row, col};
            }
            col++;
        }
        return null;
    }

    private void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == BLOCK) {
                    System.out.print("          " + "\t");
                } else {
                    int card = matrix[i][j];
                    String name = CARD_DICT.get(Math.abs(card));
                    String yinYang = card > 0 ? "阳" : "阴";
                    System.out.print(yinYang + "·" + name + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean check(int[] position) {
        int row = position[0];
        int col = position[1];
        if (row >= 1 && matrix[row - 1][col] != BLOCK) {
            // check top
            if (!doCheck(new int[]{row - 1, col})) {
                return false;
            }
        }
        if (col >= 1 && matrix[row][col - 1] != BLOCK) {
            // check left
            if (!doCheck(new int[]{row, col - 1})) {
                return false;
            }
        }
        return doCheck(position);
    }

    private boolean doCheck(int[] position) {
        int row = position[0];
        int col = position[1];
        List<int[]> alignPositions = getAlignPositions(position);
        if (!allFilled(alignPositions)) {
            return true;
        }
        int card = matrix[row][col];
        if (Math.abs(card) == DOG) {
            int countEye = 0;
            for (int[] alignPosition : alignPositions) {
                int alignCard = getCard(alignPosition);
                if (Math.abs(alignCard) == DOG) return false;
                if (Math.abs(alignCard) == FIRE) return false;
                if (Math.abs(alignCard) == EYE) countEye++;
            }
            if (countEye != 1) return false;
            return true;
        } else if (Math.abs(card) == EYE) {
            int countFire = 0;
            for (int[] alignPosition : alignPositions) {
                int alignCard = getCard(alignPosition);
                if (Math.abs(alignCard) == EYE) return false;
                if (Math.abs(alignCard) == FIRE) countFire++;
            }
            if (countFire != 1) return false;
            return true;
        } else if(Math.abs(card) == FIRE) {
            int countFox = 0;
            for (int[] alignPosition : alignPositions) {
                int alignCard = getCard(alignPosition);
                if (Math.abs(alignCard) == FIRE) return false;
                if (Math.abs(alignCard) == FOX)  countFox++;
            }
            if (countFox != 1) return false;
            return true;
        } else if (Math.abs(card) == FOX) {
            for (int[] alignPosition : alignPositions) {
                int alignCard = getCard(alignPosition);
                if (Math.abs(alignCard) == FOX) return false;
            }
            return true;
        }
        return false;
    }

    private int getCard(int[] position) {
        int r = position[0];
        int c = position[1];
        return matrix[r][c];
    }

    private boolean allFilled(List<int[]> alignPositions) {
        return alignPositions.stream().allMatch(alignPosition -> {
            int row = alignPosition[0];
            int col = alignPosition[1];
            return matrix[row][col] != YIN && matrix[row][col] != YANG;
        });
    }

    private List<int[]> getAlignPositions(int[] position) {
        List<int[]> alignPositions = Lists.newArrayList();
        int row = position[0];
        int col = position[1];
        if (row >= 1 && matrix[row - 1][col] != BLOCK) {
            alignPositions.add(new int[]{row - 1, col});
        }
        if (row + 1 < 4 && matrix[row + 1][col] != BLOCK) {
            alignPositions.add(new int[]{row + 1, col});
        }
        if (col >= 1 && matrix[row][col - 1] != BLOCK) {
            alignPositions.add(new int[]{row, col - 1});
        }
        if (col + 1 < 4 && matrix[row][col + 1] != BLOCK) {
            alignPositions.add(new int[]{row, col + 1});
        }
        return alignPositions;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

}
