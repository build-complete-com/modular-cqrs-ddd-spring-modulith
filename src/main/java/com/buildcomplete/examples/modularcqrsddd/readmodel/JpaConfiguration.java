package com.buildcomplete.examples.modularcqrsddd.readmodel;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
class JpaConfiguration {

  @Bean
  PlatformTransactionManager jpaTransactionManager(
      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManagerCustomizers
        .ifAvailable((customizers) -> customizers.customize((TransactionManager) transactionManager));
    return transactionManager;
  }

  @Bean
  TransactionTemplate transactionTemplate(@Qualifier("jpaTransactionManager") PlatformTransactionManager txManager) {
    return new TransactionTemplate(txManager);
  }
}
