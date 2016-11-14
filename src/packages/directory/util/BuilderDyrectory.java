package packages.directory.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.LinkedHashSet;
import java.util.Set;

public class BuilderDyrectory {
	
	private BuilderDyrectory(){
		
	}
	
	private static void buildDyrectory ( String where,  String what) throws IOException{
		java.util.List<String> list;
		if (what.contains("\\")) list=new ArrayList<>(Arrays.asList(what.split("\\\\"))); 
				else list=new ArrayList<>(Arrays.asList(what.split("/")));
		StringBuilder localDirectory=new StringBuilder(where);
		localDirectory.append("/");
		Path localPath;
		for (int i = 0; i < list.size(); i++) {
			localDirectory.append(list.get(i));
			localDirectory.append("/");
			localPath=(Paths.get(localDirectory.toString()));
			if (!Files.isDirectory(localPath))   Files.createDirectory(localPath);
		}
		
	}
	
	
	public static void buildDyrectories(String where,  Set<Path> set) throws IOException{
		
		for(Path path:set)
			buildDyrectory(where, path.toString());
			
	}
}


