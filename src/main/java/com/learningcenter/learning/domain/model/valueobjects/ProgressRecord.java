package com.learningcenter.learning.domain.model.valueobjects;

import com.learningcenter.learning.domain.model.aggregates.Enrollment;
import com.learningcenter.learning.domain.model.entities.ProgressRecordItem;
import com.learningcenter.learning.domain.model.entities.Tutorial;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ProgressRecord {

    @OneToMany(mappedBy = "enrollment")
    private List<ProgressRecordItem> progressRecordItemList;

    public ProgressRecord(){
        progressRecordItemList = new ArrayList<>();
    }

    public  void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath){
        Tutorial tutorial = learningPath.getFirstTutorialInLearningPathList();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorial);

        progressRecordItemList.add(progressRecordItem);
    }
    public  void starTutorial( Long tutorialId){
        if(hasAnItemInProgress()) throw new IllegalArgumentException("A tutorial is already in progress");
        ProgressRecordItem progressRecordItem = getProgressRecordItemByTutorialId(tutorialId);
        if (progressRecordItem != null){
            if(progressRecordItem.isNotStarted())
                progressRecordItem.start();
            else throw new IllegalStateException("Tutorial with give id is already started o completed");
        }
        else {
            throw new IllegalArgumentException("Tutorial with given Id not found in progress record");
        }
    }

    public void completeTutorial(Long tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemByTutorialId(tutorialId);
        if (progressRecordItem != null) {
            progressRecordItem.completed();
        }
        else throw  new IllegalArgumentException("Tutorial with given Id not found in progress record");

        if(learningPath.isLastTutorialInLearningPathList(tutorialId)) return;

        Tutorial nextTutorial = learningPath.getNextTutorialInLearningPathList(tutorialId);
        if(nextTutorial != null){
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorial);
            progressRecordItemList.add(nextProgressRecordItem);
        }

    }

    public  ProgressRecordItem getProgressRecordItemByTutorialId(Long tutorialId){
        return progressRecordItemList.stream().filter(progressRecordItem -> progressRecordItem.getTutorial()
                .getId().equals(tutorialId)).findFirst().orElse(null);
    }

    public boolean hasAnItemInProgress(){
        return progressRecordItemList.stream().anyMatch(ProgressRecordItem::isInProgress);
    }
    public Long calculateDaysElapsedForEnrollment(Enrollment enrollment){
        return progressRecordItemList.stream().filter(progressRecordItem ->  progressRecordItem.getEnrollment()
                .equals(enrollment)).mapToLong(ProgressRecordItem::calculateDaysElapsed).sum();
    }

}
