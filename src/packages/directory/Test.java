package  packages.directory;//

//import java.io.File;
import java.io.IOException;
//import java.net.URL;
//import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.Iterator;
import java.util.Map;
import java.util.Set;
//import java.util.stream.Stream;

import packages.directory.util.BuilderDyrectory;
import packages.directory.util.CopyistJavaFiles;
//import packages.directory.util.FilesIgnorIgnoreFolder;

public class Test {

	public static void main(String[] args) throws IOException{
		
				
		
		Packages packageSpring=new Packages(Paths.get("D:\\Новая папка\\soap-samples-master"), "test");
		Set<Path> resultPackage=packageSpring.getPathPackages();
		Map<Path,Path> mapJavaFile=packageSpring.getPathJavaFile(); 
		
		
		BuilderDyrectory.buildDyrectories("D:\\javaee", resultPackage);
		
		System.out.println(CopyistJavaFiles.copyJavaFiles("D:\\javaee", mapJavaFile));
		System.out.print("Successful Operation!!!!!");
	}
}
