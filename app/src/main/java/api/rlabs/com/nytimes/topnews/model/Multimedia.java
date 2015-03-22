
package api.rlabs.com.nytimes.topnews.model;

public class Multimedia{
   	private String caption;
   	private String copyright;
   	private String format;
   	private Number height;
   	private String subtype;
   	private String type;
   	private String url;
   	private Number width;

 	public String getCaption(){
		return this.caption;
	}
	public void setCaption(String caption){
		this.caption = caption;
	}
 	public String getCopyright(){
		return this.copyright;
	}
	public void setCopyright(String copyright){
		this.copyright = copyright;
	}
 	public String getFormat(){
		return this.format;
	}
	public void setFormat(String format){
		this.format = format;
	}
 	public Number getHeight(){
		return this.height;
	}
	public void setHeight(Number height){
		this.height = height;
	}
 	public String getSubtype(){
		return this.subtype;
	}
	public void setSubtype(String subtype){
		this.subtype = subtype;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
 	public Number getWidth(){
		return this.width;
	}
	public void setWidth(Number width){
		this.width = width;
	}
}
