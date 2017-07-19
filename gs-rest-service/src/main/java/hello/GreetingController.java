package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api/get")
    public List<Greeting> get(@RequestParam(value="name", defaultValue="World") String name) {
        List<Greeting> result = new ArrayList<Greeting>();
        File folder = new File("../Database/");
        for (final File fileEntry : folder.listFiles()) {
        	InputStream fileIs = null;
            ObjectInputStream objIs = null;
            try {
				fileIs = new FileInputStream("../Database/" + fileEntry.getName());
				objIs = new ObjectInputStream(fileIs);
				Greeting greeting = (Greeting) objIs.readObject();
				System.out.println("Loaded object with text: " + greeting.getText());
				result.add(greeting);
				objIs.close();
				fileIs.close();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
        }
//    	result.add(new Greeting("Monday Meeting","2017-01-02T10:00:00",
//        		"2017-01-02T13:00:00","lid2lwibsna37ltimymvlivsyq"));
    	return result;
    }
    
    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<Greeting> create(@RequestBody Greeting greeting)
    {
    	Integer currentId = 0;
    	FileReader fileReader;
		try {
			fileReader = new FileReader(new File("../Database/id.txt"));
			BufferedReader br = new BufferedReader(fileReader);
			currentId = Integer.parseInt(br.readLine());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    	OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try {
        	greeting.setId(currentId.toString());
            ops = new FileOutputStream("../Database/" + greeting.getId() + ".txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(greeting);
            objOps.flush();
            FileWriter writer = new FileWriter(new File("../Database/id.txt"));
            BufferedWriter bw = new BufferedWriter(writer);
            currentId++;
            bw.write(currentId.toString());
            bw.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(objOps != null) objOps.close();
            } catch (Exception ex){
                 
            }
        }
        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }
}