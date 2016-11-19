package groovy;

import java.io.File;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class EmbedGroovy {
	
	private Binding binding = new Binding();
	 
    public Object getProperty(String name) {
        return binding.getProperty(name);
    }
 
    public void setParameters(String[] paramNames, Object[] paramValues) {
        int len = paramNames.length;
        if (len != paramValues.length) {
            System.out.println("parameters not match!");
        }
 
        for (int i = 0; i < len; i++) {
            binding.setProperty(paramNames[i], paramValues[i]);
        }
    }
 
    public Object runScript(String scriptName) {
        GroovyShell shell = new GroovyShell(binding);
        try {
            return shell.evaluate(new File(scriptName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    public static void main(String[] args) {
        EmbedGroovy embedGroovy = new EmbedGroovy();
        String[] paramNames = { "foo" };
        Object[] paramValues = { new Integer(2) };
        embedGroovy.setParameters(paramNames, paramValues);
        Object result = embedGroovy.runScript("src/main/java/groovy/Foo.groovy");
        System.out.println(result);
        System.out.println(embedGroovy.getProperty("foo"));
        System.out.println(embedGroovy.getProperty("x"));
    }
}
