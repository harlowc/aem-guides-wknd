package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;
import com.adobe.aem.guides.wknd.core.models.BioDetail;
import com.adobe.cq.wcm.core.components.models.Image;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {BioDetail.class},
        resourceType = {BioDetailImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BioDetailImpl implements BioDetail {
    protected static final String RESOURCE_TYPE = "wknd/components/content/bioDetail";

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private List<String> specializations;

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private ModelFactory modelFactory;

    private Image image;

    @PostConstruct
    private void init() {
        image = modelFactory.getModelFromWrappedRequest(request, request.getResource(), Image.class);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return description;
    }

    @Override
    public List<String> getSpecializations() {
       if(specializations !=null){
           Collections.sort(specializations);
           return new ArrayList<String>(specializations);
       }else{
           return Collections.emptyList();
       }
    }

    /**
     * @return the Image Sling Model of this resource, or null if the resource cannot create a valid Image Sling Model.
     */
    private Image getImage() {
        return image;
    }

    @Override
    public boolean isEmpty() {
        final Image componentImage = getImage();

        if (StringUtils.isBlank(name)) {
            // Name is missing, but required
            return true;
        } else if (specializations == null || specializations.isEmpty()) {
            // At least one specialization is required
            return true;
        } else if (componentImage == null || StringUtils.isBlank(componentImage.getSrc())) {
            // A valid image is required
            return true;
        } else if(StringUtils.isBlank(description)){
            // description is missing
            return true;
        }else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }
}
