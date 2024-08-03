package Com.Example.Demo.Exception;

public class ExceptionData {
	
	
	
	private String uri;
	
	private String errormsg;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	
	@Override
	public String toString() {
		return "ExceptionData [uri=" + uri + ", errormsg=" + errormsg + "]";
	}
	
	
	
	
	

}
