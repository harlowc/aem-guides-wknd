package com.adobe.aem.guides.wknd.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import com.day.cq.wcm.api.Page;


/**
 * BioDetailModel is used in bio details component to get bioDetailPage properties from the BioInfoModel
 * @author charlow
 *
 */

@Model(adaptables={ Resource.class, SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BioDetailModel {
    
	@ScriptVariable
    private Page currentPage;
    private BioInfoModel bioInfo;
      
      @PostConstruct
    protected void init() {
      bioInfo = currentPage.getContentResource().adaptTo(BioInfoModel.class);
      }
  
      public BioInfoModel getBioInfo() {
          return bioInfo;
      }
}
