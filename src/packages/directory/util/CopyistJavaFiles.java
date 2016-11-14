package packages.directory.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Map;

public class CopyistJavaFiles {
	
	static int  numberOfCopyJava;
	
	private CopyistJavaFiles(){
		
	}
	public static void copydJavaFile ( Path where,  Path whatJava, Path whatPackage) throws IOException{
		
		
		StringBuilder newJavaFile=new StringBuilder(where.toString());
		newJavaFile.append("/");		
		newJavaFile.append(whatPackage.toString());
		newJavaFile.append("/");
		newJavaFile.append(whatJava.getFileName());
		
		//numberOfCopyJava++;
		if (!Paths.get(newJavaFile.toString()).toFile().isFile()) {
			Files.copy(whatJava, Paths.get(newJavaFile.toString()));
			numberOfCopyJava++;
		} else System.out.println(whatJava.toString());
		
		
		
	}
	
	public static int copyJavaFiles(String where, Map<Path,Path> what) throws IOException{
		numberOfCopyJava=0;
		for(Path pathJava:what.keySet()){
			copydJavaFile(Paths.get(where),pathJava,what.get(pathJava));
		}
		return numberOfCopyJava;
	}
}
