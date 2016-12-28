package es.tid.topologyModuleBase.TAPITopoModel.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import es.tid.topologyModuleBase.TAPITopoModel.model.ExtensionsSpec;
import es.tid.topologyModuleBase.TAPITopoModel.model.NameAndValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;



/**
 * The TAPI GlobalComponent serves as the super class for all TAPI entities that can be directly retrieved by their ID. As such, these are first class entities and their ID is expected to be globally unique. 
 **/

/**
 * The TAPI GlobalComponent serves as the super class for all TAPI entities that can be directly retrieved by their ID. As such, these are first class entities and their ID is expected to be globally unique. 
 */
@ApiModel(description = "The TAPI GlobalComponent serves as the super class for all TAPI entities that can be directly retrieved by their ID. As such, these are first class entities and their ID is expected to be globally unique. ")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-28T15:11:12.465+01:00")
public class LocalClass   {
  private List<ExtensionsSpec> extensions = new ArrayList<ExtensionsSpec>();

  private String localId = null;

  private List<NameAndValue> name = new ArrayList<NameAndValue>();

  public LocalClass extensions(List<ExtensionsSpec> extensions) {
    this.extensions = extensions;
    return this;
  }

  public LocalClass addExtensionsItem(ExtensionsSpec extensionsItem) {
    this.extensions.add(extensionsItem);
    return this;
  }

   /**
   * none
   * @return extensions
  **/
  @ApiModelProperty(value = "none")
  public List<ExtensionsSpec> getExtensions() {
    return extensions;
  }

  public void setExtensions(List<ExtensionsSpec> extensions) {
    this.extensions = extensions;
  }

  public LocalClass localId(String localId) {
    this.localId = localId;
    return this;
  }

   /**
   * none
   * @return localId
  **/
  @ApiModelProperty(value = "none")
  public String getLocalId() {
    return localId;
  }

  public void setLocalId(String localId) {
    this.localId = localId;
  }

  public LocalClass name(List<NameAndValue> name) {
    this.name = name;
    return this;
  }

  public LocalClass addNameItem(NameAndValue nameItem) {
    this.name.add(nameItem);
    return this;
  }

   /**
   * List of names. A property of an entity with a value that is unique in some namespace but may change during the life of the entity. A name carries no semantics with respect to the purpose of the entity.
   * @return name
  **/
  @ApiModelProperty(value = "List of names. A property of an entity with a value that is unique in some namespace but may change during the life of the entity. A name carries no semantics with respect to the purpose of the entity.")
  public List<NameAndValue> getName() {
    return name;
  }

  public void setName(List<NameAndValue> name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocalClass localClass = (LocalClass) o;
    return Objects.equals(this.extensions, localClass.extensions) &&
        Objects.equals(this.localId, localClass.localId) &&
        Objects.equals(this.name, localClass.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extensions, localId, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocalClass {\n");
    
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    localId: ").append(toIndentedString(localId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

