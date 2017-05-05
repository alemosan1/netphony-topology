package es.tid.topologyModuleBase.TAPITopoModel.api.factories;

import es.tid.topologyModuleBase.TAPITopoModel.api.ConfigApiService;
import es.tid.topologyModuleBase.TAPITopoModel.api.impl.ConfigApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-03-31T10:56:12.255Z")
public class ConfigApiServiceFactory {
    private final static ConfigApiService service = new ConfigApiServiceImpl();

    public static ConfigApiService getConfigApi() {
        return service;
    }
}
