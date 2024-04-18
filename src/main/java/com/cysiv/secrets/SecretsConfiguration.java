package com.cysiv.secrets;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application-secrets.yml")
public class SecretsConfiguration {

    @Value("${AZURE_KEYVAULT_URI}")
    private String keyVaultUrl;

    @Bean
    public SecretClient getSecretClient(Environment environment) {

        String keyVaultUrl1 = environment.getProperty("AZURE_KEYVAULT_URI}");
        return new SecretClientBuilder()
                .vaultUrl(keyVaultUrl)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }
}
