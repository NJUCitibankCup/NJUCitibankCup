package nju.citicup.bl;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
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
        biMapper = HashBiMap.create();
        biMapper.putAll(mapper);
    }


    public String convert(String former){
        if (former.length()>=4 && former.matches("\\D{1,2}\\d{4}")){
            String type = former.substring(0,former.length()-4);
            String typeName = convertType(type);
            return typeName + former.substring(former.length()-4);
        }
        return convertType(former);

    }

    public String cut(String former){
        String type = former.substring(0, former.length()-4);
        return type;
    }

    private String convertType(String type){
        if(biMapper.containsKey(type)){
            return biMapper.get(type);
        }else {
            return biMapper.inverse().get(type);
        }
    }

    public Map<String, String> getMapper() {
        return mapper;
    }

    public void setMapper(Map<String, String> mapper) {
        this.mapper = mapper;
    }


}
