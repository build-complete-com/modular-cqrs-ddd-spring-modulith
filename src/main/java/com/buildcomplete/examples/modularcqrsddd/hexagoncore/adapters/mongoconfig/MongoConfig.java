package com.buildcomplete.examples.modularcqrsddd.hexagoncore.adapters.mongoconfig;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadConcern;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
class MongoConfig extends AbstractMongoClientConfiguration {
    private final String connectionString;
    private final String databaseName;

    MongoConfig(@Value("${mongodb.connectionString}") String connectionString,
                @Value("${mongodb.databaseName}") String databaseName) {
        this.connectionString = connectionString;
        this.databaseName = databaseName;
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(this.connectionString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .readConcern(ReadConcern.MAJORITY)
                .writeConcern(WriteConcern.MAJORITY)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .retryWrites(true)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    @Primary
    MongoTransactionManager transactionManager(MongoDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }
}
