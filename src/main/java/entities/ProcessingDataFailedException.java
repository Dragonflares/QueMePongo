package entities;

import java.io.IOException;

@SuppressWarnings("serial")
public class ProcessingDataFailedException extends IOException {

	
	public ProcessingDataFailedException(String msg) {
        super(msg);
    }

    public ProcessingDataFailedException(String msg, Throwable t) {
        super(msg, t);
    }
	
	
	
	
}
