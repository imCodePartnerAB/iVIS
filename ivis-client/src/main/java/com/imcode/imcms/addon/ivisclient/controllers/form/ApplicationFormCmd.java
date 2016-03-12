package com.imcode.imcms.addon.ivisclient.controllers.form;

import com.imcode.entities.ApplicationFormQuestion;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vitaly on 23.02.16.
 */
public class ApplicationFormCmd {
    private List<ApplicationFormQuestion> questions = new ArrayList<>();

    public List<ApplicationFormQuestion> getQuestions() {
        return questions;
    }

    public Set<ApplicationFormQuestion> getQuestionSet() {
        return new LinkedHashSet<>(questions);
    }

    public void setQuestions(List<ApplicationFormQuestion> questions) {
        this.questions = questions;
    }


}
