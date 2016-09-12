package nju.citicup.bl;

import com.google.common.collect.BiMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Map;

@Component
@ConfigurationProperties
        (locations = "classpath:targetNameMap.yml")
public class FutureNameMapper {


    String name;

    Map<String,String> mapper;

    BiMap<String,String> biMapper;

    @PostConstruct
    protected void init(){
        
    }

    public String getFuturesName(String futuresId){
        return "";
    }

    public String getFuturesId(String futuresName){
        return "";
    }

    public Map<String, String> getMapper() {
        return mapper;
    }

    public void setMapper(Map<String, String> mapper) {
        this.mapper = mapper;
    }


}
