package com.codeside.pvapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;

/**
 * Generic SurveyQuestion Reference.
 */
@Document(collection = "survey_questions")
public class SurveyQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("question")
    private String question;

    @Field("idiom")
    private String idiom;

    @Field("next_true_question")
    private SurveyQuestion nextTrueQuestion;

    @Field("next_false_question")
    private SurveyQuestion nextFalseQuestion;

    @Field("linked_attribute")
    private String linkedAttribute;

    public String getId() {
    	  return id;
    }

    public SurveyQuestion id(String id) {
        this.id = id;
        return this;
    }

  	public void setId(String id) {
  		  this.id = id;
  	}

    public String getQuestion() {
  		return question;
  	}

    public SurveyQuestion question(String question) {
        this.question = question;
        return this;
    }

  	public void setQuestion(String question) {
  		  this.question = question;
  	}

  	public SurveyQuestion getNextTrueQuestion() {
  		  return nextTrueQuestion;
  	}

	  public SurveyQuestion nextTrueQuestion(SurveyQuestion nextTrueQuestion) {
        this.nextTrueQuestion = nextTrueQuestion;
        return this;
    }

  	public void setNextTrueQuestion(SurveyQuestion nextTrueQuestion) {
  		  this.nextTrueQuestion = nextTrueQuestion;
  	}

  	public SurveyQuestion getNextFalseQuestion() {
  		  return nextFalseQuestion;
  	}

  	public SurveyQuestion nextFalseQuestion(SurveyQuestion nextFalseQuestion) {
        this.nextFalseQuestion = nextFalseQuestion;
        return this;
    }

  	public void setNextFalseQuestion(SurveyQuestion nextFalseQuestion) {
  		  this.nextFalseQuestion = nextFalseQuestion;
  	}

  	public String getLinkedAttribute() {
  		  return linkedAttribute;
  	}

  	public SurveyQuestion linkedAttribute(String linkedAttribute) {
        this.linkedAttribute = linkedAttribute;
        return this;
    }

  	public void setLinkedAttribute(String linkedAttribute) {
  		  this.linkedAttribute = linkedAttribute;
  	}

  	public String getIdiom() {
        return idiom;
    }

    public SurveyQuestion idiom(String idiom) {
        this.idiom = idiom;
        return this;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    @Override
    public String toString() {
        return "SurveyQuestion{" +
    		", id='" + getId() + "'" +
            ", question='" + getQuestion() + "'" +
            ", nextTrueQuestion='" + getNextTrueQuestion() + "'" +
            ", nextFalseQuestion='" + getNextFalseQuestion() + "'" +
            ", linkedAttribute='" + getLinkedAttribute() + "'" +
            ", idiom='" + getIdiom() + "'" +
            "}";
    }
}
