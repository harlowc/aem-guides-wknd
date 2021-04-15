package com.adobe.aem.guides.wknd.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
/**
 * BioInfoModel adapts JCR property of bioDetailPage
 *  * @author charlow
 *
 */
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BioInfoModel {

  protected final transient Logger LOG = LoggerFactory.getLogger(BioInfoModel.class);

  //Constants
  private static final String HTML_EXTENSION = ".html";
  private static final String COMMA_AND_SPACE = ", ";
  private static final String EMPTY_SPACE = " ";
  private static final String S_STRING = "s";
  private static final String APOSTROHE = "'";
  private static final String APOSTROHE_WITH_S = APOSTROHE + S_STRING;
  //JCR property string
  private static final String NAME = "name";
  private static final String SEX = "sex";
  private static final String DESCRIPTION = "description";
  private static final String SPECIALIZATIONS = "specializations";
  private static final String ROLE = "role";
  private static final String EMAIL = "email";
  private static final String OFFICE_PHONE = "officePhone";
  private static final String CELL_PHONE = "cellPhone";
  private static final String BANKER_PAGE_THUMBNAIL = "image/fileReference";
  
  //Default thumbnail path
  private static final String DEFAULT_THUMBNAIL_MALE = "/etc.clientlibs/wknd/clientlibs/clientlib-base/resources/images/profilePicM.jpg";
  private static final String DEFAULT_THUMBNAIL_FEMALE = "/etc.clientlibs/wknd/clientlibs/clientlib-base/resources/images/profilePicF.jpg";


  @SlingObject
  private transient ResourceResolver resourceResolver;

  @ValueMapValue(name = NAME)
  private String bioName;
  
  @ValueMapValue(name = SEX)
  @Default(values = "male")
  private String sex;

  @ValueMapValue(name = ROLE)
  private String role;

  @ValueMapValue(name = EMAIL)
  private String bioEmail;

  @ValueMapValue(name = OFFICE_PHONE)
  private String officePhone;
  
  @ValueMapValue(name = CELL_PHONE)
  private String cellPhone;

  @ValueMapValue(name = BANKER_PAGE_THUMBNAIL)
  private String pageThumbnail;

  @ValueMapValue(name = SPECIALIZATIONS)
  private List<String> specializations;

  @ValueMapValue(name = DESCRIPTION)
  private String description;

  
  private String pagePath;
  private String firstName;
  
  @PostConstruct
  protected void init() {
	  
	if(StringUtils.isEmpty(pageThumbnail)) {
		this.pageThumbnail = this.sex.equalsIgnoreCase("female") ? DEFAULT_THUMBNAIL_FEMALE : DEFAULT_THUMBNAIL_MALE;
	}
    
    if (StringUtils.isNotEmpty(bioName)) {
      String splitName = bioName.split(EMPTY_SPACE)[0];

      if (splitName.endsWith(S_STRING)) {
        firstName = splitName + APOSTROHE;
      } else {
        firstName = splitName + APOSTROHE_WITH_S;
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BioInfoModel infoModel = (BioInfoModel) o;

    return bioName.equals(infoModel.bioName) && role.equals(infoModel.role) && bioEmail.equals(infoModel.bioEmail);
  } 


  public void setPath(String path){
    this.pagePath = path + HTML_EXTENSION;
  }

  public String getSex() {
	  return this.sex;
  }
  
  public String getDescription() {
	  return this.description;
  }

  public String getPagePath() {
    return this.pagePath;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getBioName() {
    return this.bioName;
  }

  public String getRole() {
    return this.role;
  }

  public String getBioEmail() {
    return this.bioEmail;
  }

  
  public String getBankerPhone() {
    return this.officePhone;
  }

  public String getPageThumbnail() {
    return this.pageThumbnail;
  }

	public String getOfficePhone() {
		return this.officePhone;
  }
  
	public String getCellPhone() {
		return this.cellPhone;
  }
  
  public List<String> getSpecializations() {
    if(specializations !=null){
        Collections.sort(specializations);
        return new ArrayList<String>(specializations);
    }else{
        return Collections.emptyList();
    }
 }
  



  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + (this.bioName != null ? this.bioName.hashCode() : 0) + (this.role != null ? this.role.hashCode() : 0);

    return hash;
  }

 	@Override
	public String toString() {
			return "{\"bioName\":\"" + bioName + "\",\"bioEmail\":\"" + bioEmail + "\",\"officePhone\":\"" + officePhone + "\",\"cellPhone\":\"" + cellPhone 
                + "',\"pageThumbnail\":\"" + pageThumbnail + ",\"pagePath\":\"" + pagePath + "\",\"firstName\":\"" + firstName + "\",\"officePhoneNumber\":\"" + 
					officePhone + "\",\"cellPhone\":\"" + cellPhone + "\"}";
	}
}