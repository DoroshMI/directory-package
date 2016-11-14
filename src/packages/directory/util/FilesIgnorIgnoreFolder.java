package packages.directory.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FilesIgnorIgnoreFolder {
	
	
	
	private Set<Path> set=new LinkedHashSet<>();
	
	private  void getListDirectory(Path dir, String ignorFolder) throws IOException{		
		try(Stream<Path> entriesDir=Files.list(dir) ){
			
			Iterator<Path> iterator=entriesDir.iterator();
			if (!iterator.hasNext()) return;
			do {
				Path path=iterator.next();
				
				if (!path.toString().contains(ignorFolder)&&Files.isDirectory(path)) 
					{set.add(path); getListDirectory(path, ignorFolder);}
				
			} while(iterator.hasNext()); 		
		}		
	}
	
	
	
	public Set<Path> getDirectoryIgnoreFolder(Path path, String ignorFolder)throws IOException {
		getListDirectory(path, ignorFolder);
		return set;
	}
		
		
}
