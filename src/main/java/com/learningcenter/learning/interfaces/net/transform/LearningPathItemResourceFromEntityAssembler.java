package com.learningcenter.learning.interfaces.net.transform;

import com.learningcenter.learning.domain.model.entities.LearningPathItem;
import com.learningcenter.learning.interfaces.net.resources.LearningPathItemResource;

public class LearningPathItemResourceFromEntityAssembler {
    public static LearningPathItemResource toResourceFromEntity(LearningPathItem learningPathItem){
        return new LearningPathItemResource(learningPathItem.getId(), learningPathItem.getCourse().getId(),
                learningPathItem.getTutorialId());
    }
}
