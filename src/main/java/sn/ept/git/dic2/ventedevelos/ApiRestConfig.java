package sn.ept.git.dic2.ventedevelos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(
                title = "API du Site de Vente de Vélos",
                description = "Ceci est la documentation de notre API",
                contact = @Contact(
                        name = "Moussa NIANG",
                        email = "niangm@@ept.sn",
                        url = "blabla"
                ),
                version = "1.0",
                license = @License(name = "OpenSource")
        ),
        servers = {@Server(
                url = "http://localhost:8080/VenteDeVelos-1.0-SNAPSHOT/",
                variables = {
                        @ServerVariable(
                                name="urlDeploiement",
                                defaultValue = "http://localhost:8080/VenteDeVelos-1.0-SNAPSHOT/"
                        )
                }
        )}
)
public class ApiRestConfig extends Application {
}
