package com.buland.graphql.netflixdgs.springboot;

import com.buland.graphql.netflixdgs.springboot.datafetchers.AlbumsDataFetcher;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {DgsAutoConfiguration.class, AlbumsDataFetcher.class} )
public class AlbumsDataFetcherTests {

    @Autowired
    DgsQueryExecutor queryExecutor;

    @Test
    void albums() {

        List<String> albumTitles = queryExecutor.executeAndExtractJsonPath( "query albums {\n" +
                "  albums(titleFilter: \"Rumours\") {\n" +
                "    title\n" +
                "    artist\n" +
                "    recordNo\n" +
                "  }\n" +
                "}", "data.albums[*].title");

        Assertions.assertNotNull(albumTitles);
        Assertions.assertEquals( albumTitles.size(), 1);
    }
}
