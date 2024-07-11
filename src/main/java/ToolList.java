import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToolList{
	final static String TOOLSPATH = "src/main/tools.tsv";
	private Map<String, Tool> toolList;
	List<String> toolCodes;
	public ToolList (){
		toolList = new HashMap<>();
		toolCodes = new ArrayList<>();
	}
	public void buildList(){

		try(Scanner scanner = new Scanner(new File(TOOLSPATH))){
			if(scanner.hasNextLine()){
				scanner.nextLine();
			}
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] cols = line.split("\t");

			if(cols.length == 3){
			String code = cols[0];
			String type = cols[1];
			String brand = cols[2];

		        Tool tool = new Tool(code,type,brand);
			toolList.put(code,tool);
			toolCodes.add(code);
			}
		}
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }


	}
	public Tool get(String code){
		return toolList.get(code);
	}
	public Tool getItem(int index){
		return toolList.get(toolCodes.get(index));
	}
	public int size(){
		return toolList.size();
	}


	
}
