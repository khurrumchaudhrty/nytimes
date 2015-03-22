
package api.rlabs.com.nytimes.topnews.model;

import java.util.List;

/**
 * parsing only last updated timestamp and result count + result list
 */
public class TopNews{
   	private String copyright;
   	private String last_updated;
   	private Number num_results;
   	private List results;
   	private String status;

 	public String getCopyright(){
		return this.copyright;
	}
	public void setCopyright(String copyright){
		this.copyright = copyright;
	}
 	public String getLast_updated(){
		return this.last_updated;
	}
	public void setLast_updated(String last_updated){
		this.last_updated = last_updated;
	}
 	public Number getNum_results(){
		return this.num_results;
	}
	public void setNum_results(Number num_results){
		this.num_results = num_results;
	}
 	public List getResults(){
		return this.results;
	}
	public void setResults(List results){
		this.results = results;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
