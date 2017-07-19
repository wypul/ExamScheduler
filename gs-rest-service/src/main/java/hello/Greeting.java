package hello;

import java.io.Serializable;

public class Greeting implements Serializable {

    private String text;
    private String start;
    private String end;
    private String id;
    
    public void setText(String text) {
		this.text = text;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Greeting() {}
    
    public Greeting(String text, String start, String end,String id) {
        this.id = id;
        this.start=start;
        this.end=end;
        this.text = text;
    }

	public String getText() {
		return text;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getId() {
		return id;
	}

    
}