package clickMaxOptlog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 不用搞了。。。
 *
 * Created by donglongcheng01 on 2017/12/19.
 */
public class Main {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("bigdata_research_full.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            FileInputStream fis2 = new FileInputStream("bigdata_research_full.csv");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fis));
            String str = null;
            while ((str = br.readLine()) != null) {
                String uidStr = str.split(",")[0];

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
