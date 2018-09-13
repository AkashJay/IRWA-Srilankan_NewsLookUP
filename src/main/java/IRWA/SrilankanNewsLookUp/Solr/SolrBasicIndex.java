package IRWA.SrilankanNewsLookUp.Solr;

import java.io.File;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.CommonParams;


public class SolrBasicIndex {
	
	//Diretory file path to read json files
			public static File solrJsonDirectory1 = new File("/home/akash/Documents/News_ApacheSolr/hiru");
			
			//Create a solr client object to connect to solr in local
			private static SolrClient client = null;
			
			
			public static void main(String[] args) throws SolrServerException, IOException {
				
				//Access the bookstore core using solr client object created above
				 client = ApacheSolrClient.getInstance().getClient();
				
				//client = new HttpSolrClient("http://localhost:8983/solr/bookstore");
				 System.out.println("Solr client created "+ ((HttpSolrClient) client).getBaseURL());
				
				
				
				//Index the documents
				index();
				
				//Then create the Query
				SolrQuery query = basicQuery("27");
				System.out.println("Query formed as "+query.toQueryString());
				
				//Then execute the query and get the Document list that match the query
				SolrDocumentList documentList = executeQuery(query);
				
				
				//At last print the returned documents
				printAsDocuments(documentList);
			}
			
			private static void index() throws SolrServerException, IOException {
				
				for (File file : solrJsonDirectory1.listFiles()) {
					
					System.out.println("Indexing the file : "+file.getName());
					
					SolrInputDocument inputDocument = SolrUtills.getSolrDocumentFromFile(file);
					client.add(inputDocument);
				}
				
				client.commit();
				
				
			}
			
			private static SolrQuery basicQuery(String id) {
				//Build the query
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(SolrConstant.id).append(":").append(id);
				
				SolrQuery query = new SolrQuery();
				query.set(CommonParams.Q, buffer.toString());
				
				return query;
			}
			
			private static SolrDocumentList executeQuery(SolrQuery query) throws SolrServerException, IOException {
				
				//Limit the output
				query.setRows(10);
				//Execute the query and catch to a QueryResponse
				QueryResponse response = client.query(query);
				
				SolrDocumentList result	 = response.getResults();
				System.out.println("No of document returened : "+result.getNumFound());
				
				return result;
			}
			
			private static void printAsDocuments(SolrDocumentList documentList) {
				
				for(SolrDocument document : documentList) {
					Object object = document.get(SolrConstant.id);
					System.out.println("id value is : "+object);
				}
				
			}
}
