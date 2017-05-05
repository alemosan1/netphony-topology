package es.tid.topologyModuleBase.TAPITopoModel.api.factories;

import es.tid.topologyModuleBase.TAPITopoModel.api.OperationsApiService;
import es.tid.topologyModuleBase.TAPITopoModel.api.impl.OperationsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-03-31T10:56:12.255Z")
public class OperationsApiServiceFactory {
    private final static OperationsApiService service = new OperationsApiServiceImpl();

    public static OperationsApiService getOperationsApi() {
        return service;
    }
}
