package practice;


import java.util.List;

/**
 * UrlPromotionPage
 *
 * @title UrlPromotionPage
 * @Description
 * @Author donglongcheng01
 * @Date 2020-02-28
 **/
public class UrlPromotionPage {

    private String onlineUrl;

    private List<Integer> ocpcTransTypeList;

    private Double factor;

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public String getOnlineUrl() {
        return onlineUrl;
    }

    public void setOnlineUrl(String onlineUrl) {
        this.onlineUrl = onlineUrl;
    }

    public List<Integer> getOcpcTransTypeList() {
        return ocpcTransTypeList;
    }

    public void setOcpcTransTypeList(List<Integer> ocpcTransTypeList) {
        this.ocpcTransTypeList = ocpcTransTypeList;
    }
}
