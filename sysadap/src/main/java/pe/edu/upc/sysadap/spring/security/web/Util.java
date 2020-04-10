package pe.edu.upc.sysadap.spring.security.web;

import java.util.HashMap;

public class Util {

	public static void main(String[] args) {
		HashMap<String, String> obj = new HashMap<>();
		obj.put("nombre", "Angelo");
		obj.put("edad", "22");
		
		String nombre = "abc";
		
		if(obj.containsKey("edad") && "ab3c".contains(nombre)) {
			System.out.println(obj.get("edad"));
			
		}
	}
}
