package packages.directory     ; //


import java.io.File;
import java.io.IOException;
//import java.net.URI;
//import java.nio.file.DirectoryStream;
//import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import packages.directory.util.FilesIgnorIgnoreFolder;

public class Packages {
	
	public  int quantityJavaFiles; //Field that describe quantity java files
	private Path pathDirectory;
	private String ignorFolder;
	
	private Map<Path, Path> mapFiles=new LinkedHashMap<>();
	
	public Packages(Path path, String ignorFolder) {
		this.pathDirectory=path;
		this.ignorFolder=ignorFolder;
	}
	
	
	private static String getFileExtension(File file) {
		if (!file.isFile()) return "";
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	

	public   Path searchPathPackage(Path javaFile) throws IOException{
		//Path result;
		if (!getFileExtension(javaFile.toFile()).equals("java")) return null;
		quantityJavaFiles++;
		
		// Read java file
		List<String> lines=new ArrayList<String>();
		try{
			lines=Files.readAllLines(javaFile);
			for(String line:lines){
				StringBuilder s= new StringBuilder();
				if( (line.contains("package"))&&(line.contains(";"))) {
					char[] c=line.toCharArray();
					for (int i=line.indexOf("package")+"package".length(); i<line.indexOf(";"); i++){		
						if (c[i]!=' ') 
							if (c[i]!='.') s.append(c[i]); else s.append("/");
					}	
					
					return Paths.get(s.toString());					
				}				
			}
		}catch (IOException e){
			
			System.err.println("Uppssss "+javaFile.toString()+"     "+e);
		}finally {
			// close resource
		}				
		return null;		
		
	}
	
	
	
	public Set<Path> getPathPackages() throws IOException{
		Set<Path> result=new LinkedHashSet<>();
		
		for(Path path:new FilesIgnorIgnoreFolder().getDirectoryIgnoreFolder(pathDirectory, ignorFolder)){
			try (Stream<Path> entries = Files.list(path)) {
				Iterator<Path> iterator=entries.iterator();
				while (iterator.hasNext()) {
					Path pathJava=iterator.next();
					///System.out.println(path.toString());
					Path pathPackageTemp=searchPathPackage(pathJava);
					if (pathPackageTemp!=null) {
						result.add(pathPackageTemp);
						mapFiles.put(pathJava, pathPackageTemp);
					}
					
				}
				
			}
		}						
		System.out.println("-------------");
		System.out.println(result.size());
		System.out.println(quantityJavaFiles);
		return result;
	}
	
	
	public Map<Path,Path>  getPathJavaFile(){
		return mapFiles;
	}
	
	
	
	
	
	
	
}  
