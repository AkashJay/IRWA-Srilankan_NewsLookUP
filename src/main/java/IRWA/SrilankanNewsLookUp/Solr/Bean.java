package IRWA.SrilankanNewsLookUp.Solr;

import org.apache.solr.client.solrj.beans.Field;

public class Bean {
	
	@Field(SolrConstant.heading)
	private String heading;
	
	@Field(SolrConstant.link)
	private String link;
	
	@Field(SolrConstant.content)
	private String content;
	
	@Field(SolrConstant.date)
	private String date;
	
	@Field(SolrConstant.id)
	private String id;

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Bean [heading=" + heading + ", link=" + link + ", content=" + content + ", date=" + date + ", id=" + id
				+ "]";
	}


	

}
