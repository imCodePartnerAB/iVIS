package com.imcode.imcms.addon.ivisclient.utils;

import com.imcode.entities.ApplicationFormQuestion;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.nullsLast;

/**
 * Created by vitaly on 25.01.16.
 */
public class Step implements Comparable<Step> {
    private Comparator<Step> naturalComparator = nullsLast(comparing(Step::getSortOrder, nullsLast(comparingInt(Integer::intValue))));
    private String name;
    private Integer sortOrder;
//    private SortedSet<ApplicationFormQuestion> questions = new TreeSet();

    public Step(String name, Integer sortOrder) {
        this(name, sortOrder, Collections.emptySet());
    }

    public Step(String name, Integer sortOrder, Collection<ApplicationFormQuestion> questions) {
        this.name = name;
        this.sortOrder = sortOrder;
        questions.addAll(questions);
    }

    public String getName() {
        return name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

//    public SortedSet<ApplicationFormQuestion> getQuestions() {
//        return questions;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Step)) return false;

        Step step = (Step) o;

        if (name != null ? !name.equals(step.name) : step.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(Step o) {
        return naturalComparator.compare(this, o);
    }

    @Override
    public String toString() {
        return name;
    }
}