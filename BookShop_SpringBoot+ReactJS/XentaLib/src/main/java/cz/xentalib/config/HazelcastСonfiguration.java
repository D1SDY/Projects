package cz.xentalib.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JavaDeveloperZone on 08-01-2018.
 */
@Configuration
public class HazelcastСonfiguration {
    @Bean
    public Config hazelCastConfig() {
        Config config = new Config();
        config.setInstanceName("hazelcast-instance")        // hazel case instance name
                .addMapConfig(
                        new MapConfig()                     // create map
                                .setName("tourCache")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(2000));     // cache will be available until it will remove manually. less then 0 means never expired.
        return config;
    }
}