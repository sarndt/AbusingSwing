package net.abusingjava.swing.types;

public class JavaType {
	
	final Class<?> $class;
	
	public JavaType(final String $javaType) throws ClassNotFoundException {
		Class<?> $class = null;
		if ("string".equalsIgnoreCase($javaType))
			$class = java.lang.String.class;
		else if ("byte".equalsIgnoreCase($javaType))
			$class = byte.class;
		else if ("short".equalsIgnoreCase($javaType))
			$class = int.class;
		else if ("long".equalsIgnoreCase($javaType))
			$class = long.class;
		else if ("int".equalsIgnoreCase($javaType) || "integer".equalsIgnoreCase("javaType"))
			$class = int.class;
		else if ("boolean".equalsIgnoreCase($javaType) || "bool".equalsIgnoreCase("javaType"))
			$class = boolean.class;
		else if ("float".equalsIgnoreCase($javaType))
			$class = float.class;
		else if ("double".equalsIgnoreCase($javaType))
			$class = double.class;
		else if ("date".equalsIgnoreCase($javaType))
			$class = java.util.Date.class;
		
		if ($class == null)
			$class = Class.forName($javaType);
		
		this.$class = $class;
	}
	
	public JavaType(final Class<?> $theClass) {
		$class = $theClass;
	} 
	
	public Class<?> getJavaType() {
		return $class;
	}
}