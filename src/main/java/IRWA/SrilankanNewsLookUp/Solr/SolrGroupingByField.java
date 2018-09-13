package IRWA.SrilankanNewsLookUp.Solr;


import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.GroupParams;


public class SolrGroupingByField {
	
	private static SolrClient client = null;

	public static void main(String[] args) throws SolrServerException, IOException {
		client = ApacheSolrClient.getInstance().getClient();

		SolrQuery query = new SolrQuery();
		query.set(CommonParams.Q, "*:*");

		//Enable group by
		query.set(GroupParams.GROUP, "true");
		query.set(GroupParams.GROUP_FIELD, "date");
		query.add(GroupParams.GROUP_FIELD, "date");
		query.set(GroupParams.GROUP_LIMIT, "3");

		System.out.println("Query " + query.toQueryString());

		//get the response
		QueryResponse response = client.query(query);		
		GroupResponse groupResponse = response.getGroupResponse();
		
		
		for (GroupCommand gCommand : groupResponse.getValues()) {
			System.out.println("Name " + gCommand.getName());
			System.out.println("No of Matches  " + gCommand.getMatches());

			for (Group group : gCommand.getValues()) {
				System.out.println("Group " + group.getGroupValue());
				SolrDocumentList docs = group.getResult();
				for (SolrDocument doc : docs) {
					DocumentObjectBinder bind = new DocumentObjectBinder();
					Bean bean = bind.getBean(Bean.class, doc);
					System.out.println(bean);
					System.out.println("***********************");
				}
			}
			break;
		}
	}

}
