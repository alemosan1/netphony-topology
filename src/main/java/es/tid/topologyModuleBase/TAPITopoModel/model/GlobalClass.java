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
public class GlobalClass   {
  private List<ExtensionsSpec> extensions = new ArrayList<ExtensionsSpec>();

  private List<NameAndValue> label = new ArrayList<NameAndValue>();

  private String uuid = null;

  private List<NameAndValue> name = new ArrayList<NameAndValue>();

  public GlobalClass extensions(List<ExtensionsSpec> extensions) {
    this.extensions = extensions;
    return this;
  }

  public GlobalClass addExtensionsItem(ExtensionsSpec extensionsItem) {
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

  public GlobalClass label(List<NameAndValue> label) {
    this.label = label;
    return this;
  }

  public GlobalClass addLabelItem(NameAndValue labelItem) {
    this.label.add(labelItem);
    return this;
  }

   /**
   * List of labels.A property of an entity with a value that is not expected to be unique and is allowed to change. A label carries no semantics with respect to the purpose of the entity and has no effect on the entity behavior or state.
   * @return label
  **/
  @ApiModelProperty(value = "List of labels.A property of an entity with a value that is not expected to be unique and is allowed to change. A label carries no semantics with respect to the purpose of the entity and has no effect on the entity behavior or state.")
  public List<NameAndValue> getLabel() {
    return label;
  }

  public void setLabel(List<NameAndValue> label) {
    this.label = label;
  }

  public GlobalClass uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

   /**
   * Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}   A Universally Unique IDentifier in the string representation defined in RFC 4122.  The canonical representation uses lowercase characters.   The following is an example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6
   * @return uuid
  **/
  @ApiModelProperty(value = "Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}   A Universally Unique IDentifier in the string representation defined in RFC 4122.  The canonical representation uses lowercase characters.   The following is an example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6")
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public GlobalClass name(List<NameAndValue> name) {
    this.name = name;
    return this;
  }

  public GlobalClass addNameItem(NameAndValue nameItem) {
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
    GlobalClass globalClass = (GlobalClass) o;
    return Objects.equals(this.extensions, globalClass.extensions) &&
        Objects.equals(this.label, globalClass.label) &&
        Objects.equals(this.uuid, globalClass.uuid) &&
        Objects.equals(this.name, globalClass.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extensions, label, uuid, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GlobalClass {\n");
    
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
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

