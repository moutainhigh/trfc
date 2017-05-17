package com.tianrui.web.smvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记需要进行Token有效性验证的方法
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiAuthValidation {
	
	//子系统分类  1:门岗 2:卡务 3客商APP
	String callType();
	
}
