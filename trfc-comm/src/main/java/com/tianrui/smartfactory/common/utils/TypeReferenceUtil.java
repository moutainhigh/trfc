package com.tianrui.smartfactory.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.tianrui.smartfactory.common.api.ApiParam;

public class TypeReferenceUtil {
	
	public static Map<String,Type> map=new HashMap<String,Type>();
	
	public static <T> Type getTypeReference(Class<T> clazz){
		if(!map.containsKey(clazz.toString())){
			map.put(clazz.toString(), type(ApiParam.class,clazz));
		}
		return map.get(clazz.toString());
	}
	
	static ParameterizedType type(final Class<?> raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
