
package api.rlabs.com.nytimes.topnews.model;

import java.util.List;

/**
 * ignoring all the list members as it would be beyond the scope of the example will work with
 * parsing only hand full of items here
 *
 * list of this will also serve the adapter it self :)
 */
public class Results{
   	private String abstractnews;
   	private String byline;
   	private String created_date;
   	private List des_facet;
   	private List geo_facet;
   	private String item_type;
   	private String kicker;
   	private String material_type_facet;
   	private List multimedia;
   	private List org_facet;
   	private String per_facet;
   	private String published_date;
   	private String section;
   	private String subsection;
   	private String title;
   	private String updated_date;
   	private String url;

 	public String getAbstract(){
		return this.abstractnews;
	}
	public void setAbstract(String abstractnews){
		this.abstractnews = abstractnews;
	}
 	public String getByline(){
		return this.byline;
	}
	public void setByline(String byline){
		this.byline = byline;
	}
 	public String getCreated_date(){
		return this.created_date;
	}
	public void setCreated_date(String created_date){
		this.created_date = created_date;
	}
 	public List getDes_facet(){
		return this.des_facet;
	}
	public void setDes_facet(List des_facet){
		this.des_facet = des_facet;
	}
 	public List getGeo_facet(){
		return this.geo_facet;
	}
	public void setGeo_facet(List geo_facet){
		this.geo_facet = geo_facet;
	}
 	public String getItem_type(){
		return this.item_type;
	}
	public void setItem_type(String item_type){
		this.item_type = item_type;
	}
 	public String getKicker(){
		return this.kicker;
	}
	public void setKicker(String kicker){
		this.kicker = kicker;
	}
 	public String getMaterial_type_facet(){
		return this.material_type_facet;
	}
	public void setMaterial_type_facet(String material_type_facet){
		this.material_type_facet = material_type_facet;
	}
 	public List getMultimedia(){
		return this.multimedia;
	}
	public void setMultimedia(List multimedia){
		this.multimedia = multimedia;
	}
 	public List getOrg_facet(){
		return this.org_facet;
	}
	public void setOrg_facet(List org_facet){
		this.org_facet = org_facet;
	}
 	public String getPer_facet(){
		return this.per_facet;
	}
	public void setPer_facet(String per_facet){
		this.per_facet = per_facet;
	}
 	public String getPublished_date(){
		return this.published_date;
	}
	public void setPublished_date(String published_date){
		this.published_date = published_date;
	}
 	public String getSection(){
		return this.section;
	}
	public void setSection(String section){
		this.section = section;
	}
 	public String getSubsection(){
		return this.subsection;
	}
	public void setSubsection(String subsection){
		this.subsection = subsection;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getUpdated_date(){
		return this.updated_date;
	}
	public void setUpdated_date(String updated_date){
		this.updated_date = updated_date;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
}
