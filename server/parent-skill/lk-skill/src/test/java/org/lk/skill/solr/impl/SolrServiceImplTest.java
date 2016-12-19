package org.lk.skill.solr.impl;

import base.BaseTest;
import org.junit.Test;
import org.lk.skill.solr.ISolrService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lk on 2016/12/15.
 */
public class SolrServiceImplTest extends BaseTest {

    @Autowired
    private ISolrService solrService;

    @Test
    public void query() throws Exception {
        solrService.query(null);
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void add() throws Exception {

    }
}