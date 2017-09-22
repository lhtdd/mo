package com.lyao.mo.common.properties;
/**
 * 简单工厂类实现加载不同的properties文件属性值
 * @author lenovo
 *
 */
public class PropertiesFactory {
	
	public static Object create(Class<?> clazz) {  
        if (clazz.getName().equals(SystemProperties.class.getName())) {  
            return createSystemProperties();  
        }else if(clazz.getName().equals(QQMailProperties.class.getName())){
        	return createQQMailProperties();  
        }
        return null;  
    }  
      
    private static SystemProperties createSystemProperties() {  
        return new SystemProperties("systemFiles/system.properties");  
    } 
    private static QQMailProperties createQQMailProperties() {  
    	return new QQMailProperties("systemFiles/qqmail.properties");  
    } 
    
    public static void main(String[] args) {
    	SystemProperties sysProperties = (SystemProperties) PropertiesFactory.create(SystemProperties.class);
    	System.out.println(sysProperties.getProperty("mail.username"));
	}
}
