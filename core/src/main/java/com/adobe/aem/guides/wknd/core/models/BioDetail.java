package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

/**
 * Represents the Byline AEM Component for the WKND Site project.
 **/
public interface BioDetail {
    /***
     * @return a string to display as the name.
     */
    String getName();

    /***
     * @return a string to display the Description for the Bio.
     */
    String getDescription();

    /***
     * Occupations are to be sorted alphabetically in a descending order.
     *
     * @return a list of occupations.
     */
    List<String> getSpecializations();

    /***
     * @return a boolean if the component has enough content to display.
     */
    boolean isEmpty();
}
