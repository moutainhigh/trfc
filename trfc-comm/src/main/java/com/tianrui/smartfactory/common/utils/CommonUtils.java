package com.tianrui.smartfactory.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CommonUtils {  
      
    /** 
     * 通过对象得到所有的该对象所有定义的属性值 
     * @param object 目标对象 
     */  
    public static void method(Object object){  
       try{  
           Class<? extends Object> clazz = object.getClass();  
           Field[] fields = object.getClass().getDeclaredFields();//获得属性  
           for (Field field : fields) {  
               PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);  
               Method getMethod = pd.getReadMethod();//获得get方法  
               Object o = getMethod.invoke(object);//执行get方法返回一个Object  
               System.out.println(o);  
           }  
       }catch (Exception e) {  
           e.printStackTrace();  
       }   
     }  
      
    /** 
     * 通过对象和具体的字段名字获得字段的值 
     * @param object 目的对象 
     * @param filed 字段名 
     * @return 通过字段名得到字段值 
     */  
    public static Object method(Object object,String filed)   {  
       try {  
           Class<? extends Object> clazz = object.getClass();  
           PropertyDescriptor pd = new PropertyDescriptor(filed,clazz);  
           Method getMethod = pd.getReadMethod();//获得get方法  
           Object o = getMethod.invoke(object);//执行get方法返回一个Object  
           return o;  
       }catch (Exception e) {  
           e.printStackTrace();  
           return null;  
       }   
     }  
      
}  