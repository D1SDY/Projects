package cz.xentalib.rest;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Elastic Controller
 */
@RestController
public class ElasticNewsController {
    @Autowired
    Client client;

    @GetMapping("/view/name/{field}")
    public Map<String, Object> searchByName(@PathVariable final String field) {
        Map<String, Object> map = null;
        SearchResponse response = client.prepareSearch("auctions")
                .setTypes("organizer")
                .setSearchType(SearchType.QUERY_AND_FETCH).setQuery(QueryBuilders.matchQuery("name",
                        field)).get();
        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
        map = searchHits.get(0).getSource();
        return map;
    }

}
