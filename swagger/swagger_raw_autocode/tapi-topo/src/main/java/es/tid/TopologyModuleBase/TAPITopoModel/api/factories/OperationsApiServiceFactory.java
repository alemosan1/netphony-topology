package es.tid.TopologyModuleBase.TAPITopoModel.api.factories;

import es.tid.TopologyModuleBase.TAPITopoModel.api.OperationsApiService;
import es.tid.TopologyModuleBase.TAPITopoModel.api.impl.OperationsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-09T11:18:25.638+01:00")
public class OperationsApiServiceFactory {
    private final static OperationsApiService service = new OperationsApiServiceImpl();

    public static OperationsApiService getOperationsApi() {
        return service;
    }
}