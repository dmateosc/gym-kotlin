package com.example.graphql.resolver;

import com.example.graphql.domain.bank.BankAccount;
import com.example.graphql.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {


        public BankAccount bankAccount(UUID id){
            log.info("Retrieving name account  {}", id);
            return BankAccount.builder().id(id).currency(Currency.USD).name("David").build();
        }

}
