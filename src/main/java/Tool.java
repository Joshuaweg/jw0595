class Tool{
private String code;
private String type;
private String brand;
//creates object from record in tools.tsc
public Tool(){
	code = "";
	type = "";
	brand = "";
   }
public Tool(String cd, String tp, String br){
	code = cd;
	type=tp;
	brand=br;
   }
public String getType(){
	return type;
}
public String getCode(){
	return code;
}
public String getBrand(){
	return brand;
}
}
