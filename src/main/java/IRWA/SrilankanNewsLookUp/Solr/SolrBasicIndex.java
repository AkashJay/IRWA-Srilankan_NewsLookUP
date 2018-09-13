package IRWA.SrilankanNewsLookUp.Solr;

import java.io.File;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrBasicIndex {
	
	//Diretory file path to read json files
			public static File solrJsonDirectory1 = new File("/home/akash/Documents/News_ApacheSolr/hiru");
			
			//Create a solr client object to connect to solr in local
			private static SolrClient client = null;
			
			
			public static void main(String[] args) throws SolrServerException, IOException {
				
				//Access the bookstore core using solr client object created above
				 client = new HttpSolrClient.Builder("http://localhost:8983/solr/news_lookup").build();
				
				//client = new HttpSolrClient("http://localhost:8983/solr/bookstore");
				 System.out.println("Solr client created "+ ((HttpSolrClient) client).getBaseURL());
				
				
				
				//Index the documents
				index();
				
//				//Then create the Query
//				SolrQuery query = basicQuery("1");
//				System.out.println("Query formed as "+query.toQueryString());
//				
//				//Then execute the query and get the Document list that match the query
//				SolrDocumentList documentList = executeQuery(query);
//			
//						
//				//At last print the returned documents
//				printAsDocuments(documentList);
//				printAsDocumentsAsABean(documentList);
				
				
				
				//Now we will try to delete a document for the given query
				//executeDelete(query);
				
				
			}
			
			private static void index() throws SolrServerException, IOException {
				
				for (File file : solrJsonDirectory1.listFiles()) {
					
					System.out.println("Indexing the file : "+file.getName());
					
					SolrInputDocument inputDocument = SolrUtills.getSolrDocumentFromFile(file);
					client.add(inputDocument);
				}
				
				client.commit();
				
				
			}
}
