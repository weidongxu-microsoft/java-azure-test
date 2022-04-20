package com.fabrikam;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.identity.EnvironmentCredentialBuilder;
import com.azure.identity.AzureAuthorityHosts;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.management.AzureEnvironment;
import com.azure.resourcemanager.AzureResourceManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        TokenCredential credential = new EnvironmentCredentialBuilder()
        .authorityHost(AzureAuthorityHosts.AZURE_PUBLIC_CLOUD)
        .build();

        // If you don't set the tenant ID and subscription ID via environment variables,
        // change to create the Azure profile with tenantId, subscriptionId, and Azure environment.
        AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);

        AzureResourceManager azureResourceManager = AzureResourceManager.configure()
                .withLogLevel(HttpLogDetailLevel.BASIC)
                .authenticate(credential, profile)
                .withDefaultSubscription();


        System.out.println("count " + azureResourceManager.storageAccounts().list().stream().count());

        System.exit(0);
    }
}
