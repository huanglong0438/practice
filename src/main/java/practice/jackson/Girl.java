package practice.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Girl
 *
 * @title Girl
 * @Description
 * @Author donglongcheng01
 * @Date 2020-07-28
 **/
@Data
public class Girl {

    @JsonProperty("h")
    private Integer hight;

    @JsonProperty("w")
    private Integer weight;

    @JsonProperty("n")
    private String name;

}
