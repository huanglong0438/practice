package practice.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Maps;
import org.junit.Test;
import practice.Week;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * JacksonTest
 *
 * @title JacksonTest
 * @Description
 * @Author donglongcheng01
 * @Date 2020-03-23
 **/
public class JacksonTest {

    private static ObjectMapper webMapper;

    static {
        webMapper = new ObjectMapper();
        webMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        webMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        webMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        webMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        webMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        SimpleModule webSm = new SimpleModule("DateModule", new Version(1, 0, 0, null));
        webSm.addDeserializer(Date.class, new WebDateDeserializer());
        webSm.addSerializer(new WebDateSerializer());
        webMapper.registerModule(webSm);
    }

    private static final ObjectMapper mapper = webMapper;

    static class WebDateDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return dateFormat.parse(jsonParser.getText());
            } catch (ParseException e) {
                    // nothing to do
                }
            throw new IllegalArgumentException("date format is invalid, only support: [yyyy-MM-dd] or [yyyy-MM-dd "
                    + "HH:mm:ss]");
        }
    }

    static class WebDateSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeString(new SimpleDateFormat("yyyy-MM-dd").format(date));
        }

        @Override
        public Class<Date> handledType() {
            return Date.class;
        }
    }

    @Test
    public void testJackSon() {
        ObjectNode response = mapper.createObjectNode();
        Map<String, Object> map = Maps.newHashMap();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", new Date());
        map.put("4", Week.MONDAY);
        JsonNode resultNode = mapper.convertValue(map, JsonNode.class);
        System.out.println(resultNode);
    }

    @Test
    public void testSerialization() throws JsonProcessingException {
        Map<Integer, String> map = Maps.newHashMap();
        map.put(1, "111");
        map.put(2, "222");
        map.put(3, "333");

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(map));
    }

    @Test
    public void testDeSerialization() throws JsonProcessingException {
        String json = "{\"1\":\"111\",\"2\":\"222\",\"3\":\"333\"}";
        Map<Integer, String> map = mapper.readValue(json, new TypeReference<Map<Integer, String>>() {
        });
        System.out.println(map);
    }

    @Test
    public void testSublinkParse() throws Exception {
        String json = "{\"topic\":\"\",\"linkUrl\":\"https://m.baidu.com\",\"tag\":\"费用\",\"linkText\":\"雅思考试报名费用\", \"num\":1}";
        Map<String, Object> map = mapper.readValue(json, Map.class);
        String linkText = (String) map.get("linkText");
        map.put("linkText", linkText.replace("雅思", "托福"));
        map.put("null", null);

        int num = (int) map.get("num");
        System.out.println(num);
        System.out.println(mapper.writeValueAsString(map));
    }

    @Test
    public void testJsonProperty() throws Exception {
        String json = "{\n" +
                "    \"n\": \"Black Rose\",\n" +
                "    \"h\": 163,\n" +
                "    \"w\": 48\n" +
                "}";
        System.out.println(json);
        Method method = GirlService.class.getDeclaredMethod("findGirl");
        Girl girl = mapper.readValue(json, TypeFactory.defaultInstance().constructType(method.getGenericReturnType()));
        System.out.println(girl);
    }

}
