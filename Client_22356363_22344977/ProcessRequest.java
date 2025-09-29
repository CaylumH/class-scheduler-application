package Client_22356363_22344977;

import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ProcessRequest extends Task<String> {     //Ref: AR, JavaFX_Concurrent, CS4076
    // Use of javafx.concurrent allows gui to be controlled by main thread,
    // Messages to server don't block main thread as they are processed in a separate one through Javafx.concurrent.Task
    String request;
    BufferedReader in;
    PrintWriter out;

    ProcessRequest(String request, BufferedReader in, PrintWriter out){
        this.request = request;
        this.in = in;
        this.out = out;
    }

    @Override
    protected String call() throws Exception {
        out.println(request);
        return in.readLine();
    }
}
