package es.tid.topologyModuleBase.IETFTopoModel.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-18T18:59:38.916+02:00")
public class SupportingNetworkSchema   {
  
  private String networkRef = null;

  /**
   * References the underlay network.
   **/
  public SupportingNetworkSchema networkRef(String networkRef) {
    this.networkRef = networkRef;
    return this;
  }

  
  @ApiModelProperty(value = "References the underlay network.")
  @JsonProperty("networkRef")
  public String getNetworkRef() {
    return networkRef;
  }
  public void setNetworkRef(String networkRef) {
    this.networkRef = networkRef;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SupportingNetworkSchema supportingNetworkSchema = (SupportingNetworkSchema) o;
    return Objects.equals(networkRef, supportingNetworkSchema.networkRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(networkRef);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SupportingNetworkSchema {\n");
    
    sb.append("    networkRef: ").append(toIndentedString(networkRef)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

