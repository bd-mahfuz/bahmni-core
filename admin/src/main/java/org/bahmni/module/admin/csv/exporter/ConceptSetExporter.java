package org.bahmni.module.admin.csv.exporter;

import org.apache.log4j.Logger;
import org.bahmni.module.admin.concepts.mapper.ConceptSetMapper;
import org.bahmni.module.admin.csv.models.ConceptRow;
import org.bahmni.module.admin.csv.models.ConceptRows;
import org.bahmni.module.admin.csv.models.ConceptSetRow;
import org.openmrs.Concept;
import org.openmrs.api.APIException;
import org.openmrs.api.ConceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConceptSetExporter {

    private static final org.apache.log4j.Logger log = Logger.getLogger(ConceptSetExporter.class);
    @Autowired
    private ConceptService conceptService;
    private final ConceptSetMapper conceptSetMapper;

    public ConceptSetExporter() {
        conceptSetMapper = new ConceptSetMapper();
    }

    public ConceptRows exportConcepts(String conceptName) {
        Concept conceptSet = conceptService.getConceptByName(conceptName);
        if(conceptSet == null){
            throw new APIException("Concept " + conceptName + " not found");
        }
        return conceptSetMapper.mapAll(conceptSet);
    }
}
