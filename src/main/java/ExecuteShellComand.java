import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteShellComand {
    private static final Logger logger = LogManager.getLogger(ExecuteShellComand.class);


	public static void main(String[] args) {

		ExecuteShellComand obj = new ExecuteShellComand();

		//in mac oxs
		String command = "whoami";

		//in windows
		//String command = "ping -n 3 " + domainName;

		String output = obj.executeCommand(command);

		String abc = "Hello World";

        logger.debug("output value,  {} ",abc);
	//	System.out.println(output);

	}

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}