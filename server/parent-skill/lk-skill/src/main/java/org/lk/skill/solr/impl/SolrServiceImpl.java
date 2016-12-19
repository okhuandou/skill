package org.lk.skill.solr.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.lk.skill.solr.ISolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by lk on 2016/12/6.
 */
@Service
public class SolrServiceImpl implements ISolrService {

    @Autowired
    private SolrServer httpSolrServer;

    @Override
    public Object query(Object obj){
        SolrQuery query= new SolrQuery();
        query.setQuery("*:*");
        query.setStart(0);
        query.setRows(50);
        //执行查询
        try {
            QueryResponse response = httpSolrServer.query(query);
            //取查询结果
            SolrDocumentList documentList = response.getResults();
            long numFound = documentList.getNumFound();
            System.out.println("查询的条数为："+numFound);
            for(SolrDocument solrDocument: documentList){
                System.out.println(solrDocument.get("id"));
                System.out.println(solrDocument.get("name"));
                System.out.println(solrDocument.get("updateTime"));
                System.out.println(solrDocument.get("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void update(Object obj){
        try {
            UpdateResponse response = httpSolrServer.addBean(obj);
            httpSolrServer.commit();
            System.err.println(response.getStatus());// 响应状态
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object obj) {
        try {
            httpSolrServer.deleteByQuery("*:*");
            httpSolrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Object obj) {
        //操作solr部分，模拟商品上架，
        Random random =  new Random();
        long l = random.nextLong();
        SolrInputDocument doc = new SolrInputDocument();
        //ID
        doc.setField("id", obj.toString());
//            //名称
//            doc.setField("name_ik", "名称" + l);
//            //图片URL
//            doc.setField("url", "www.abdi.com"+ l);
//            //品牌 ID
//            doc.setField("brandId", "sdfasd"+ l);
//            doc.setField("price", "100000"+ l);
        try {
            httpSolrServer.add(doc);
            httpSolrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
