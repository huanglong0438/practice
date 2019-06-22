package practice.serialize;

import java.io.Serializable;

/**
 * @Description: $description$
 * @Param: $params$
 * @return: $returns$
 * @Author: donglongcheng01
 * @Date: $date$
 */
public class FuckerInfo implements Serializable{

    private String fuckName;

    private String fuckWho;

    public String getFuckName() {
        return fuckName;
    }

    public void setFuckName(String fuckName) {
        this.fuckName = fuckName;
    }

    public String getFuckWho() {
        return fuckWho;
    }

    public void setFuckWho(String fuckWho) {
        this.fuckWho = fuckWho;
    }
}
