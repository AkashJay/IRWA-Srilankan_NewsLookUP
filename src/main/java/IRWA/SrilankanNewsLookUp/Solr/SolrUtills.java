package IRWA.SrilankanNewsLookUp.Solr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrInputDocument;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class SolrUtills {
	//Read json files and create solr documents in this class
	
		public static final String SOLR_OPERATION_ADD = "add";
		public static final String SOLR_OPERATION_SET = "set";
		
		//Read the json files
		public static SolrInputDocument getSolrDocumentFromFile(File file) throws FileNotFoundException {
		
			JSONObject newsJSON = (JSONObject) JSONValue.parse(new FileInputStream(file));
			return getSolrDocumentFromJSON(newsJSON);
			
		}

		//From the read json file extract solr document object and return
		private static SolrInputDocument getSolrDocumentFromJSON(JSONObject bookJSON) {
			//Document to catch all the field data
			SolrInputDocument solrDocument = new SolrInputDocument();
			
			//solrDocument.addField(name, value); 
			solrDocument.addField(SolrConstant.id, (String) bookJSON.get(SolrConstant.id));
			solrDocument.addField(SolrConstant.content, (String) bookJSON.get(SolrConstant.content));
			solrDocument.addField(SolrConstant.date, (String) bookJSON.get(SolrConstant.date));
			solrDocument.addField(SolrConstant.heading, (String) bookJSON.get(SolrConstant.heading));
			solrDocument.addField(SolrConstant.link, (String) bookJSON.get(SolrConstant.link));
			
			

			
			
			return solrDocument;
		}

		

}
