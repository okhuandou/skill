package org.lk.skill.solr;

/**
 * Created by lk on 2016/12/5.
 */
public interface ISolrService {

    public void add(Object obj);

    public  Object query(Object obj);

    public void update(Object obj);

    public void delete(Object obj);

}
