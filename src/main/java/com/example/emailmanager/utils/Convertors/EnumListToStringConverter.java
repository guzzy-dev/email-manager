package com.example.emailmanager.utils.Convertors;

import com.example.emailmanager.Model.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class EnumListToStringConverter implements AttributeConverter<List<Role>, String> {
    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        String result = "";
        if (roles.isEmpty()) return result;
        for (int pos = 0; pos< roles.size(); pos++) {
            result += roles.get(pos).name() + ",";
        }
        return result.substring(0, result.length()-1);
    }

    @Override
    public List<Role> convertToEntityAttribute(String s) {
        List<String> stringList = Arrays.stream(s.split(",")).toList();
        List<Role> roles = new ArrayList<>();
        for (String strRole: stringList){
            roles.add(Role.valueOf(strRole));
        }
        return roles;
    }
}
