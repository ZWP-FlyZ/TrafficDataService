package app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;



@Component
public class FileStorageUtil implements InitializingBean{

	private final Path rootLocation;
	
	
	public FileStorageUtil(){
		rootLocation = Paths.get("sessionId");
	}
	 
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
    
    
   
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    
    
    public String getMessageFrom(String fileName,String defString){
    	if(fileName==null) return defString;
		StringBuffer sb = new StringBuffer();
		BufferedReader br;
		try {
			File file = load(fileName).toFile();
			if(!file.exists()||file.isDirectory()) return defString;
			br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file),"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
			      sb.append(line);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return defString;
    }
    

    @Transactional
	public boolean setMessageTo(String fileName,String message){
    	if(fileName==null||message==null) return false;
    	BufferedWriter bw ;
    	try {
    		File file = load(fileName).toFile();
    		file.delete();
    		file.createNewFile();
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file),"UTF-8"));
			bw.write(message);
			bw.flush();
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
			return false;
		}    	
    }
    
    
    

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
        	System.err.println(e.toString());
        }
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		 init();
	}
    
	
	
}
