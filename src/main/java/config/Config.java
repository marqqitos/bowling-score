package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import gameManagement.implementations.ScoreManager;
import gameManagement.interfaces.IScoreManager;

@Configuration
@ComponentScan("gameManagement.implementations")
public class Config {
	
	@Bean
    public IScoreManager scoreManager() {
        return new ScoreManager();
    }

}
